package com.piper.valley.auth;

import com.piper.valley.models.enums.Role;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.enums.StoreStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public boolean canAccessUser(CurrentUser currentUser, int userId) {
		return currentUser != null
				&& (currentUser.getRole().contains(Role.ADMIN) || currentUser.getId() == userId);
	}

	@Override
	public boolean canViewStore(Store store, CurrentUser currentUser) {
		return (store.getStatus() == StoreStatus.ACCEPTED
				|| currentUser.getRole().contains(Role.ADMIN)
				|| store.getStoreOwner().getId() == currentUser.getId());
	}

	@Override
	public boolean canAccessStore(Store store, CurrentUser currentUser) {
		return store.getStoreOwner().getId() == currentUser.getId()
				|| (currentUser.getUser().getStoreOwner() != null && store.getCollaborators().contains(currentUser.getUser().getStoreOwner()));
	}

}
