package com.piper.valley.controllers;
import com.piper.valley.auth.CurrentUser;
import com.piper.valley.forms.*;
import com.piper.valley.models.domain.*;
import com.piper.valley.models.service.*;
import com.piper.valley.utilities.FlashMessages;
import com.piper.valley.validators.*;
import com.piper.valley.viewmodels.AddProductViewModel;
import com.piper.valley.viewmodels.DemoteAdminViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private UserService userService;

	@Autowired
	private CompanyService companyService;

    @Autowired
    private AddBrandFormValidator brandFormValidator;

    @Autowired
    private AddProductFormValidator addProductFormValidator;

	@Autowired
	private AddCompanyFormValidator addCompanyFormValidator;

	@Autowired
	private PromoteAdminFormValidator promoteAdminFormValidator;

	@Autowired
	private DemoteAdminFormValidator demoteAdminFormValidator;

	@Autowired
	private AddProductViewModel addProductViewModel;

	@Autowired
	private DemoteAdminViewModel demoteAdminViewModel;

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

	@InitBinder("promoteAdminForm")
	public void promoteAdminFormInitBinder(WebDataBinder binder){
		binder.addValidators(promoteAdminFormValidator);
	}

	@InitBinder("demoteAdminForm")
	public void demoteAdminFormInitBinder(WebDataBinder binder){
		binder.addValidators(demoteAdminFormValidator);
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
    public ModelAndView addBrand(@Valid @ModelAttribute("addBrandForm")AddBrandForm addBrandForm, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors())
            return new ModelAndView("admin/addbrand","addBrandForm",addBrandForm);

        Brand brand = brandService.addBrand(addBrandForm);

	    FlashMessages.success("Success! Brand: " + brand.getName() + " Added to the platform!", redirectAttributes);


	    return new ModelAndView("redirect:/admin/addbrand");
    }

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/addcompany", method = RequestMethod.GET)
	public ModelAndView addCompany(@ModelAttribute("addCompanyForm") AddCompanyForm addCompanyForm) {
		return new ModelAndView("admin/addcompany", "addCompanyForm", addCompanyForm);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/addcompany", method = RequestMethod.POST)
	public ModelAndView addCompany(@Valid @ModelAttribute("addCompanyForm")AddCompanyForm addCompanyForm, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors())
			return new ModelAndView("admin/addcompany","addCompanyForm",addCompanyForm);

		Company company = companyService.addCompany(addCompanyForm);

		FlashMessages.success("Success! Company: " + company.getName() + " Added to the platform!", redirectAttributes);

		return new ModelAndView("redirect:/admin/addcompany");
	}

   @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.GET)
    public ModelAndView addProduct(@ModelAttribute("addProductForm") AddProductForm addProductForm) {
	   return new ModelAndView("admin/addproduct", addProductViewModel.create(addProductForm));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid @ModelAttribute("addProductForm") AddProductForm addProductForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
	        return new ModelAndView("admin/addproduct", addProductViewModel.create(addProductForm));

        Product product = productService.addProduct(addProductForm);

	    FlashMessages.success("Success! Product: " + product.getName() + " Added to the platform!", redirectAttributes);

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
    public ModelAndView viewAndAcceptStore(@PathVariable("id") Long id) {
        Optional<Store> store = storeService.getStoreById(id);

        // If the store wasn't found
        if (!store.isPresent()) {
            return new ModelAndView("error/404");
        }

        return new ModelAndView("admin/acceptstore", "store", store);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/acceptstores", method = RequestMethod.POST)
    public ModelAndView acceptStore(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {

        Store store = storeService.acceptStore(id);

	    FlashMessages.success("Success! Store: " + store.getName() + " Added to the platform!", redirectAttributes);

        return new ModelAndView("redirect:/admin/acceptstores");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/rejectstores/{id}", method = RequestMethod.GET)
    public ModelAndView viewAndRejectStore(@PathVariable("id") Long id) {
        Optional<Store> store = storeService.getStoreById(id);

        // If the store wasn't found
        if (!store.isPresent()) {
            return new ModelAndView("error/404");
        }

        return new ModelAndView("admin/rejectstore", "store", store);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/rejectstores", method = RequestMethod.POST)
    public ModelAndView rejectStore(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {

        Store store = storeService.rejectStore(id);

        FlashMessages.success("Success! Store: " + store.getName() + " Rejected from the platform!", redirectAttributes);

        return new ModelAndView("redirect:/admin/acceptstores");
    }
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/promote", method = RequestMethod.GET)
	public ModelAndView promoteAdmin(@ModelAttribute("promoteAdminForm") PromoteAdminForm promoteAdminForm, CurrentUser currentUser) {
		return new ModelAndView("admin/promote");
	}


	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/promote", method = RequestMethod.POST)
	public ModelAndView promoteAdmin(@Valid @ModelAttribute("promoteAdminForm") PromoteAdminForm promoteAdminForm, BindingResult bindingResult, CurrentUser currentUser, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return new ModelAndView("admin/promote");

		User user = userService.promoteAdmin(promoteAdminForm,currentUser.getUser());

		if(user != null) {
			FlashMessages.success("Success! " + user.getName() + " is now an Admin!", redirectAttributes);

		}

		return new ModelAndView("redirect:/admin/promote" );
	}


	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/demote", method = RequestMethod.GET)
	public ModelAndView demoteAdmin(@ModelAttribute("demoteAdminForm") DemoteAdminForm demoteAdminForm, CurrentUser currentUser) {
		return new ModelAndView("admin/demote", demoteAdminViewModel.create(demoteAdminForm, currentUser.getId()));
	}


	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/demote", method = RequestMethod.POST)
	public ModelAndView demoteAdmin(@Valid @ModelAttribute("demoteAdminForm") DemoteAdminForm demoteAdminForm, BindingResult bindingResult, CurrentUser currentUser, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return new ModelAndView("admin/demote", demoteAdminViewModel.create(demoteAdminForm, currentUser.getId()));

		User user = userService.demoteAdmin(demoteAdminForm,currentUser.getUser());

		if(user != null)
			FlashMessages.success("Success! " + user.getName() + " is no longer an admin!", redirectAttributes);

		//TODO SESSION REFRESH

		return new ModelAndView("redirect:/admin/demote" );
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/dashbaord", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		return new ModelAndView("admin/dashboard");
	}

}
