package com.piper.valley.service;

import com.piper.valley.dao.UserDao;
import com.piper.valley.entity.User;
import com.piper.valley.helpers.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public Msg register(User user, String password, String confirmPassword) {
		if (!validateEmail(user.getEmail())) {
			return Msg.WRONG_EMAIL;
		}

		if (userDao.emailExists(user.getEmail())) {
			return Msg.EMAIL_EXISTS;
		}

		if (!password.equals(confirmPassword)) {
			return Msg.PASSWORD_CONFIRM;
		}

		if (!validateUsername(user.getUsername())) {
			return Msg.INVALID_USERNAME;
		}

		if (!validatePassword(password)) {
			return Msg.INVALID_PASSWORD;
		}

		if (userDao.getEntityByUsername(user.getUsername()) != null) {
			return Msg.USERNAME_EXISTS;
		}

		if (userDao.insertEntityToDb(new User(
				"tmpId",
				user.getName(),
				user.getUsername(),
				encrypt(password),
				user.getEmail(),
				user.getType())))
			return Msg.SUCCESS;

		return Msg.UNKNOWN;
	}

	public boolean login(String username, String password) {

		//Get User by Username
		User user = userDao.getEntityByUsername(username);

		//Check if user exists and has same password
		if (user != null && matchPassword(user, password))
			return true;

		//User doesn't exist. or password doesn't match.
		return false;
	}

	private boolean validateEmail(String email) {
		return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
				"[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0" +
				"e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(" +
				"?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0" +
				"-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x" +
				"7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	}

	private boolean validateUsername(String username) {
		return username.length() >= 5;
	}

	private boolean validatePassword(String password) {
		return password.length() >= 8;
	}

	private void updatePassword(User user, String newPassword) {
		String passwordHash = encrypt(newPassword);
		user.setPasswordHash(passwordHash);
		userDao.updateEntity(user);
	}

	private boolean matchPassword(User user, String password) {
		String hash = encrypt(password);
		return hash.equals(user.getPasswordHash());
	}

	private String encrypt(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] encodedhash = digest.digest(
				password.getBytes(StandardCharsets.UTF_8));

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < encodedhash.length; i++) {
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
