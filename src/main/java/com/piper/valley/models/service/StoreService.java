package com.piper.valley.models.service;

import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.User;

import java.util.Collection;
import java.util.Optional;


public interface StoreService {
	Optional<Store> getStoreById(long id);

	void acceptStore(long storeId);

	Collection<Store> getAllStores();

	Store add(AddStoreForm form, User user);
}
