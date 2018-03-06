package com.piper.valley.service;

import com.piper.valley.dao.UserDao;
import com.piper.valley.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public boolean register(User user, String confirmPassword) {
		//Validation (TODO Validate all attributes)
		if (!confirmPassword.equals(user.getPasswordHash()))
			return false;

		if (userDao.getEntityByUsername(user.getUsername()) != null)
			return false;

		return userDao.insertEntityToDb(new User("tmpId", user.getName(), user.getUsername(), user.getPasswordHash(), user.getEmail(),user.getType()));
	}

	public boolean login(String username, String password) {

		//Get User by Username
		User user = userDao.getEntityByUsername(username);

		//Check if user exists and has same password
		if (user != null && user.getPasswordHash().equals(password))
			return true;

		//User doesn't exist. or password doesn't match.
		return false;
	}

	public void updatePassword(User user, String newPassword) {
		String passwordHash = encrypt(newPassword);
		user.setPasswordHash(passwordHash);
		userDao.updateEntity(user);
	}

	public boolean matchPassword(User user, String password) {
		String hash = encrypt(password);
		return hash.equals(user.getPasswordHash());
	}

	private static String encrypt(String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			byte[] digest = md5.digest();
			return DatatypeConverter.printHexBinary(digest).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
