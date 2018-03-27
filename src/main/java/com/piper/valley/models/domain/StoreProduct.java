package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class StoreProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	private float price;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Store store;

	@OneToMany(mappedBy = "storeProduct", cascade = CascadeType.ALL)
	private List<Order> orders;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
