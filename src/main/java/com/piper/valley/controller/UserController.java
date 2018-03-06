package com.piper.valley.controller;

import com.piper.valley.entity.User;
import com.piper.valley.helpers.Msg;
import com.piper.valley.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public String login() {
		return "user/login";
	}

	@PostMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Msg result = userService.login(username, password);

		if (result == Msg.SUCCESS) {
			//TODO SET SESSIONS OR WHTVR TO MAKE U LOGIN
			model.addAttribute("username", username);
			model.addAttribute("title", username);
			return "user/profile";
		}

		model.addAttribute("message", result.getValue());
		return "user/login";
	}

	@GetMapping(value = "/register")
	public String register(Model model) {
		return "user/register";
	}

	@PostMapping(value = "/register")
	public String register(@ModelAttribute User user, HttpServletRequest request, Model model) {
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		Msg result = userService.register(user, password, confirmPassword);

		if (result == Msg.SUCCESS) {
			//TODO FFS THIS SPRING SHIT IS FUCKING RETARDED IT REDIRECTS USING CONTEXT PATH FFFSFS!#$!#
			return "redirect:http://localhost:8080/"; // temp
		}

		model.addAttribute("message", result.getValue());
		return "user/register";
	}

}
