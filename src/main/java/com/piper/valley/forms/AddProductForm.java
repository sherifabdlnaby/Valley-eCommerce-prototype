package com.piper.valley.forms;

import org.hibernate.validator.constraints.NotEmpty;


public class AddProductForm {

	@NotEmpty
	private String name = "";

	@NotEmpty
	private String brand = "";

	@NotEmpty
	String  price ;



	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "AddProductForm{" +
				"name='" + name + '\'' +
				", brand='" + brand + '\'' +
				", price=" + price +
				'}';
	}


}
