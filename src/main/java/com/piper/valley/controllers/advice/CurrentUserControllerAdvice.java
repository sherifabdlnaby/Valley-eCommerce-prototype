package com.piper.valley.controllers.advice;

import com.piper.valley.auth.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {

	@ModelAttribute("currentUser")
	public CurrentUser getCurrentUser(Authentication authentication) {
		return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
	}

}
