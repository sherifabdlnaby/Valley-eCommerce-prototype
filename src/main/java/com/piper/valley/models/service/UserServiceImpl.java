package com.piper.valley.models.service;

import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.Role;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	public UserServiceImpl(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}

	@Override
	public Optional<User> getUserById(long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public Optional<User> getUserByUsername(String email) {
		return userRepository.findOneByUsername(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll(new Sort("email"));
	}

	@Override
	public User register(UserCreateForm form) {
		User user = new User();
		user.setName(form.getName());
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
		//Create Roles List
		Collection<Role> roles = new ArrayList<>();
		roles.add(Role.USER);
		//Add Roles List to User
		user.setRole(roles);
		return userRepository.save(user);
	}
}
