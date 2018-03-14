package com.piper.valley.models.repository;

import com.piper.valley.models.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	Optional<Store> findOneById(long id);
}
