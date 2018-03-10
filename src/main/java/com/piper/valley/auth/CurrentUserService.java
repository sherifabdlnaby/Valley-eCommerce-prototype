package com.piper.valley.auth;

public interface CurrentUserService {

	boolean canAccessUser(CurrentUser currentUser, int userId);

}
