package com.piper.valley.auth;

import com.piper.valley.models.domain.User;
import com.piper.valley.models.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with username=%s was not found", username)));
										//because orders count is lazy loaded.
		return new CurrentUser(user, userService.getUserOrdersCount(user.getId()));
	}
}
