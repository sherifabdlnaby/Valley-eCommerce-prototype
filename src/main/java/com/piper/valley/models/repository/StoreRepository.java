package com.piper.valley.models.repository;

import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	List<Store> findAllByStatus(StoreStatus storeStatus);
	List<Store> findByStoreOwner_IdAndName(Long id, String Name);
	List<Store> findByStoreOwner_Id(Long id);
	List<Store> findByStoreOwner_IdAndStatus(Long id, StoreStatus storeStatus);
}
