package com.piper.valley.validators;

import com.piper.valley.auth.AuthService;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddStoreCollaboratorForm;
import com.piper.valley.forms.AddStoreProductForm;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.models.service.UserService;
import com.piper.valley.utilities.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class AddCollaboratorFormValidator implements Validator {
    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AddStoreProductForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddStoreCollaboratorForm form = (AddStoreCollaboratorForm) target;

        //Avoid Querying DB if there is an error already.
        if(errors.hasErrors())
            return;

        Optional<User> userOptional = userService.getUserById(form.getCollaboratorId());
        if(!userOptional.isPresent()) {
            errors.rejectValue("userId", "NotValid");
            return;
        }

        Optional<Store> storeOptional = storeService.getStoreById(form.getStoreId());
        if(!storeOptional.isPresent()) {
            errors.rejectValue("storeId", "NotValid");
            return;
        }

        User user = userOptional.get();
        Store store = storeOptional.get();



    }
}
