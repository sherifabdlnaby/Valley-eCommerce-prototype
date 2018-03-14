package com.piper.valley.controllers;

import com.piper.valley.models.domain.Store;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StoreController {
	/////////////////////////*  SERVICES, REPOSITORIES AND VALIDATORS SECTION  */////////////////////////////

	@Autowired
	private StoreService storeService;

//	@Autowired
//	private AddStoreFormValidator addStoreFormValidator;

	//////////////////////////////////*  VALIDATORS BINDING SECTION  *//////////////////////////////////////

//	@InitBinder("addStoreForm")
//	public void addStoreFormInitBinder(WebDataBinder binder) {
//		// This maps the add brand form to our own validator.
//		binder.addValidators(addStoreFormValidator);
//	}

	//@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/store/accept/{id}", method = RequestMethod.GET)
	public ModelAndView acceptStore(@PathVariable("id") long id) {
		Optional<Store> store = storeService.getStoreById(id);

		// If the store wasn't found
		if (!store.isPresent()) {
			return new ModelAndView("error/404");
		}
		System.out.println(store.get().getName());
		return new ModelAndView("store/accept", "store", store);
	}

//	@PreAuthorize("hasAuthority('ADMIN')")
//	@RequestMapping("/store/accept/{id}")
//	public ModelAndView acceptStore()
}
