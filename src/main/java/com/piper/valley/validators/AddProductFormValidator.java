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
		if(form.getPhysicalProduct())
			validatePhysical(errors, form);
		else
			validateVirtual(errors, form);
	}

	private void validatePhysical(Errors errors,AddProductForm form)
	{
		if(form.getLength() == null)
			errors.rejectValue("length","NotEmpty");

		if(form.getWidth() == null)
			errors.rejectValue("width","NotEmpty");

		if(form.getHeight() == null)
			errors.rejectValue("height","NotEmpty");

		if(form.getWeight() == null)
			errors.rejectValue("weight","NotEmpty");
	}

	private void validateVirtual(Errors errors,AddProductForm form)
	{
		if(form.getSerial() == null || form.getSerial().equals(""))
			errors.rejectValue("serial","NotEmpty");
		if(form.getSerial().length() < 2 || form.getSerial().length() > 60)
			errors.rejectValue("serial","msg.SerialSizeRange");
	}

}
