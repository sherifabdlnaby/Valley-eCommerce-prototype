package com.piper.valley.models.service;

import com.piper.valley.models.domain.Store;
import com.piper.valley.models.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;

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
		// TODO
		return null;
	}

	// TODO
//	@Override
//	public Store add(StoreCreateForm form) {
//		return null;
//	}
}
