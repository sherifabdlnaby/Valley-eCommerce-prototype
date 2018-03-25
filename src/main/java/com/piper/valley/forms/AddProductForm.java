package com.piper.valley.forms;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Component
public class AddProductForm {

	@NotEmpty
	private String name = "";

	@NotNull
	private Integer companyId;

	@NotNull
	private Integer brandId;

	@NotNull
	@Min(0)
	private Float averagePrice;

	public String getName() {
		return name;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;


	}
}
