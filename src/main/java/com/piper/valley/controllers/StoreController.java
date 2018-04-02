package com.piper.valley.controllers;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.AddOrderForm;
import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.forms.AddStoreProductForm;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreProductService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.utilities.AuthUtil;
import com.piper.valley.utilities.FlashMessages;
import com.piper.valley.validators.AddStoreProductFormValidator;
import com.piper.valley.viewmodels.AddOrderViewModel;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
	private AddOrderViewModel addOrderViewModel;

    @Autowired
	private OrderService orderService;

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
    public ModelAndView addStore(@Valid @ModelAttribute("addStoreForm")AddStoreForm addStoreForm, BindingResult bindingResult, CurrentUser currentUser, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors())
            return new ModelAndView("store/add","AddStoreForm",addStoreForm);

        Store store = storeService.add(addStoreForm, currentUser.getUser());

	    //Add Role to Runtime Session
        AuthUtil.addRoleAtRuntime(Role.STORE_OWNER);

	    FlashMessages.info(store.getName() + " added to the platform and awaiting Admin approval!", redirectAttributes);


	    return new ModelAndView("redirect:/store/view/"+store.getId());
    }

	@RequestMapping(value = "/store/view/{id}", method = RequestMethod.GET)
	public ModelAndView viewProduct(@PathVariable("id") Long id, CurrentUser currentUser) {
		Optional<Store> storeTmp = storeService.getStoreById(id);

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
	public ModelAndView addStoreProduct(@Valid @ModelAttribute("addStoreProductForm") AddStoreProductForm addStoreProductForm, BindingResult bindingResult, CurrentUser currentUser, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return new ModelAndView("store/addproduct", addStoreProductViewModel.create(addStoreProductForm, currentUser.getId()));

		StoreProduct storeProduct = storeService.addProductToStore(addStoreProductForm, currentUser.getUser());

		FlashMessages.success("Success! " + storeProduct.getProduct().getName() + " Added to your store!", redirectAttributes);

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

	@RequestMapping(value = "/store/products/{id}/buy", method = RequestMethod.GET)
	public ModelAndView addOrder(@PathVariable("id") Long id, @ModelAttribute("addOrderForm") AddOrderForm addOrderForm) {
		Optional<StoreProduct> product = storeProductService.getProductById(id);
		if (!product.isPresent())
			return new ModelAndView("error/404");
		return new ModelAndView("store/addorder", addOrderViewModel.create(addOrderForm,id));
	}

	@RequestMapping(value = "/store/products/{id}/buy", method = RequestMethod.POST)
	public ModelAndView addOrder(@PathVariable("id") Long id,@Valid @ModelAttribute("addOrderForm")AddOrderForm addOrderForm, BindingResult bindingResult,CurrentUser currentUser,RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors())
			return new ModelAndView("store/addorder",addOrderViewModel.create(addOrderForm,id));
		Optional<StoreProduct> product = storeProductService.getProductById(id);
		Order order = orderService.addOrder(currentUser.getUser(),product.get(),addOrderForm);

		FlashMessages.info(product.get().getProduct().getName() + " added to the Shopping Cart!", redirectAttributes);


		return new ModelAndView("redirect:/");
	}

}
