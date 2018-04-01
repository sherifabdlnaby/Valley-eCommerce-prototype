package com.piper.valley.models.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "addedDate", nullable = false)
	private Date addedDate;

	@Column(name = "processedDate")
	private Date processedDate;

	@Column(name = "quantity", nullable = false)
	private int quantity = 1;

	//Processed true = bought by the user.
	@Column(name = "processed", nullable = false)
	private boolean processed = false;

	@ManyToOne
	private StoreProduct storeProduct;

	//TODO Index this.
	@ManyToOne
	private User user;

	public StoreProduct getStoreProduct() {
		return storeProduct;
	}

	public void setStoreProduct(StoreProduct storeProduct) {
		this.storeProduct = storeProduct;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}


	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order(User user, StoreProduct storeProduct, int quantity) {
		this.storeProduct = storeProduct;
		this.quantity = quantity;
		this.user = user;
		this.addedDate = new Date();
	}
}
