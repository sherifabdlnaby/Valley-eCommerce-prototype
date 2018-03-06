package com.piper.valley.service;

import com.piper.valley.dao.UserDao;
import com.piper.valley.entity.User;
import com.piper.valley.helpers.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;
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

		if (userDao.insertEntityToDb(new User("tmpId", user, encrypt(password))))
			return Msg.SUCCESS;

		return Msg.UNKNOWN;
	}

	public Msg login(String username, String password) {
		User user = userDao.getEntityByUsername(username);

		// If user not found
		if (user == null) {
			return Msg.USER_NOT_EXIST;
		}

		// If wrong password
		if (!matchPassword(user, password))
			return Msg.WRONG_PASSWORD;

		// All is good
		return Msg.SUCCESS;
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
		return username.length() >= 1; // for testing
	}

	private boolean validatePassword(String password) {
		return password.length() >= 1; // 3shan nengz
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
