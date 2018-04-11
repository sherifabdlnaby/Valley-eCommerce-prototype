package com.piper.valley.models.service;

import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.OrderStatus;
import com.piper.valley.models.domain.Role;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.repository.OrderRepository;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Optional<User> getUserById(Long id) {
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
	public Integer getUserOrdersCount(Long Id) {
		return orderRepository.countOrdersByUser_IdAndOrderStatus(Id, OrderStatus.UNPROCESSED);
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
		Set<Role> roles = new HashSet<>();
		roles.add(Role.USER);

		//Add Roles List to User
		user.setRole(roles);

		//Save da kolo
		return userRepository.save(user);
	}
}
