package com.piper.valley.validators;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.models.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddBrandFormValidator implements Validator{
   @Autowired
    private BrandRepository brandRepository;

   @Override
    public boolean supports(Class<?> clazz)
   {
       return clazz.equals(AddBrandForm.class);
   }
    public void validate(Object target,Errors errors)
   {
       AddBrandForm form =(AddBrandForm) target;
       validateName(errors,form);
   }
   private void validateName(Errors errors,AddBrandForm form)
   {
       if(errors.hasFieldErrors("name"))
           return;

       if(brandRepository.findOneByName(form.getName()).isPresent()) {
                errors.rejectValue("name","msg.DuplicateBrandName");
       }
   }
}
