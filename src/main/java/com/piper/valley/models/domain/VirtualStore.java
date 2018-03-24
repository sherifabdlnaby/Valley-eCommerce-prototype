package com.piper.valley.models.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.ArrayList;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class VirtualStore extends Store {

	@Override
	public List<StoreProduct> getStoreProducts() {
		//TODO Check that they're all Virtual
		return storeProducts;
	}

	@Override
	public boolean addStoreProduct(StoreProduct storeProduct) {
		if(storeProduct.getProduct() instanceof VirtualProduct){
			if(storeProducts == null)
				storeProducts = new ArrayList<>();
			return storeProducts.add(storeProduct);
		}
		return false;
	}

	@Override
	public boolean setStoreProducts(List<StoreProduct> storeProducts) {
		//TODO check that they're all virtual products
		this.storeProducts = storeProducts;
		return true;
	}
}

