package com.piper.valley.models.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
	@Id
	private Long id;

	@MapsId
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	@OneToMany(mappedBy = "shoppingCart", orphanRemoval = false)
	private List<Order> orders;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
