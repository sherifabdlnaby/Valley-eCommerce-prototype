package com.piper.valley.viewmodels;

import com.piper.valley.forms.AddProductForm;
import com.piper.valley.forms.AddStoreProductForm;
import com.piper.valley.models.service.BrandService;
import com.piper.valley.models.service.CompanyService;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
public class AddStoreProductViewModel {
	@Autowired
	private StoreService storeService;

	@Autowired
	private ProductService productService;

	public HashMap<String, Object> create(AddStoreProductForm form, Long StoreOwnerId) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("form"        , form);
		model.put("stores"      , storeService.getAllAcceptedUserStores(StoreOwnerId));
		model.put("products"    , productService.getAllProducts());
		return model;
	}
}
