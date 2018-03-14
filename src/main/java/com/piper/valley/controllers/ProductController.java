package com.piper.valley.controllers;


import com.piper.valley.models.domain.Product;
import com.piper.valley.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Optional;

@Controller
public class ProductController {
    /////////////////////////*  SERVICES, REPOSITORIES AND VALIDATORS SECTION  */////////////////////////////

    @Autowired
    private ProductService productService;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////*  CONTROLLER ACTION  *///////////////////////////////////////////

    //@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/product/view/{id}", method = RequestMethod.GET)
    public ModelAndView viewProduct(@PathVariable("id") long id) {
        Optional<Product> product = productService.getProductById(id);
        if (!product.isPresent()) {
            return new ModelAndView("error/404");
        }
        return new ModelAndView("product/view", "product", product);
    }






}
