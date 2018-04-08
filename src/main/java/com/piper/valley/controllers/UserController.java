package com.piper.valley.controllers;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.Order;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.models.service.UserService;
import com.piper.valley.utilities.FlashMessages;
import com.piper.valley.validators.UserCreateFormValidator;
import com.piper.valley.viewmodels.ShoppingCartModel;
import com.piper.valley.viewmodels.StoreOwnerDashboardViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
	/////////////////////////*  SERVICES, REPOSITORIES AND VALIDATORS SECTION  */////////////////////////////
	@Autowired
	private UserService userService;

	@Autowired
	private UserCreateFormValidator userCreateFormValidator;

	@Autowired
	private StoreOwnerDashboardViewModel storeOwnerDashboardViewModel;


	@Autowired
	private ShoppingCartModel shoppingCartModel;


	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////*  VALIDATORS BINDING SECTION  *//////////////////////////////////////


	@InitBinder("registerForm")
	public void registerFormInitBinder(WebDataBinder binder) {
		binder.addValidators(userCreateFormValidator);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////*  CONTROLLER ACTION  *///////////////////////////////////////////

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
		return new ModelAndView("user/login", "error", error.isPresent() ? error : null);
	}


	//@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #pathVariable)")
	//@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("registerForm") UserCreateForm registerForm) {
		return new ModelAndView("user/register", "registerForm", registerForm);
	}

	//@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute("registerForm") UserCreateForm registerForm, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return new ModelAndView("user/register", "registerForm", registerForm);

		//Save to DB
		userService.register(registerForm);

		//Login
		try {
			request.changeSessionId();
			request.login(registerForm.getUsername(), registerForm.getPassword());
		} catch (ServletException e) {
			e.printStackTrace();
		}

		FlashMessages.info("Welcome to Valley! Thank you for registering, we're happy for having you!", redirectAttributes);


		return new ModelAndView("redirect:/");
	}

	@PreAuthorize("hasAuthority('STORE_OWNER')")
	@RequestMapping(value = "/user/storeowner/dashbaord", method = RequestMethod.GET)
	public ModelAndView addStoreProduct(CurrentUser currentUser) {
		return new ModelAndView("store/dashboard", storeOwnerDashboardViewModel.create(currentUser.getId()));
	}

	@RequestMapping(value = "/user/shoppingcart", method = RequestMethod.GET)
	public ModelAndView shoppingCart(CurrentUser currentUser) {
		return new ModelAndView("user/shoppingcart",shoppingCartModel.create(currentUser.getId()));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
}
