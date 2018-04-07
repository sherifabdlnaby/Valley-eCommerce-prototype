package com.piper.valley.controllers.api;

import com.piper.valley.models.common.SearchResult;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class AutoCompleteController {
	@Autowired
	private SearchService searchService;
	@RequestMapping(value = "api/autocomplete/storeproduct/{queryString}")
	public List<StoreProduct> storeProductList(@PathVariable("queryString") String queryString) {
		if(queryString == null || queryString.isEmpty())
			 return null;
		return searchService.storeProductSearch(queryString, 200);
	}

	@RequestMapping(value = "api/autocomplete/store/{queryString}")
	public List<Store> storeList(@PathVariable(value = "q") String queryString) {
		if(queryString == null || queryString.isEmpty())
			return null;
		return searchService.storeSearch(queryString, 100);
	}
}
