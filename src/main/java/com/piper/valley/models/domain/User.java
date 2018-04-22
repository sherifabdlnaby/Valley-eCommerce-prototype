package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(name = "name", nullable = false, unique = false)
	private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user", orphanRemoval = true) //Setting that to true means upon deleting a user, his history is deleted.
    protected List<StoreHistory> history;                                               //Facebook wouldn't be proud.


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "userRoles", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "roles", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private StoreOwner storeOwner;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Admin admin;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
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

	public void addRole(Role role) {
		if(roles == null)
			roles = new HashSet<>();

		roles.add(role);
	}

	public void removeRole(Role role) {
		roles.remove(role);
	}

	public User(Long Id, String username, String email, String passwordHash, String name, Set<Role> roles) {
		this.id = Id;
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
		this.name = name;
		this.roles = roles;
	}

	public User() {
	}

	public User(Long Id) {
		this.id = Id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public StoreOwner getStoreOwner() {
		return storeOwner;
	}

	public void setStoreOwner(StoreOwner storeOwner) {
		this.storeOwner = storeOwner;
	}


	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
