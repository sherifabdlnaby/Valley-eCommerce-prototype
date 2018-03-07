package com.piper.valley.models.service;
import com.piper.valley.models.entity.User;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean register(User user, String confirmPassword) {
		//Validation (TODO Validate all attributes)
		List<User> x = userRepository.findAll();

		if (!confirmPassword.equals(user.getPassword()))
			return false;

		if (userRepository.existsByUsername(user.getUsername()))
			return false;

		if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail()))
			return false;

		User savedUser = userRepository.save(user);

		return false;
	}

	public boolean login(String username, String password) {

		//UserDao userDao = null;

		//Get User by Username
		User user = null;//userDao.getEntityByUsername(username);

		//Check if user exists and has same password
		if (user != null && user.getPassword().equals(password))
			return true;

		//User doesn't exist. or password doesn't match.
		return false;
	}

}
