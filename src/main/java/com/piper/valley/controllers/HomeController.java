package com.piper.valley.controllers;

import com.piper.valley.auth.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String Index(CurrentUser currentUser) {
		/*
		* 	FlashMessages.danger("test test 1337", redirectAttributes);
		FlashMessages.warning("test warning", redirectAttributes);
		FlashMessages.info("test test 1337", redirectAttributes);
		FlashMessages.success("SUCCESS", redirectAttributes);
		*/
		return "home/index";
	}
}
