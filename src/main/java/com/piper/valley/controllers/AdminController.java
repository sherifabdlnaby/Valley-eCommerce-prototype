package com.piper.valley.controllers;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.forms.AddProductForm;
import com.piper.valley.forms.UserCreateForm;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.service.BrandService;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.validators.AddBrandFormValidator;
import com.piper.valley.validators.AddProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdminController {
    /////////////////////////*  SERVICES, REPOSITORIES AND VALIDATORS SECTION  */////////////////////////////
    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

	@Autowired
	private StoreService storeService;

    @Autowired
    private AddBrandFormValidator brandFormValidator;

    @Autowired
    private AddProductFormValidator addProductFormValidator;

	//	@Autowired
//	private AddStoreFormValidator addStoreFormValidator;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////*  VALIDATORS BINDING SECTION  *//////////////////////////////////////

    @InitBinder("addBrandForm")
    public void addBrandFormInitBinder(WebDataBinder binder) {
        binder.addValidators(brandFormValidator); //This maps the add brand form to our own validator.
    }

    @InitBinder("addProductForm")
    public void AddProductFormInitBinder(WebDataBinder binder)
    {
        binder.addValidators(addProductFormValidator);
    }

	//	@InitBinder("addStoreForm")
//	public void addStoreFormInitBinder(WebDataBinder binder) {
//		// This maps the add brand form to our own validator.
//		binder.addValidators(addStoreFormValidator);
//	}

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////*  CONTROLLER ACTION  *///////////////////////////////////////////

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addbrand", method = RequestMethod.GET)
    public ModelAndView addBrand(@ModelAttribute("addBrandForm") AddBrandForm addBrandForm) {
        return new ModelAndView("admin/addbrand", "addBrandForm", addBrandForm);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addbrand", method = RequestMethod.POST)
    public ModelAndView addBrand(@Valid @ModelAttribute("addBrandForm")AddBrandForm addBrandForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return new ModelAndView("admin/addbrand","addBrandForm",addBrandForm);
        brandService.addBrand(addBrandForm);
        return new ModelAndView("redirect:/");
    }

   @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.GET)
    public ModelAndView addProduct(@ModelAttribute("addProductForm") AddProductForm addProductForm) {
        return new ModelAndView("admin/addproduct", "addProductForm", addProductForm);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid @ModelAttribute("addProductForm") AddProductForm addProductForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ModelAndView("admin/addproduct", "addProductForm", addProductForm);

        productService.addProduct(addProductForm);

        return new ModelAndView("redirect:/");
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/acceptStore/{id}", method = RequestMethod.GET)
    public ModelAndView viewAndAcceptStore(@PathVariable("id") long id) {
        Optional<Store> store = storeService.getStoreById(id);

        // If the store wasn't found
        if (!store.isPresent()) {
            return new ModelAndView("error/404");
        }

        return new ModelAndView("store/accept", "store", store);
    }

    //	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/acceptStore/{id}", method = RequestMethod.POST)
    public ModelAndView acceptStore(@PathVariable("id") long id) {
        storeService.acceptStore(id);
        return new ModelAndView("redirect:/"); // Temporary
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////*  CONTROLLER ACTION  *///////////////////////////////////////////






}
