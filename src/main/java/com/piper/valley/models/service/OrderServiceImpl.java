package com.piper.valley.models.service;


import com.piper.valley.forms.AddOrderForm;
import com.piper.valley.models.domain.Order;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findOneById(id);
    }

    @Override
    public Order addOrder(User user, StoreProduct storeProduct, AddOrderForm addOrderForm) {
        Order order=new Order(user,storeProduct,addOrderForm);
        return orderRepository.save(order);
    }

    @Override
    public Collection<Order> getOrders(Long id, Boolean processed) {
        return orderRepository.findAllByUser_IdAndProcessed(id,processed);
    }

    @Override
    public Order changeStatus(Long id) {
        Optional<Order>order=getOrderById(id);
        Order order1=order.get();
        order1.setProcessed(true);
        return orderRepository.save(order1);
    }
}
