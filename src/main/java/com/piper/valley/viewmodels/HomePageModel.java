package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.Product;
import com.piper.valley.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class HomePageModel {
    @Autowired
    ProductService productService;

    //Getting all products
    public HashMap<String, Object> create() {
        HashMap<String, Object> model = new HashMap<>();
        Collection<Product> products=productService.getAllProducts();
        model.put("products",products);
        return model;
    }

}
