package com.piper.valley.models.service;

import com.piper.valley.forms.AddStoreCollaboratorForm;
import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.forms.AddStoreProductForm;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.repository.StoreRepository;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

    @Autowired
    private StoreHistoryService storeHistoryService;

	@Autowired
	private StoreProductService storeProductService;

    @Override
	public Optional<Store> getStoreById(Long id) {
		return Optional.ofNullable(storeRepository.findOne(id));
	}

    @Override
    public Optional<Store> getStoreByName(String name) {
        return storeRepository.findByName(name);
    }

    @Override
	public Store acceptStore(Long storeId) {
		Optional<Store> store = Optional.ofNullable(storeRepository.findOne(storeId));
		if(store.isPresent()) {
			Store store1 = store.get();
			store1.setStatus(StoreStatus.ACCEPTED);
			return storeRepository.save(store1);
		}
		return null;
	}

	@Override
	public Store rejectStore(Long storeId) {
		Optional<Store> store = Optional.ofNullable(storeRepository.findOne(storeId));
		if(store.isPresent()) {
			Store store1 = store.get();
			store1.setStatus(StoreStatus.REJECTED);
			return storeRepository.save(store1);
		}
		return null;
	}

	@Override
	public Collection<Store> getAllStores() {
		return storeRepository.findAll();
 	}

	@Override
	public Collection<Store> getAllAppliedStores() {
		return storeRepository.findAllByStatus(StoreStatus.PENDING);
	}

	@Override
	public Collection<Store> getAllUserAndCollabStores(Long storeOwnerId) {
		return storeRepository.findAllByStoreOwner_IdOrCollaborators_User_IdAndStatus(storeOwnerId, storeOwnerId, StoreStatus.ACCEPTED);
	}

	@Override
	public 	Collection<Store> getAllAcceptedUserStores(Long storeOwnerId){
		return storeRepository.findByStoreOwner_IdAndStatus(storeOwnerId, StoreStatus.ACCEPTED);
	}

	@Override
	public 	Collection<Store> getAllPendingUserStores(Long storeOwnerId){
		return storeRepository.findByStoreOwner_IdAndStatus(storeOwnerId, StoreStatus.PENDING);
	}

	@Override
	public 	Collection<Store> getAllNotAcceptedUserStores(Long storeOwnerId){
		return storeRepository.findByStoreOwner_IdAndStatus(storeOwnerId, StoreStatus.REJECTED);
	}

	@Override
	public 	Collection<Store> getAllCollaboratedUserStores(Long storeOwnerId){
		return userRepository.findOne(storeOwnerId).getStoreOwner().getCollaboratedStores();
	}

	@Override
	public Store add(AddStoreForm form, User sessionUser) {
		Store store;
		if(form.getPhysical())
		{
			PhysicalStore physicalStore = new PhysicalStore();
			physicalStore.setAddress(form.getAddress());
			store = physicalStore;
		}
		else
			store = new VirtualStore();

		//Common Attributes
		store.setStatus(StoreStatus.PENDING);
		store.setName(form.getName());

		//Add New Role to User (We query as session user can be outdated)
		User user = userRepository.findOne(sessionUser.getId());

		if(!user.getRoles().contains(Role.STORE_OWNER))
			user.addRole(Role.STORE_OWNER);

		//First Time StoreOwner (create storeowner row in table)
		if(user.getStoreOwner() == null){
			user.setStoreOwner(new StoreOwner());
			user.getStoreOwner().setUser(user);
		}

		//save user
		user = userRepository.save(user);

		//Link Store with the StoreOwner of LoggedUser(Delegation)
		store.setStoreOwner(user.getStoreOwner());

		return storeRepository.save(store);
	}

	@Override
	public StoreProduct addProductToStore(AddStoreProductForm form, User user) {
		Optional<Product> productOptional = productService.getProductById(form.getProductId());
		Optional<Store>   storeOptional   = this.getStoreById(form.getStoreId());

		//Don't have to check for Presence (validator should've checked for their existence)
		Product product = productOptional.get();
		Store store = storeOptional.get();

		StoreProduct storeProduct = new StoreProduct();
		storeProduct.setName(form.getName());
		storeProduct.setDescription(form.getDescription());
		storeProduct.setPrice(form.getPrice());
		storeProduct.setProduct(product);
		storeProduct.setStore(store);

		//add to store
		store.addStoreProduct(storeProduct);

		//Hibernate Bugs ? :"D
		Store save = storeRepository.save(store);

		//Save History
		StoreProductHistory storeProductHistory = new StoreProductHistory(
				user,
				store,
				storeProduct.getName() + " was added.",
				new Date(),
				StoreHistoryType.ADD,
				storeProduct.getStore().getId(),
				storeProduct.getProduct().getId(),
				storeProduct.getDescription(),
				storeProduct.getPrice(),
				storeProduct.getName(),
				storeProduct.getId()
		);

		storeHistoryService.add(storeProductHistory);

		return storeProduct;
	}

	@Override
	public Boolean removeProductFromStore(Long storeProductId, User user) {

		Optional<StoreProduct> optionalStoreProduct = storeProductService.getProductById(storeProductId);

		if(!optionalStoreProduct.isPresent())
			return false;

		//remove it
		StoreProduct storeProduct = optionalStoreProduct.get();

		storeProductService.remove(storeProduct);

		//Save History piece
		StoreProductHistory storeProductHistory = new StoreProductHistory(
				user,
				storeProduct.getStore(),
				storeProduct.getName() + " was remove from the store.",
				new Date(),
				StoreHistoryType.DELETE,
				storeProduct.getStore().getId(),
				storeProduct.getProduct().getId(),
				storeProduct.getDescription(),
				storeProduct.getPrice(),
				storeProduct.getName(),
				storeProduct.getId()
		);

		storeHistoryService.add(storeProductHistory);

		return true;
	}

	@Override
	public StoreOwner addCollaboratorToStore(AddStoreCollaboratorForm form, Long userId){
		Optional<Store> storeOptional   = this.getStoreById(form.getStoreId());
		Optional<User> userOptional	= userService.getUserByUsername(form.getUsername());
		Store store = storeOptional.get();
		User collaborator = userOptional.get();


		//Add New Role to User (We query as session user can be outdated)
		if(!collaborator.getRoles().contains(Role.STORE_OWNER))
			collaborator.addRole(Role.STORE_OWNER);

		//First Time StoreOwner (create storeowner row in table)
		if(collaborator.getStoreOwner() == null){
			collaborator.setStoreOwner(new StoreOwner());
			collaborator.getStoreOwner().setUser(collaborator);
		}

		collaborator.getStoreOwner().addStCollaberatedStore(store);
		collaborator = userRepository.save(collaborator);
		store.addCollaborator(collaborator.getStoreOwner());

		//save user
		store = storeRepository.save(store);

		//save history
		StoreCollabHistory storeCollabHistory = new StoreCollabHistory(
				new User(userId),
				store,
				"Collaborator: " + collaborator.getName() + " added to the store!",
				new Date(),
				StoreHistoryType.ADD,
				collaborator
		);

		storeHistoryService.add(storeCollabHistory);

		return collaborator.getStoreOwner();
	}

	@Override
	public void removeCollaboratorToStore(AddStoreCollaboratorForm form, Long userId){
		Optional<Store> storeOptional   = this.getStoreById(form.getStoreId());
		Optional<User> userOptional	= userService.getUserByUsername(form.getUsername());
		Store store = storeOptional.get();
		User collaborator = userOptional.get();
		collaborator.getStoreOwner().removeStCollaberatedStore(store);

		//Add New Role to User (We query as session user can be outdated)
		if(collaborator.getStoreOwner().getCollaboratedStores().isEmpty()&&collaborator.getStoreOwner().getStores().isEmpty())
			collaborator.removeRole(Role.STORE_OWNER);

		store.removeCollaborator(collaborator.getStoreOwner());

		if(collaborator.getStoreOwner() != null)
			collaborator.setStoreOwner(null);

		collaborator = userRepository.save(collaborator);
		store = storeRepository.save(store);

		//save history
		StoreCollabHistory storeCollabHistory = new StoreCollabHistory(
				new User(userId),
				store,
				"Collaborator: " + collaborator.getName() + " removed from the store!",
				new Date(),
				StoreHistoryType.DELETE,
				collaborator
		);

		storeHistoryService.add(storeCollabHistory);

	}
}

