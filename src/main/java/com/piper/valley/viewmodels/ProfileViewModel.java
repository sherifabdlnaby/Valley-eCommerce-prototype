package com.piper.valley.viewmodels;

import com.piper.valley.models.enums.OrderStatus;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.models.service.StoreService;
import com.piper.valley.models.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

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
        model.put("orders",orderService.getAllByUser(user.getId(), OrderStatus.PROCESSED));
        model.put("deliveredOrders",orderService.getAllByUser(user.getId(), OrderStatus.DELIVERED));
        model.put("user",user);
        model.put("stores", user.getStoreOwner() != null ? storeService.getAllAcceptedUserStores(user.getId()) : new ArrayList<>());
        return model;
    }
}
