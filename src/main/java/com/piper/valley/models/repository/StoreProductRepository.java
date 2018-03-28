package com.piper.valley.models.repository;


import com.piper.valley.models.domain.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {
	Optional<StoreProduct>findById(Long id);

}
