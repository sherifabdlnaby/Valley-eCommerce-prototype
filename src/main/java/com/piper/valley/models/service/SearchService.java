package com.piper.valley.models.service;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.models.common.SearchResult;
import com.piper.valley.models.domain.Brand;
import com.piper.valley.models.domain.Product;
import com.piper.valley.models.domain.StoreProduct;

import java.util.Collection;
import java.util.List;

public interface SearchService {
    SearchResult generalSearch(String queryString);
    List<StoreProduct> storeProductSearch(String queryString);
    List<Product> productSearch(String queryString);
}
