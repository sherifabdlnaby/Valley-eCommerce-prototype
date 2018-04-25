package com.piper.valley.validators;

import com.piper.valley.auth.AuthService;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.UndoHistoryForm;
import com.piper.valley.models.domain.StoreHistory;
import com.piper.valley.models.domain.StoreHistoryStatus;
import com.piper.valley.models.service.StoreHistoryService;
import com.piper.valley.utilities.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UndoHistoryValidator implements Validator {

	@Autowired
	StoreHistoryService storeHistoryService;

	@Autowired
	AuthService authService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UndoHistoryForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UndoHistoryForm undoHistoryForm = (UndoHistoryForm) target;

		//Avoid Querying DB if there is an error already.
		if(errors.hasErrors())
			return;

		Optional<StoreHistory> storeHistoryOptional = storeHistoryService.getById(undoHistoryForm.getId());

		if(!storeHistoryOptional.isPresent()) {
			errors.rejectValue("Id", "NotValid", "History doesn't exist!");
			return;
		}

		StoreHistory storeHistory = storeHistoryOptional.get();
		if(storeHistory.getStatus() != StoreHistoryStatus.UNDOABLE){
			errors.rejectValue("Id", "NotValid");
			return;
		}


		CurrentUser currentUser = AuthUtil.getCurrentUser();
		if(!authService.canAccessStore(storeHistory.getStore(), currentUser)){
			errors.rejectValue("Id", "msg.NotAuthorized", "You're not Authorized to do that!");
		}

	}
}
