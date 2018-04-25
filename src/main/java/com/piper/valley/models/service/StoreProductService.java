package com.piper.valley.models.service;


import com.piper.valley.models.domain.StoreProduct;

import java.util.Collection;
import java.util.Optional;

public interface StoreProductService {
	Optional<StoreProduct>getProductById(Long id);
	void incrementViews(StoreProduct storeProduct);
	Collection<StoreProduct> getTop30();
	void remove(StoreProduct storeProduct);
}
