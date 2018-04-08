package com.piper.valley.auth;

import com.piper.valley.models.domain.Store;

public interface AuthService {

	boolean canAccessUser(CurrentUser currentUser, int userId);


	boolean canViewStore(Store store, CurrentUser currentUser);

	//Is a Collaborator or an actual Store Owner.
	boolean canAccessStore(Store store, CurrentUser currentUser);
}
