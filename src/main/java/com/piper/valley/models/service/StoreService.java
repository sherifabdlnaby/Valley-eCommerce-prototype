package com.piper.valley.models.service;

import com.piper.valley.forms.AddStoreCollaboratorForm;
import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.forms.AddStoreProductForm;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreOwner;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.User;

import java.util.Collection;
import java.util.Optional;


public interface StoreService {
	Optional<Store> getStoreById(Long id);

	Optional<Store> getStoreByName(String name);

	Store acceptStore(Long storeId);

	Store rejectStore(Long storeId);

	Collection<Store> getAllStores();

	Collection<Store> getAllAppliedStores();

	Collection<Store> getAllUserAndCollabStores(Long storeOwnerId);

	Collection<Store> getAllAcceptedUserStores(Long storeOwnerId);

	Collection<Store> getAllPendingUserStores(Long storeOwnerId);

	Collection<Store> getAllNotAcceptedUserStores(Long storeOwnerId);

	Collection<Store> getAllCollaboratedUserStores(Long storeOwnerId);

	Store add(AddStoreForm form, User user);

	StoreProduct addProductToStore(AddStoreProductForm form, User user);

	Boolean removeProductFromStore(Long storeProductId, User user);

	StoreOwner addCollaboratorToStore(AddStoreCollaboratorForm form, Long userId);

	void removeCollaboratorToStore(AddStoreCollaboratorForm form, Long userId);
}
