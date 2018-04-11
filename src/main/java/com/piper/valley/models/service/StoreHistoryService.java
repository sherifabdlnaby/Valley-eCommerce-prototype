package com.piper.valley.models.service;

import com.piper.valley.models.domain.StoreHistory;

import java.util.Collection;

public interface StoreHistoryService {
    Collection<StoreHistory> getByStoreID(Long id);
    void add(StoreHistory storeHistory);
}
