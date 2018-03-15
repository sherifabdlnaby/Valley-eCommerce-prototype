package com.piper.valley.models.service;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.Type;
import com.piper.valley.models.repository.StoreRepository;
import com.piper.valley.forms.AddStoreForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	public StoreServiceImpl(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Override
	public Optional<Store> getStoreById(long id) {
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
	public Store add(AddStoreForm form, CurrentUser user) {
		Store store=new Store();
		store.setName(form.getName());
		store.setAddress(form.getAddress());
		store.setPhone(form.getPhone());
		store.setType(Type.valueOf(form.getType()));
		store.setOwner_id(user.getUser().getId());
		return storeRepository.save(store);
	}
}
