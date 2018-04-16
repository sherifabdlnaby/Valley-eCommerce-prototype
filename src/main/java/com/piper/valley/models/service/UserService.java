package com.piper.valley.models.service;

import com.piper.valley.forms.DemoteAdminForm;
import com.piper.valley.forms.PromoteAdminForm;
import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

	Optional<User> getUserById(Long id);

	Optional<User> getUserByEmail(String email);

	Optional<User> getUserByUsername(String email);

	List getAllActiveSubordinates(Long id);

	Integer getUserOrdersCount(Long Id);

	Collection<User> getAllUsers();

	User register(UserCreateForm form);

	User promoteAdmin(PromoteAdminForm form, User superior);

	User demoteAdmin(DemoteAdminForm form, User superior);

}
