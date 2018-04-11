package com.piper.valley.models.domain;

import com.piper.valley.forms.AddOrderForm;

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

	@Column(name = "deliveredDate")
	private Date deliveredDate;

	@Column(name = "quantity", nullable = false)
	private int quantity = 1;

	@Column(name = "address",nullable = false)
	private String address;

	//Processed true = bought by the user.
	//Let's keep this here for backward compatibility (I don't wanna drop the dp soz)
	@Column(name = "processed", nullable = false)
	private boolean processed = false;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus  = OrderStatus.UNPROCESSED;

	@ManyToOne
	private StoreProduct storeProduct;

	public Order() {
	}

	//TODO Index this.
	@ManyToOne
	private User user;

	public Order(User user, StoreProduct storeProduct, AddOrderForm addOrderForm) {
				this.storeProduct = storeProduct;
				this.quantity = addOrderForm.getQuantity();
				this.user = user;
				this.addedDate = new Date();
				this.address=addOrderForm.getAddress();
				this.processedDate = new Date();
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
}
