package com.piper.valley.viewmodels;

import com.piper.valley.forms.AddProductForm;
import com.piper.valley.models.domain.Brand;
import com.piper.valley.models.domain.Company;
import com.piper.valley.models.service.BrandService;
import com.piper.valley.models.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;

@Component
public class AddProductViewModel {
	@Autowired
	BrandService brandService;

	@Autowired
	CompanyService companyService;

	//Query for Brands/Companies using the view model!.
	public HashMap<String, Object> create(AddProductForm form) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("addProductForm"  , form);
		model.put("brands"          , brandService.getAllBrands());
		model.put("companies"       , companyService.getAllCompanies());
		return model;
	}
}
