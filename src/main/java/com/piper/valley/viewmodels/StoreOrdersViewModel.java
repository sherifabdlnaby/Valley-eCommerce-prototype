package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.Order;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.models.service.StoreService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StoreOrdersViewModel {

	@Autowired
	StoreService storeService;

	@Autowired
	OrderService orderService;


	//Get Orders per store.
	public HashMap<String, Object> create(Long Id) {
		HashMap<String, Object> model = new HashMap<>();
		Collection<Store> Accepted=storeService.getAllAcceptedUserStores(Id);
		Collection<Collection<Order>>orders=new ArrayList<>();
		Collection<Store>stores=new ArrayList<>();
		for (Store store:Accepted)
		{
			Collection<Order>ordersPerStore=orderService.getAllProcessedByStore(store.getId());
			if(ordersPerStore.size()>0)
			{
				orders.add(ordersPerStore);
				stores.add(store);
			}
		}
		model.put("stores",stores);
		model.put("orders",orders);
		return model;
	}

}
