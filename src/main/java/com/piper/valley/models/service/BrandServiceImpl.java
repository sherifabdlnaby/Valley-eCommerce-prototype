package com.piper.valley.models.service;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.models.domain.Brand;
import com.piper.valley.models.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Collection<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand addBrand(AddBrandForm form) {
        Brand brand=new Brand();
        brand.setName(form.getName());
        return brandRepository.save(brand);
    }
}
