package com.piper.valley.models.service;

import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.repository.StoreRepository;
import com.piper.valley.models.repository.UserRepository;
import com.piper.valley.utilities.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<Store> getStoreById(Long id) {
		return Optional.ofNullable(storeRepository.findOne(id));
	}

	@Override
	public void acceptStore(long storeId) {
		Optional<Store> store = Optional.ofNullable(storeRepository.findOne(storeId));
		store.ifPresent(store1 -> {
			store1.setAccepted(true);
			storeRepository.save(store1);
		});
	}

	@Override
	public Collection<Store> getAllStores() {
		return storeRepository.findAll();
	}

	@Override
	public Collection<Store> getAllAppliedStores() {
		return storeRepository.findAllByAccepted(false);
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
		store.setAccepted(false);
		store.setName(form.getName());

		//Add New Role to User (We query as session user can be outdated)
		User user = userRepository.findOne(sessionUser.getId());

		if(!user.getRole().contains(Role.STORE_OWNER))
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
}

