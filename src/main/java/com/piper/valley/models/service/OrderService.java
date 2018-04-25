package com.piper.valley.models.service;

import com.piper.valley.forms.AddOrderForm;
import com.piper.valley.models.domain.Order;
import com.piper.valley.models.domain.OrderStatus;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {
   Optional<Order>getOrderById(Long id);
   Order addOrder(User user, StoreProduct storeProduct, AddOrderForm addOrderForm);
   Collection<Order> getAllProcessedByStore(Long id, OrderStatus orderStatus);
   Collection<Order> getAllByUser(Long id, OrderStatus orderStatus);
   Collection<Order> getOrders(Long id, OrderStatus orderStatus);
   Order changeStatus(Long id);
   Integer checkout(Long userId);
   Order finishOrder(Long orderId);
   Integer getOrderCountByUser(Long id, OrderStatus orderStatus);
}
