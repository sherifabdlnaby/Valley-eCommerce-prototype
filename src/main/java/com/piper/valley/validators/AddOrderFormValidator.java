package com.piper.valley.validators;

import com.piper.valley.forms.AddOrderForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddOrderFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AddOrderForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddOrderForm form = (AddOrderForm) target;
		validateQuantity(errors,form);
		validateAddress(errors,form);
	}

	private void validateQuantity(Errors errors,AddOrderForm form)
	{
		if(form.getQuantity() == null)
		{
			errors.rejectValue("quantity","NotEmpty");
		}
		if(form.getQuantity()<=0)
		{
			errors.rejectValue("quantity","msg.QuantityNegativeOrZero");
		}
	}

	private void validateAddress(Errors errors,AddOrderForm form)
	{
		if(form.getAddress().length() < 2 || form.getAddress().length() > 200)
			errors.rejectValue("address","msg.AddressSizeRange");
	}

}
