package com.piper.valley.models.service;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.models.domain.Brand;

import java.util.Collection;

public interface BrandService {
    Collection<Brand> getAllBrands();
    Brand addBrand(AddBrandForm form);
}
