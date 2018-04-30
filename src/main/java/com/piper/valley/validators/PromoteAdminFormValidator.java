package com.piper.valley.validators;

import com.piper.valley.models.service.AuthService;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.PromoteAdminForm;
import com.piper.valley.models.enums.Role;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.service.UserService;
import com.piper.valley.utilities.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PromoteAdminFormValidator implements Validator {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PromoteAdminForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PromoteAdminForm form = (PromoteAdminForm) target;

        //Avoid Querying DB if there is an error already.
        if(errors.hasErrors())
            return;

        Optional<User> userOptional = userService.getUserByUsername(form.getUsername());

        if(!userOptional.isPresent()) {
            errors.rejectValue("username", "NotValid");
            return;
        }

        User user = userOptional.get();

        if(user.getRoles().contains(Role.ADMIN)) {
            errors.rejectValue("username", "msg.AlreadyAdmin");
        }

        CurrentUser currentUser = AuthUtil.getCurrentUser();

        if(user.getId().equals(currentUser.getUser().getId())) {
            errors.rejectValue("username", "msg.PromoteSelf");
        }
    }
}
