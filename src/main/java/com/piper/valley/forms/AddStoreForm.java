package com.piper.valley.forms;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Component
public class AddStoreForm {

	@NotEmpty
	@Length(max = 140, min = 3)
	private String name = "";

	private Boolean isPhysical = true;

	private String Address;

	public AddStoreForm() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPhysical() {
		return isPhysical;
	}

	public void setPhysical(Boolean physical) {
		isPhysical = physical;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
}
