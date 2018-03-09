package com.piper.valley.auth;

import com.piper.valley.models.domain.Role;
import com.piper.valley.models.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private User user;

	public CurrentUser(User user) {
		super(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public int getId() {
		return user.getId();
	}

	public Role getRole() {
		return user.getRole();
	}

}
