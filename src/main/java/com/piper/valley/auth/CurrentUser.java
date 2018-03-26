package com.piper.valley.auth;

import antlr.StringUtils;
import com.piper.valley.models.domain.Role;
import com.piper.valley.models.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private User user;

	public CurrentUser(User user) {
		super(user.getUsername(), user.getPasswordHash(),
			AuthorityUtils.commaSeparatedStringToAuthorityList(
					StringUtils.stripFrontBack(user.getRole().toString(), "[", "]" )
			)
		);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public long getId() {
		return user.getId();
	}

	public Collection<Role> getRole() {
		return user.getRole();
	}

}
