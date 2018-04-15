package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.*;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.models.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class ProfileViewModel {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    StoreService storeService;
    public HashMap<String,Object> create(User user)
    {
        HashMap<String, Object> model = new HashMap<>();
        model.put("orders", user.getOrders().stream().filter(x -> x.getOrderStatus() == OrderStatus.PROCESSED).collect(Collectors.toList()));
        model.put("user",user);
        model.put("stores", user.getStoreOwner() != null ? user.getStoreOwner().getStores().stream().filter(x -> x.getStatus() == StoreStatus.ACCEPTED).collect(Collectors.toList())
                                                            : new ArrayList<>());
        return model;
    }
}
