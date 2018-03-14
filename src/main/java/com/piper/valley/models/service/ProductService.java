package com.piper.valley.models.service;


import com.piper.valley.models.domain.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

	Optional<Product>getProductById(long id);
	Optional<Product>getProductByName(String name);
	Optional<Product>getPriceBetween(double start,double end);
	Collection<Product>getAllProducts();
	//Product addProduct(AddProductForm productForm);

}
