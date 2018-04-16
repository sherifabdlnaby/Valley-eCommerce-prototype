package com.piper.valley.models.service;

import com.piper.valley.forms.DemoteAdminForm;
import com.piper.valley.forms.PromoteAdminForm;
import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.Admin;
import com.piper.valley.models.domain.OrderStatus;
import com.piper.valley.models.domain.Role;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.repository.OrderRepository;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Optional<User> getUserById(Long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public Optional<User> getUserByUsername(String email) {
		return userRepository.findOneByUsername(email);
	}

	@Override
	public List<User> getAllActiveSubordinates(Long id) {
		return userRepository.findAllByRolesInAndAdmin_Superior_Id(Collections.singletonList(Role.ADMIN), id);
	}

	@Override
	public Integer getUserOrdersCount(Long Id) {
		return orderRepository.countOrdersByUser_IdAndOrderStatus(Id, OrderStatus.UNPROCESSED);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll(new Sort("email"));
	}

	@Override
	public User register(UserCreateForm form) {
		User user = new User();
		user.setName(form.getName());
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));

		//Create Roles List
		Set<Role> roles = new HashSet<>();
		roles.add(Role.USER);

		//Add Roles List to User
		user.setRoles(roles);

		//Save da kolo
		return userRepository.save(user);
	}

	@Override
	public User promoteAdmin(PromoteAdminForm form, User superior) {
		//.get directly as form is validated.
		User user = getUserByUsername(form.getUsername()).get();

		if (!user.getRoles().contains(Role.ADMIN))
			user.addRole(Role.ADMIN);

		//TODO to be removed.
		//corner case for database created admins //TODO remove this when initial admin will be added automatically.
		if(superior.getAdmin() == null) {
			superior.setAdmin(new Admin());
			superior.getAdmin().setUser(superior);
			superior.getAdmin().setSuperior(superior.getAdmin());
			superior = userRepository.save(superior);
		}
		///////////////////////////////////////////////////////

		//First Time Admin (create Admin row in table)
		if (user.getAdmin() == null) {
			user.setAdmin(new Admin());
			user.getAdmin().setUser(user);
			user.getAdmin().setSuperior(superior.getAdmin());
		}

		//save user
		return userRepository.save(user);
	}

	@Override
	public User demoteAdmin(DemoteAdminForm form, User superior) {
		//.get directly as form is validated.
		User user = getUserByUsername(form.getUsername()).get();


		if (user.getRoles().contains(Role.ADMIN))
			user.removeRole(Role.ADMIN);


		//We won't remove relation to preserve the chain. (we can make this better later (agile baby))

		//save user
		return userRepository.save(user);
	}
}
