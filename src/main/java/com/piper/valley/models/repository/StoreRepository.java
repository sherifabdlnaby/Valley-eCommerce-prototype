package com.piper.valley.models.repository;

import com.piper.valley.models.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	List<Store> findAllByAccepted(Boolean accepted);
	List<Store> findByStoreOwner_IdAndName(Long id, String Name);
}
