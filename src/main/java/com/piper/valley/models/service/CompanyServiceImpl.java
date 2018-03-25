package com.piper.valley.models.service;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.forms.AddCompanyForm;
import com.piper.valley.models.domain.Brand;
import com.piper.valley.models.domain.Company;
import com.piper.valley.models.repository.BrandRepository;
import com.piper.valley.models.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Collection<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(AddCompanyForm form) {
        Company company= new Company();
        company.setName(form.getName());
        return companyRepository.save(company);
    }
}
