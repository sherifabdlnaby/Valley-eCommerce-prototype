package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.Store;
import com.piper.valley.models.repository.OrderRepository;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StoreOwnerDashboardViewModel {

	@Autowired
	StoreService storeService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	//Get User Stores.
	public HashMap<String, Object> create(Long Id) {
		HashMap<String, Object> model = new HashMap<>();
		Collection<Store> Accepted = storeService.getAllAcceptedUserStores(Id);
		Collection<Store> Pending = storeService.getAllPendingUserStores(Id);
		Collection<Store> Rejected = storeService.getAllNotAcceptedUserStores(Id);
		Collection<Store> Collaborated = storeService.getAllCollaboratedUserStores(Id);
		model.put("accepted", Accepted);
		model.put("pending", Pending);
		model.put("rejected", Rejected);
		model.put("collaborated", Collaborated);

		return model;
	}

}
