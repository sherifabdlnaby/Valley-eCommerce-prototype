package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "store")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "ownerId", nullable = false, unique = true)
	private long ownerId;

	@Column(name = "accepted", nullable = false)
	private boolean accepted;

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

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
