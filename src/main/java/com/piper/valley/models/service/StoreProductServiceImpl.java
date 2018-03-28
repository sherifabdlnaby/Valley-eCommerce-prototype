package com.piper.valley.models.service;

import com.piper.valley.forms.AddProductForm;
import com.piper.valley.models.domain.PhysicalProduct;
import com.piper.valley.models.domain.Product;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.VirtualProduct;
import com.piper.valley.models.repository.BrandRepository;
import com.piper.valley.models.repository.CompanyRepository;
import com.piper.valley.models.repository.ProductRepository;
import com.piper.valley.models.repository.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class StoreProductServiceImpl implements StoreProductService {

	@Autowired
	private StoreProductRepository storeProductRepository;

	@Override
	public Optional<StoreProduct> getProductById(Long id) {
		return storeProductRepository.findById(id);
	}

	public void incrementViews(Long storeProductId) {
		Optional<StoreProduct> product = Optional.ofNullable(storeProductRepository.findOne(storeProductId));
		product.ifPresent(product1 -> {
			product1.setStoreViews(product1.getStoreViews()+1);
			storeProductRepository.save(product1);
		});
	}
}
