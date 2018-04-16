package com.piper.valley.models.domain;
import javax.persistence.*;
import java.util.List;


@Entity
public class Admin {
	@Id
	private Long id;

	@MapsId
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	@ManyToOne
	private Admin superior;

	@OneToMany(mappedBy = "superior")
	private List<Admin> subordinates;

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

	public Admin getSuperior() {
		return superior;
	}

	public void setSuperior(Admin superior) {
		this.superior = superior;
	}

	public List<Admin> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Admin> subordinates) {
		this.subordinates = subordinates;
	}
}


