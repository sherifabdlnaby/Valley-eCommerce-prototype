package com.piper.valley.models.service;


import com.piper.valley.forms.AddOrderForm;
import com.piper.valley.models.domain.Order;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.repository.OrderRepository;
import com.piper.valley.models.repository.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StoreProductRepository storeProductRepository;

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
        order1.setProcessedDate(new Date());
        return orderRepository.save(order1);
    }

    @Override
    public Collection<Order> getAllProcessedByStore(Long id) {
        Collection<StoreProduct>products=storeProductRepository.findAllByStoreId(id);
        Collection<Order>allOrders=orderRepository.findAllByProcessed(true);
        Collection<Order>orders=orderRepository.findAllByUser_IdAndProcessed((long)-1,false);
        for(Order order:allOrders) {
            if (products.contains(order.getStoreProduct()))
            {
                orders.add(order);
            }
        }
        return orders;
    }
}
