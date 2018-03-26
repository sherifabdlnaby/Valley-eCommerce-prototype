package com.piper.valley.models.repository;

import com.piper.valley.models.domain.Brand;
import com.piper.valley.models.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findOneByName(String name);
    Optional<Company> findOneById(Integer id);
    List<Company> findAll();

}
