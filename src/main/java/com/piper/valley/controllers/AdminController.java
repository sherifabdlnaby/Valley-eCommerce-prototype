package com.piper.valley.controllers;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.forms.AddCompanyForm;
import com.piper.valley.forms.AddProductForm;
import com.piper.valley.models.domain.Product;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.service.BrandService;
import com.piper.valley.models.service.CompanyService;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.validators.AddBrandFormValidator;
import com.piper.valley.validators.AddCompanyFormValidator;
import com.piper.valley.validators.AddProductFormValidator;
import com.piper.valley.viewmodels.AddProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
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
	private CompanyService companyService;

    @Autowired
    private AddBrandFormValidator brandFormValidator;

    @Autowired
    private AddProductFormValidator addProductFormValidator;

	@Autowired
	private AddCompanyFormValidator addCompanyFormValidator;

	@Autowired
	private AddProductViewModel addProductViewModel;

	//	@Autowired
//	private AddStoreFormValidator addStoreFormValidator;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////*  VALIDATORS BINDING SECTION  *//////////////////////////////////////

    @InitBinder("addBrandForm")
    public void addBrandFormInitBinder(WebDataBinder binder) {
        binder.addValidators(brandFormValidator);
    }

    @InitBinder("addProductForm")
    public void AddProductFormInitBinder(WebDataBinder binder){
        binder.addValidators(addProductFormValidator);
    }

	@InitBinder("addCompanyForm")
	public void AddCompanyFormInitBinder(WebDataBinder binder){
		binder.addValidators(addCompanyFormValidator);
	}


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
	@RequestMapping(value = "/admin/addcompany", method = RequestMethod.GET)
	public ModelAndView addCompany(@ModelAttribute("addCompanyForm") AddCompanyForm addCompanyForm) {
		return new ModelAndView("admin/addcompany", "addCompanyForm", addCompanyForm);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/addcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("addCompanyForm")AddCompanyForm addCompanyForm, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ModelAndView("admin/addcompany","addCompanyForm",addCompanyForm);
		companyService.addCompany(addCompanyForm);
		return new ModelAndView("redirect:/");
	}

   @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.GET)
    public ModelAndView addProduct(@ModelAttribute("addProductForm") AddProductForm addProductForm) {
	   return new ModelAndView("admin/addproduct", addProductViewModel.create(addProductForm));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid @ModelAttribute("addProductForm") AddProductForm addProductForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
	        return new ModelAndView("admin/addproduct", addProductViewModel.create(addProductForm));

        Product product = productService.addProduct(addProductForm);

        return new ModelAndView("redirect:/product/view/"+product.getId());
    }

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/acceptstores", method = RequestMethod.GET)
	public ModelAndView viewAppliedStore() {
		Collection<Store> stores = storeService.getAllAppliedStores();
		return new ModelAndView("admin/acceptStoreList", "stores", stores);
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/acceptstores/{id}", method = RequestMethod.GET)
    public ModelAndView viewAndAcceptStore(@PathVariable("id") long id) {
        Optional<Store> store = storeService.getStoreById(id);

        // If the store wasn't found
        if (!store.isPresent()) {
            return new ModelAndView("error/404");
        }

        return new ModelAndView("admin/acceptstore", "store", store);
    }

    //TODO POST REQUEST IN URL AR U FUKING KIDDING ME ?
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/acceptstores/{id}", method = RequestMethod.POST)
    public ModelAndView acceptStore(@PathVariable("id") long id) {
        storeService.acceptStore(id);
        return new ModelAndView("redirect:/admin/acceptstores"); // Temporary
    }

}
