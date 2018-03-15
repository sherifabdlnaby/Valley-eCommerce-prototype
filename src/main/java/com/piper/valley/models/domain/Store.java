package com.piper.valley.models.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "store")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "accepted", nullable = false)
	private boolean accepted;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)*/
	@Column(name = "owner_id", nullable = false)
	private long owner_id;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "phone", nullable =false, unique = true)
	private String phone;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	/*public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/

	public long getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
