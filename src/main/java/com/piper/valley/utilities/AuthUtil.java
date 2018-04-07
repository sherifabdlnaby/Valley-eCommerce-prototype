package com.piper.valley.utilities;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.models.domain.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.List;

public class AuthUtil {
	//Only use it in non controller context!
	public static CurrentUser getCurrentUser(){
		return (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static void addRoleAtRuntime(Role role){
		//Add Role at Runtime!
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());

		if(updatedAuthorities.contains(new SimpleGrantedAuthority(role.name())))
			return;

		//Add it in List
		updatedAuthorities.add(new SimpleGrantedAuthority(role.name()));

		//Add it in Session User
		CurrentUser updatedUser = getCurrentUser();
		updatedUser.getRole().add(role);

		//Update da kolo ba2a
		Authentication newAuth = new PreAuthenticatedAuthenticationToken(updatedUser, auth.getCredentials(), updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}

	public static void updateOrders(Integer newCount){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		//Add it in Session User
		CurrentUser updatedUser = getCurrentUser();
		updatedUser.setOrdersCount(newCount);

		//Update da kolo ba2a
		Authentication newAuth = new PreAuthenticatedAuthenticationToken(updatedUser, auth.getCredentials(), auth.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}
}
