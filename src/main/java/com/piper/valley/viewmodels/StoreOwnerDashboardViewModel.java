package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class StoreOwnerDashboardViewModel {

	@Autowired
	StoreService storeService;

	//Get User Stores.
	public HashMap<String, Object> create(Long Id) {
		HashMap<String, Object> model = new HashMap<>();
		Collection<Store> Accepted=storeService.getAllAcceptedUserStores(Id);
		Collection<Store> Pending=storeService.getAllPendingUserStores(Id);
		Collection<Store> Rejected=storeService.getAllNotAcceptedUserStores(Id);

		//statistics charts data
		Collection<String> Names         = Accepted.stream().map(Store::getName).collect(Collectors.toList());
		//Collection<Integer> ProductsCount = Accepted.stream().map(x -> x.getStoreProducts().size()).collect(Collectors.toList());
		Collection<Integer> ProductsCount = Accepted.stream().map(x -> x.getStoreProducts().stream().map(StoreProduct::getStoreViews).reduce(0, (a, b) -> a + b)).collect(Collectors.toList());

		model.put("accepted",Accepted);
		model.put("pending",Pending);
		model.put("rejected",Rejected);

		model.put("names",Names);
		model.put("productCount",ProductsCount);
		return model;
	}

}
