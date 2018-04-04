package com.piper.valley.controllers;

import com.piper.valley.models.repository.UserRepository;
import com.piper.valley.models.service.SearchService;
import com.piper.valley.viewmodels.HomePageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "q", required = false) String queryString) {
		if(queryString == null || queryString.isEmpty())
			return new ModelAndView("search/index");
		else
			return new ModelAndView("search/result", "searchResult", searchService.generalSearch(queryString));
	}

}
