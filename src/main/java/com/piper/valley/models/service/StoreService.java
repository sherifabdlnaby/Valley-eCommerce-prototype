package com.piper.valley.models.service;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.models.domain.Store;

import java.util.Collection;
import java.util.Optional;


public interface StoreService {
	Optional<Store> getStoreById(long id);

	void acceptStore(long storeId);

	Collection<Store> getAllStores();

	Store add(AddStoreForm form, CurrentUser user);
}

