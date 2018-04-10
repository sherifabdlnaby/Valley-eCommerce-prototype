package com.piper.valley.validators;

import com.piper.valley.auth.AuthService;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddStoreProductForm;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.utilities.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class AddStoreProductFormValidator implements Validator {
	@Autowired
	private ProductService productService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private AuthService authService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AddStoreProductForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddStoreProductForm form = (AddStoreProductForm) target;

		//Avoid Querying DB if there is an error already.
		if(errors.hasErrors())
			return;

		Optional<Product> productOptional = productService.getProductById(form.getProductId());
		if(!productOptional.isPresent()) {
			errors.rejectValue("productId", "NotValid");
			return;
		}

		Optional<Store> storeOptional = storeService.getStoreById(form.getStoreId());
		if(!storeOptional.isPresent()) {
			errors.rejectValue("storeId", "NotValid");
			return;
		}

		Product product = productOptional.get();
		Store store = storeOptional.get();

		CurrentUser currentUser = AuthUtil.getCurrentUser();
		if(!authService.canAccessStore(store, currentUser))
			errors.rejectValue("storeId","Unauthorized!!!!");

		if(store.getStatus() != StoreStatus.ACCEPTED)
			errors.rejectValue("storeId","msg.AcceptedStore");

		//Check Type
		if(store instanceof VirtualStore && product instanceof PhysicalProduct)
			errors.rejectValue("productId", "msg.VirtualStoreProducts");

	}
}
