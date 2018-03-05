package com.piper.valley.entity;

public class User  {
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;

    public User(){

    }

    public User(String id,String name, String username, String password, String email) {
        this.id=id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

    @Override
    public boolean equals(Object obj) {
        return ((this.username.equals(((User) obj).username)
                && this.password.equals(((User) obj).password))||(this.email.equals(((User) obj).email)&&this.password.equals(((User) obj).password)));
    }


    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
