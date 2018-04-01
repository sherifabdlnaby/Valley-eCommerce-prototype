package com.piper.valley.models.repository;

import com.piper.valley.models.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<Order> findOneById(Long id);
}
