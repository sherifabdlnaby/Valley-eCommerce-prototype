package com.piper.valley.models.domain;
import javax.persistence.*;


@Entity
public class Admin {
	@Id
	private Long id;

	@MapsId
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}


