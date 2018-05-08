package com.piper.valley;
import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.Admin;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.enums.Role;
import com.piper.valley.models.repository.UserRepository;
import com.piper.valley.models.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ValleyApplication.class)
public class UserServiceTests {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	User testUser;

	@Before
	public void initialize(){
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(Role.USER);
		roleSet.add(Role.ADMIN);
		testUser = new User(1337L, "sherifabdlnaby", "sherif@email.com", "HA$H3DPA$$W0RD", "Sherif", roleSet);
		testUser.setAdmin(new Admin());
		testUser.getAdmin().setUser(testUser);
		testUser = userRepository.saveAndFlush(testUser);
	}

	@After
	//Delete what we added.
	public void destruct(){
		userRepository.delete(testUser);
	}


	@Test
	public void testRegisterViaForm() {

		UserCreateForm userCreateForm = new UserCreateForm();
		userCreateForm.setName("sherif");
		userCreateForm.setUsername("testregister");
		userCreateForm.setEmail("email@mail.com");
		userCreateForm.setPassword("123456");
		userCreateForm.setPasswordConfirm("123456");

		userService.register(userCreateForm);

		//userRepository.saveAndFlush()
		User user = userRepository.findOneByUsername("testregister").get();

		assert user != null;
	}

	@Test
	public void testNoRegisterDuplicate() {

		UserCreateForm userCreateForm = new UserCreateForm();
		userCreateForm.setName("sherif");
		userCreateForm.setUsername("sherifabdlnaby");
		userCreateForm.setEmail("email@mail.com");
		userCreateForm.setPassword("123456");
		userCreateForm.setPasswordConfirm("123456");

		try {
			userService.register(userCreateForm);
			userService.register(userCreateForm);
			assert false;
		}
		catch (DataIntegrityViolationException e){
			return;
		}
	}

}
