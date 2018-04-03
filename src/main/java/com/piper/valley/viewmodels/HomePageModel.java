package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class HomePageModel {
    @Autowired
    StoreProductService storeProductService;

    //Getting all products
    public HashMap<String, Object> create() {
        HashMap<String, Object> model = new HashMap<>();
        Collection<StoreProduct> products=storeProductService.getAll();
        model.put("products",products);
        return model;
    }

}
