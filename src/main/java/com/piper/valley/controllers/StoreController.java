package com.piper.valley.controllers;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.forms.AddCompanyForm;
import com.piper.valley.forms.AddProductForm;
import com.piper.valley.forms.AddStoreForm;
import com.piper.valley.models.domain.Product;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.service.BrandService;
import com.piper.valley.models.service.CompanyService;
import com.piper.valley.models.service.ProductService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.validators.AddBrandFormValidator;
import com.piper.valley.validators.AddCompanyFormValidator;
import com.piper.valley.validators.AddProductFormValidator;
import com.piper.valley.validators.AddStoreFormValidator;
import com.piper.valley.viewmodels.AddProductViewModel;
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
    private AddStoreFormValidator addStoreFormValidator;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////*  VALIDATORS BINDING SECTION  *//////////////////////////////////////

    @InitBinder("addStoreForm")
    public void addBrandFormInitBinder(WebDataBinder binder) {
        binder.addValidators(addStoreFormValidator);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////*  CONTROLLER ACTION  *///////////////////////////////////////////

    @RequestMapping(value = "/store/add", method = RequestMethod.GET)
    public ModelAndView addBrand(@ModelAttribute("addStoreForm") AddStoreForm addStoreForm) {
        return new ModelAndView("store/add", "addStoreForm", addStoreForm);
    }

    @RequestMapping(value = "/store/add", method = RequestMethod.POST)
    public ModelAndView addBrand(@Valid @ModelAttribute("addStoreForm")AddStoreForm addStoreForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return new ModelAndView("store/add","AddStoreForm",addStoreForm);

        return new ModelAndView("redirect:/");
    }

}
