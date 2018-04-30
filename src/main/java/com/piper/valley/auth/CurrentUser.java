package com.piper.valley.auth;

import antlr.StringUtils;
import com.piper.valley.models.enums.Role;
import com.piper.valley.models.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private User user;

	private Integer ordersCount;

	public CurrentUser(User user, Integer ordersCount) {
		super(user.getUsername(), user.getPasswordHash(),
			AuthorityUtils.commaSeparatedStringToAuthorityList(
					StringUtils.stripFrontBack(user.getRoles().toString(), "[", "]" )
			)
		);
		this.user = user;
		this.ordersCount = ordersCount;
	}

	public User getUser() {
		return user;
	}


	public Integer getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(Integer ordersCount) {
		this.ordersCount = ordersCount;
	}

	public long getId() {
		return user.getId();
	}

	public Collection<Role> getRole() {
		return user.getRoles();
	}
}
