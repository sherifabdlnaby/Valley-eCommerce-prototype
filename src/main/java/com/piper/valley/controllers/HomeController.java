package com.piper.valley.controllers;

import com.piper.valley.models.repository.UserRepository;
import com.piper.valley.viewmodels.HomePageViewModel;
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
	private HomePageViewModel homePageModel;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("home/index", homePageModel.create());
	}

}
