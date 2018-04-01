package com.piper.valley.models.service;

import com.piper.valley.models.domain.Order;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findOneById(id);
	}

	@Override
	public void addOrder(User user, StoreProduct storeProduct) {
		Order order = new Order(user, storeProduct, 1); // TODO: quantity
		orderRepository.save(order);
	}
}
