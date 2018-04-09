package com.piper.valley.models.repository;

import com.piper.valley.models.domain.Brand;
import com.piper.valley.models.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOneById(Long id);
    Integer countOrdersByUser_IdAndProcessed(Long id, Boolean isProcessed);
    List<Order> findAllByUser_IdAndProcessed(Long id, Boolean isProcessed);
    List<Order> findAllByUser_IdAndProcessedOrderByProcessedDateDesc(Long id, Boolean isProcessed);
    List<Order> findAllByProcessed(Boolean isProcessed);

}
