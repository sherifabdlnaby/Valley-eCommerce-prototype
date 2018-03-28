package com.piper.valley.controllers;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.forms.AddStoreProductForm;
import com.piper.valley.models.domain.Role;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.StoreStatus;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreProductService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.utilities.AuthUtil;
import com.piper.valley.validators.AddStoreProductFormValidator;
import com.piper.valley.viewmodels.AddStoreProductViewModel;
import com.piper.valley.viewmodels.StoreOwnerDashboardViewModel;
import com.piper.valley.viewmodels.StoreProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class StoreController {
    /////////////////////////*  SERVICES, REPOSITORIES AND VALIDATORS SECTION  */////////////////////////////
    @Autowired
    private StoreService storeService;

	@Autowired
	private ProductService productService;

	@Autowired
	private StoreProductService storeProductService;

	@Autowired
	private AddStoreProductViewModel addStoreProductViewModel;

	@Autowired
	private StoreProductViewModel storeProductViewModel;

    @Autowired
    private AddStoreProductFormValidator addStoreProductFormValidator;

    @Autowired
    private StoreOwnerDashboardViewModel storeOwnerDashboardViewModel;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////*  VALIDATORS BINDING SECTION  *//////////////////////////////////////

    @InitBinder("addStoreProductForm")
    public void addBrandFormInitBinder(WebDataBinder binder) {
        binder.addValidators(addStoreProductFormValidator);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////*  CONTROLLER ACTION  *///////////////////////////////////////////

    @RequestMapping(value = "/store/add", method = RequestMethod.GET)
    public ModelAndView addStore(@ModelAttribute("addStoreForm") AddStoreForm addStoreForm) {
        return new ModelAndView("store/add", "addStoreForm", addStoreForm);
    }

    @RequestMapping(value = "/store/add", method = RequestMethod.POST)
    public ModelAndView addStore(@Valid @ModelAttribute("addStoreForm")AddStoreForm addStoreForm, BindingResult bindingResult, CurrentUser currentUser)
    {
        if(bindingResult.hasErrors())
            return new ModelAndView("store/add","AddStoreForm",addStoreForm);

        Store store = storeService.add(addStoreForm, currentUser.getUser());

		if(true)
		    //Add Role to Runtime Session
            AuthUtil.addRoleAtRuntime(Role.STORE_OWNER);

	    return new ModelAndView("redirect:/store/view/"+store.getId());
    }

	@RequestMapping(value = "/store/view/{id}", method = RequestMethod.GET)
	public ModelAndView viewProduct(@PathVariable("id") Long id, CurrentUser currentUser) {
		Optional<Store> storeTmp = storeService.getStoreById(id);

		//TODO send 404 status code not just render error.
		if (!storeTmp.isPresent())
			return new ModelAndView("error/404");

		Store store = storeTmp.get();
		//TODO use custom authorizor instead of hardcoding it here (lateR)
		if(store.getStatus() == StoreStatus.ACCEPTED || currentUser.getRole().contains(Role.ADMIN) || store.getStoreOwner().getId() == currentUser.getId())
			return new ModelAndView("store/view", "store", store);
		else
			return new ModelAndView("error/403");
	}

	@PreAuthorize("hasAuthority('STORE_OWNER')")
	@RequestMapping(value = "/store/addproduct", method = RequestMethod.GET)
	public ModelAndView addStoreProduct(@ModelAttribute("addStoreProductForm") AddStoreProductForm addStoreProductForm, CurrentUser currentUser) {
		return new ModelAndView("store/addproduct", addStoreProductViewModel.create(addStoreProductForm, currentUser.getId()));
	}


	@PreAuthorize("hasAuthority('STORE_OWNER')")
	@RequestMapping(value = "/store/addproduct", method = RequestMethod.POST)
	public ModelAndView addStoreProduct(@Valid @ModelAttribute("addStoreProductForm") AddStoreProductForm addStoreProductForm, BindingResult bindingResult, CurrentUser currentUser) {
		if(bindingResult.hasErrors())
			return new ModelAndView("store/addproduct", addStoreProductViewModel.create(addStoreProductForm, currentUser.getId()));

		StoreProduct storeProduct = storeService.addProductToStore(addStoreProductForm, currentUser.getUser());

		//TODO Flash message Successful!
		return new ModelAndView("redirect:/store/products/"+storeProduct.getId());
	}

    @PreAuthorize("hasAuthority('STORE_OWNER')")
    @RequestMapping(value = "/store/statistics", method = RequestMethod.GET)
    public ModelAndView viewStatistics(CurrentUser currentUser) {
        return new ModelAndView("store/statistics", storeOwnerDashboardViewModel.create(currentUser.getId()));
    }

	@RequestMapping(value = "/store/products/{id}", method = RequestMethod.GET)
	public ModelAndView viewStoreProduct(@PathVariable("id") Long id) {

		Optional<StoreProduct> product = storeProductService.getProductById(id);
		if (!product.isPresent())
			return new ModelAndView("error/404");

		StoreProduct storeProduct = product.get();

        storeProductService.incrementViews(storeProduct);
        productService.incrementViews(storeProduct.getProduct());

		return new ModelAndView("store/storeprodcutview", storeProductViewModel.create(storeProduct));
	}

}
