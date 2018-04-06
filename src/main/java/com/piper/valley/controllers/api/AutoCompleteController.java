package com.piper.valley.controllers.api;

import com.piper.valley.models.common.SearchResult;
import com.piper.valley.models.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class AutoCompleteController {
	@Autowired
	private SearchService searchService;
	@RequestMapping(value = "api/search", method = RequestMethod.GET)
	public SearchResult index(@RequestParam(value = "q", required = false) String queryString) {
		if(queryString == null || queryString.isEmpty())
			 return null;

		SearchResult searchResult = searchService.autoCompleteSearch(queryString);

		return searchResult;
	}
}
