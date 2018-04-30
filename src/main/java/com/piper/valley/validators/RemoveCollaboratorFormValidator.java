package com.piper.valley.validators;

import com.piper.valley.models.service.AuthService;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddStoreCollaboratorForm;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.models.service.UserService;
import com.piper.valley.utilities.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class RemoveCollaboratorFormValidator implements Validator {
    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AddStoreCollaboratorForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddStoreCollaboratorForm form = (AddStoreCollaboratorForm) target;

        //Avoid Querying DB if there is an error already.
        if(errors.hasErrors())
            return;

        Optional<User> userOptional = userService.getUserByUsername(form.getUsername());
        if(!userOptional.isPresent()) {
            errors.rejectValue("username", "msg.NotValide");
            return;
        }

        Optional<Store> storeOptional = storeService.getStoreById(form.getStoreId());
        if(!storeOptional.isPresent()) {
            errors.rejectValue("storeId", "msg.NotValide");
            return;
        }

        User user = userOptional.get();
        Store store = storeOptional.get();

        CurrentUser currentUser = AuthUtil.getCurrentUser();
        if(!authService.canAccessStore(store, currentUser)) {
            errors.rejectValue("storeId", "Unauthorized!!!!");
            return;
        }
        if(user.getId()==currentUser.getUser().getId()) {
            errors.rejectValue("username", "msg.DuplicateStoreOwner");
            return;
        }
        if(user.getStoreOwner()!=null&&!store.getCollaborators().contains(user.getStoreOwner())) {
            errors.rejectValue("username", "msg.NotCollaborator");
            return;
        }

    }
}
