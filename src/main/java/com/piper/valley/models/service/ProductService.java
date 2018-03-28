package com.piper.valley.models.service;


import com.piper.valley.forms.AddProductForm;
import com.piper.valley.models.domain.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

	Optional<Product>getProductById(Long id);
	Optional<Product>getProductByName(String name);
	Optional<Product>getPriceBetween(Float start,Float end);
	Collection<Product>getAllProducts();
	Product addProduct(AddProductForm productForm);
	void incrementViews(Product product);
}
