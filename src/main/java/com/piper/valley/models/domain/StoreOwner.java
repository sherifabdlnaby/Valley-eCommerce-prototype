package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.*;

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

	@ManyToMany(cascade = CascadeType.PERSIST)
	protected Set<Store> collaboratedStores;

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

	public Set<Store> getCollaboratedStores() {
		return collaboratedStores;
	}

	public void setCollaboratedStores(Set<Store> collaboratedStores) {
		this.collaboratedStores = collaboratedStores;
	}
	public boolean addStCollaberatedStore(Store store) {
		if(collaboratedStores == null)
			collaboratedStores = new HashSet<>();
		return collaboratedStores.add(store);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StoreOwner that = (StoreOwner) o;
		return Objects.equals(id, that.id);
	}
}

