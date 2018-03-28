package com.piper.valley.models.service;


import com.piper.valley.models.domain.StoreProduct;

import java.util.Optional;

public interface StoreProductService {

	Optional<StoreProduct>getProductById(Long id);
	void incrementViews(StoreProduct storeProduct);

}
