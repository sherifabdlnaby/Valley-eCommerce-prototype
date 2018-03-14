package com.piper.valley.validators;

import com.piper.valley.forms.AddProductForm;
import com.piper.valley.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddProductFormValidator implements Validator {
	@Autowired
	private ProductRepository productRepository;


	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AddProductForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddProductForm form = (AddProductForm) target;
		validatePrice(errors,form);

	}

	private void validatePrice(Errors errors,AddProductForm form)
	{
		if(form.getPrice()!=""&&Double.parseDouble(form.getPrice())<0)
			errors.rejectValue("price","msg.Negative");
	}






}
