package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreHistory;
import com.piper.valley.models.service.StoreHistoryService;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Component
public class StoreHistoryViewModel {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreHistoryService storeHistoryService;
    public HashMap<String, Object> create(Long Id) {
        HashMap<String, Object> model = new HashMap<>();
        //TODO get collab stores too ? (we follow requirement)
        Collection<Store> stores =storeService.getAllAcceptedUserStores(Id);

        for (Store store :  stores)
            Collections.reverse(store.getHistory());

        model.put("stores", stores);
        return model;
    }

}
