package com.piper.valley.models.repository;

import com.piper.valley.models.domain.StoreHistory;
import com.piper.valley.models.domain.StoreHistoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreHistoryRepository extends JpaRepository<StoreHistory, Long> {
    Optional<StoreHistory> findOneById(Long id);
    List<StoreHistory> findAllByType(StoreHistoryType storeHistoryType);
    List<StoreHistory> findAll();
    List<StoreHistory> findAllByStoreId(Long id);

}


