package com.piper.valley.models.repository;

import com.piper.valley.models.domain.History;
import com.piper.valley.models.domain.HistoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findOneById(Long id);
    List<History> findAllByType(HistoryType historyType);
    List<History> findAll();
}


