package com.piper.valley.viewmodels;

import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreHistory;
import com.piper.valley.models.service.StoreHistoryService;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
public class StoreHistoryViewModel {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreHistoryService storeHistoryService;
    public HashMap<String, Object> create(Long Id) {
        HashMap<String, Object> model = new HashMap<>();
        Collection<Store> Accepted=storeService.getAllAcceptedUserStores(Id);
        Collection<Collection<StoreHistory>>history=new ArrayList<>();
        Collection<Store>stores=new ArrayList<>();
        for (Store store:Accepted)
        {
            Collection<StoreHistory>historiesPerStore=storeHistoryService.getByStoreID(store.getId());
            if(historiesPerStore.size()>0)
            {
                history.add(historiesPerStore);
                stores.add(store);
            }
        }
        model.put("stores",stores);
        model.put("history",history);
        return model;
    }

}
