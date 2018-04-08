package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.Order;
import com.piper.valley.models.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class ShoppingCartModel {
    @Autowired
    OrderService orderService;

    public HashMap<String, Object> create(Long id) {
        HashMap<String, Object> model = new HashMap<>();
        Collection<Order> orders=orderService.getOrders(id,false);
        model.put("orders",orders);
        Double total=0.0;
        for(Order order: orders)
        {
            total+=order.getQuantity()*order.getStoreProduct().getPrice();
        }
        model.put("total",total);
        return model;
    }

}
