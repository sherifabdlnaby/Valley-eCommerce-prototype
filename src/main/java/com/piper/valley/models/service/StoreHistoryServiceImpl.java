package com.piper.valley.models.service;

import com.piper.valley.models.domain.StoreHistory;
import com.piper.valley.models.repository.StoreHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class StoreHistoryServiceImpl implements  StoreHistoryService{
    @Autowired
    StoreHistoryRepository storeHistoryRepository;
    @Override
    public void add(StoreHistory storeHistory)
    {
        storeHistoryRepository.save(storeHistory);
    }
    @Override
    public Collection<StoreHistory> getByStoreID(Long id)
    {
        return  storeHistoryRepository.findAllByStoreId(id);
    }
}
