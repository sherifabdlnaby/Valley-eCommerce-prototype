package com.piper.valley.models.common;

import com.piper.valley.models.domain.Product;
import com.piper.valley.models.domain.StoreProduct;

import java.util.List;

public class SearchResult {
	private List<StoreProduct> storeProducts;
	private List<Product> products;

	public SearchResult() {
	}

	public SearchResult(List<StoreProduct> storeProducts, List<Product> products) {
		this.storeProducts = storeProducts;
		this.products = products;
	}

	public List<StoreProduct> getStoreProducts() {
		return storeProducts;
	}

	public void setStoreProducts(List<StoreProduct> storeProducts) {
		this.storeProducts = storeProducts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
