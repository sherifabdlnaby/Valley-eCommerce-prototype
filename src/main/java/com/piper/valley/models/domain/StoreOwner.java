package com.piper.valley.models.domain;
import javax.persistence.*;
import java.util.List;

@Entity
public class StoreOwner {

	@Id
	protected Long id;

	@MapsId
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	@OneToMany(mappedBy = "storeOwner")
	private List<Store> stores;

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

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

