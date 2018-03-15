package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(name = "name", nullable = false, unique = false)
	private String name;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	/*@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "store")
	private Set<Store> stores = new HashSet<>();
*/
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	//TODO add more user attributes

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*public Set<Store> getStores() {
		return this.stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
*/	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email.replaceFirst("@.*", "@***") +
				", passwordHash='" + passwordHash.substring(0, 10) +
				", role=" + role +
				'}';
	}
}
