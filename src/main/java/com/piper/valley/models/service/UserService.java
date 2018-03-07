package com.piper.valley.models.service;
import com.piper.valley.models.entity.Role;
import com.piper.valley.models.entity.User;
import com.piper.valley.models.repository.RoleRepository;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public boolean register(User user) {
		//Hash password using bCrypt
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));

		//Save to DB
		userRepository.save(user);

		return true;
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
