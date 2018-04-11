package com.piper.valley.validators;

import com.piper.valley.auth.AuthService;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddStoreCollaboratorForm;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.models.service.UserService;
import com.piper.valley.utilities.AuthUtil;
import com.piper.valley.utilities.FlashMessages;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
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
        if(store.getCollaborators().contains(user.getStoreOwner())) {
            errors.rejectValue("username", "msg.DuplicateCollaborator");
            return;
        }
        if(store.getStoreOwner().getUser().getId().equals(currentUser.getUser().getId()))
            errors.rejectValue("username","msg.DuplicateStoreOwner");
    }
}
