package com.piper.valley.models.service;

import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

	Optional<User> getUserById(Long id);

	Optional<User> getUserByEmail(String email);

	Optional<User> getUserByUsername(String email);

	Integer getUserOrdersCount(Long Id);

	Collection<User> getAllUsers();

	User register(UserCreateForm form);

}
