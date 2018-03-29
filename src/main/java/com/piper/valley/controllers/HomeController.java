package com.piper.valley.controllers;

import com.piper.valley.models.repository.UserRepository;
import com.piper.valley.viewmodels.HomePageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HomePageModel homePageModel;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("home/index", homePageModel.create());
	}

}
