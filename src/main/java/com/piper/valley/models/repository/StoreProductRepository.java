package com.piper.valley.models.repository;


import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.domain.StoreStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {
	Optional<StoreProduct> findById(Long id);

	List<StoreProduct> findAll();

	List<StoreProduct> findAllTop30ByOrderByIdDesc();

	List<StoreProduct> findAllTop30ByStore_StatusOrderByIdDesc(StoreStatus storeStatus);
}
