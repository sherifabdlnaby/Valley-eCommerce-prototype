package com.piper.valley.forms;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class AddOrderForm {

	@NotNull
	private Integer quantity = 1;

	@NotEmpty
	private String address;

	public AddOrderForm() {
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
