package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.PhysicalProduct;
import com.piper.valley.models.domain.StoreProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StoreProductViewModel {

	//Check if Product is Physical/Virtual
	public HashMap<String, Object> create(StoreProduct storeProduct) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("product"  , storeProduct);

		//We should use attributes instead of passing a whole obj, but mehh... msh 3ayz a8yar el view bt3at Refaie.
		PhysicalProduct physicalProduct = null;
		if(storeProduct.getProduct() instanceof PhysicalProduct)
			physicalProduct =  (PhysicalProduct) storeProduct.getProduct();

		model.put("physicalproduct"  , physicalProduct);
		return model;
	}

}
