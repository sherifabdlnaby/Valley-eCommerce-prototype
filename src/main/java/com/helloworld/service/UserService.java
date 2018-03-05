package com.helloworld.service;

import com.helloworld.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

	private ArrayList<User> users = new ArrayList<>(Arrays.asList(
			new User("Refaie", "123"),
			new User("Hamed", "1234"),
			new User("Sherif", "12345")
	));

	public void addUser(User user) {
		users.add(user);
	}
}
