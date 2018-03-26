package com.piper.valley.controllers;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.models.domain.Admin;
import com.piper.valley.models.domain.StoreOwner;
import com.piper.valley.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	@RequestMapping("/")
	public String Index(CurrentUser currentUser) {
		return "home/index";
	}
}
