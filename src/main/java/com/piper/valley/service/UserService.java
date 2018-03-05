package com.piper.valley.service;

import com.piper.valley.dao.EntityDao;
import com.piper.valley.dao.UserDao;
import com.piper.valley.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	//TODO
	public boolean register(User user) {
		return userDao.insertEntityToDb(user);
	}

	//TODO
	public boolean login(String username, String password) {

		//Get User by Username
		User user = userDao.getEntityByUsername(username);

		//Check if user exists and has same password
		if(user != null && user.getPassword().equals(password))
			return true;

		//User doesn't exist. or password doesn't match.
		return false;
	}

}
