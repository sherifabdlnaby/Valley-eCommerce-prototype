package com.piper.valley.validators;

import com.piper.valley.models.service.AuthService;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddStoreCollaboratorForm;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.User;
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
            errors.rejectValue("username", "msg.UsernameNotFound");
            return;
        }

        Optional<Store> storeOptional = storeService.getStoreById(form.getStoreId());
        if(!storeOptional.isPresent()) {
            errors.rejectValue("storeId", "msg.NotValid");
            return;
        }

        User user = userOptional.get();
        Store store = storeOptional.get();

        CurrentUser currentUser = AuthUtil.getCurrentUser();
        if(!authService.canAccessStore(store, currentUser)) {
            errors.rejectValue("storeId", "msg.NotAuthorized");
            return;
        }
        if(store.getCollaborators().contains(user.getStoreOwner())) {
            errors.rejectValue("username", "msg.DuplicateCollaborator");
            return;
        }
        if(user.getId()==currentUser.getUser().getId()) {
            errors.rejectValue("username", "msg.DuplicateStoreOwner");
            return;
        }
    }
}
