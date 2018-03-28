package com.piper.valley.models.service;


import com.piper.valley.models.domain.PhysicalProduct;

import java.util.Optional;

public interface PhysicalProductService {

	Optional<PhysicalProduct>getProductbyId(Long id);

}
