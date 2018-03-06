package com.piper.valley.controller;

import com.piper.valley.auth.Authenticator;
import com.piper.valley.entity.User;
import com.piper.valley.helpers.Msg;
import com.piper.valley.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Authenticator authenticator;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (userService.login(username, password) ) {
			//Save New Session
			//TODO userID
			authenticator.saveAuth(1337, username);
			model.addAttribute("username", username);
			model.addAttribute("title", username);
			return "redirect:/";
		} else {
			model.addAttribute("message", "Wrong Username or password");
			return "user/login";
		}
	}

	@RequestMapping(value = "/register")
	public String register(Model model) {
		return "user/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user, HttpServletRequest request, Model model) {
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		Msg result = userService.register(user, password, confirmPassword);

		if (result == Msg.SUCCESS) {
			//SAVE SESSION
			//TODO userID
			authenticator.saveAuth(1337,user.getUsername());
			return "redirect:/user/view/"+ user.getUsername();
		}

		model.addAttribute("message", result.getValue());
		return "user/register";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		//Delete Session
		authenticator.removeAuth();
		return "redirect:/";
	}

	@RequestMapping(value = "/view/{username}")
	public String view(@PathVariable("username") String username) {
		return "user/profile";
	}

}
