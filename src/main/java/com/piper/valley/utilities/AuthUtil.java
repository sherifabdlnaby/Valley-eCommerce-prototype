package com.piper.valley.utilities;

import com.piper.valley.auth.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
	//Only use it in non controller context!
	public static CurrentUser getCurrentUser(){
		return (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
