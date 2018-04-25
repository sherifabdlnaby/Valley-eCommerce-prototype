package com.piper.valley.models.service;

import com.piper.valley.auth.CurrentUser;
import com.piper.valley.models.domain.StoreCollabHistory;
import com.piper.valley.models.domain.StoreHistory;
import com.piper.valley.models.domain.StoreProductHistory;

import java.util.Collection;
import java.util.Optional;

public interface StoreHistoryService {
    Boolean undo(Long HistoryId, CurrentUser currentUser);
    Boolean undoCollab(StoreCollabHistory storeCollabHistory, CurrentUser currentUser);
    Boolean undoProduct(StoreProductHistory storeProductHistory, CurrentUser currentUser);
    Collection<StoreHistory> getByStoreID(Long id);
    void add(StoreHistory storeHistory);
    Optional<StoreHistory> getById(Long Id);
}
