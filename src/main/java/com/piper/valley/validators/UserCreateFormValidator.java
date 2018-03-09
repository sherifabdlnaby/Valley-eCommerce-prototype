package com.piper.valley.validators;

import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormValidator implements Validator {
	@Autowired
	private UserRepository userRepository;


	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserCreateForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserCreateForm form = (UserCreateForm) target;
		validatePasswords(errors, form);
		validateUsername(errors, form);
		validateEmail(errors, form);
	}

	private void validatePasswords(Errors errors, UserCreateForm form) {
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			errors.reject("password.no_match", "Passwords do not match");
		}
	}

	private void validateUsername(Errors errors, UserCreateForm form) {
		if (userRepository.findOneByUsername(form.getUsername()).isPresent()) {
			errors.reject("email.exists", "User with this email already exists");
		}
	}

	private void validateEmail(Errors errors, UserCreateForm form) {
		if (userRepository.findOneByEmail(form.getEmail()).isPresent()) {
			errors.reject("email.exists", "User with this email already exists");
		}
	}
}
