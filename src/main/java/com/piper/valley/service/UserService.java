package com.piper.valley.service;

import com.piper.valley.dao.UserDao;
import com.piper.valley.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public boolean register(User user, String confirmPassword) {
		//Validation (TODO Validate all attributes)
		if (!confirmPassword.equals(user.getPassword()))
			return false;

		if (userDao.getEntityByUsername(user.getUsername()) != null)
			return false;

		return userDao.insertEntityToDb(new User("tmpId", user.getName(), user.getUsername(), user.getPassword(), user.getEmail()));
	}

	public boolean login(String username, String password) {

		//Get User by Username
		User user = userDao.getEntityByUsername(username);

		//Check if user exists and has same password
		if (user != null && user.getPassword().equals(password))
			return true;

		//User doesn't exist. or password doesn't match.
		return false;
	}

}
