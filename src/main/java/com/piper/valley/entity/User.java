package com.piper.valley.entity;

public class User {
	private String id;
	private String name;
	private String username;
	private String passwordHash;
	private String email;

	public User() {

	}

	public User(String id, String name, String username, String password, String email) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.passwordHash = password;
		this.email = email;
	}

	public User(String name, String password) {
		this.name = name;
		this.passwordHash = password;
	}

	@Override
	public boolean equals(Object obj) {
		return ((this.username.equals(((User) obj).username)
				&& this.passwordHash.equals(((User) obj).passwordHash)) || (this.email.equals(((User) obj).email) && this.passwordHash.equals(((User) obj).passwordHash)));
	}


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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String password) {
		this.passwordHash = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
