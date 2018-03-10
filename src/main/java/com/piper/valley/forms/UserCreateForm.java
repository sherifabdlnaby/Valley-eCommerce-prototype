package com.piper.valley.forms;

import org.hibernate.validator.constraints.NotEmpty;


public class UserCreateForm {

	@NotEmpty
	private String name = "";

	@NotEmpty
	private String username = "";

	@NotEmpty
	private String email = "";

	@NotEmpty
	private String password = "";
	@NotEmpty

	private String passwordConfirm = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@Override
	public String toString() {
		return "UserCreateForm{" +
				"email='" + email.replaceFirst("@.+", "@***") + '\'' +
				", password=***" + '\'' +
				", passwordConfirm=***" +
				'}';
	}

}
