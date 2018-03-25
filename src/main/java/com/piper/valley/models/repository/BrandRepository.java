package com.piper.valley.models.repository;

import com.piper.valley.models.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findOneByName(String name);
    Optional<Brand> findOneById(Integer id);
    List<Brand> findAll();

}
