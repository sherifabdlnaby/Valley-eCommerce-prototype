package com.piper.valley.validators;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.DemoteAdminForm;
import com.piper.valley.models.domain.Admin;
import com.piper.valley.models.domain.Role;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.service.UserService;
import com.piper.valley.utilities.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
public class DemoteAdminFormValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(DemoteAdminForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DemoteAdminForm form = (DemoteAdminForm) target;
		//Avoid Querying DB if there is an error already.
		if (errors.hasErrors())
			return;

		Optional<User> userOptional = userService.getUserByUsername(form.getUsername());

		if (!userOptional.isPresent()) {
			errors.rejectValue("username", "msg.UsernameNotFound");
			return;
		}

		User user = userOptional.get();

		if (!user.getRoles().contains(Role.ADMIN)) {
			errors.rejectValue("username", "msg.NotAdmin");
			return;
		}

		CurrentUser currentUser = AuthUtil.getCurrentUser();

		if (user.getId().equals(currentUser.getUser().getId())) {
			errors.rejectValue("username", "msg.PromoteSelf");
		}

		if (user.getAdmin().getSuperior().getId() != currentUser.getId()) {
			//Query your way till head.
			Admin admin = user.getAdmin().getSuperior();
			while(true)
			{
				if(admin.getId() == currentUser.getId())
					break;

				//finished the line.
				else if(Objects.equals(admin.getId(), admin.getSuperior().getId())) {
					errors.rejectValue("username", "msg.NotYourSub");
					break;
				}

				//iterate
				admin = admin.getSuperior();
			}
		}
	}
}
