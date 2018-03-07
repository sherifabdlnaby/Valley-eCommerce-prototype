package com.piper.valley.controllers;

import com.piper.valley.models.entity.User;
import com.piper.valley.models.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (userService.login(username, password)) {
			//TODO SET SESSIONS OR WHTVR TO MAKE U LOGIN
			model.addAttribute("username", username);
			model.addAttribute("title", username);
			return "user/profile";
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
		String confirmPassword = request.getParameter("confirmPassword");
		if (userService.register(user, confirmPassword)) {
			//TODO FFS THIS SPRING SHIT IS FUCKING RETARDED IT REDIRECTS USING CONTEXT PATH FFFSFS!#$!#
			return "redirect: .../";
		}
		model.addAttribute("message", "Failed to Register, hanl2y tare2a ngeb howa failed leh bezabt ba3den");
		return "user/register";
	}

}
