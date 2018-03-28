package com.piper.valley.models.service;


import com.piper.valley.models.domain.PhysicalProduct;
import com.piper.valley.models.repository.PhysicalProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class PhysicalProductServiceImpl implements PhysicalProductService {

	@Autowired
	private PhysicalProductRepository physicalProductRepository;

	@Override
	public Optional<PhysicalProduct> getProductbyId(Long id) {
		return physicalProductRepository.findById(id);
	}
}
