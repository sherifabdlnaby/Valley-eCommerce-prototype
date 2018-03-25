package com.piper.valley.forms;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class AddProductForm {

	@NotEmpty
	private String name = "";

	@NotEmpty
	private String brand = "";

	@NotNull
	@Min(0)
	private Float averagePrice;

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Float getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Float averagePrice) {
		this.averagePrice = averagePrice;
	}

	public void setName(String name) {
		this.name = name;
	}

}
