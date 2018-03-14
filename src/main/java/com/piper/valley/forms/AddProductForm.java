package com.piper.valley.forms;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class AddProductForm {

	@NotEmpty
	private String name = "";

	@NotEmpty
	private String brand = "";

	@NotNull
	@Min(0)
	private Float price ;

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

}
