package com.piper.valley.controller;

import com.piper.valley.entity.User;
import com.piper.valley.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login()
	{
		return "user/login";
	}

	@RequestMapping(value= "/login",method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model)
	{
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		if(userService.login(username, password))
		{
			//TODO SET SESSIONS OR WHTVR TO MAKE U LOGIN
			model.addAttribute("username",username);
			model.addAttribute("title",username);
			return "user/profile";
		}
		else {
			model.addAttribute("message","Wrong Username or password");
			return "user/login";
		}
	}

	@RequestMapping(value = "/register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("title","Register");
		return "user/register";
	}

	//TODO Zaye el login using Services and DAOs
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user) {
		userService.register(user);
		return "user/register";
	}

}
