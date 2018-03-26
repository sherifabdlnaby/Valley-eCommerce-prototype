package com.piper.valley.models.service;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.forms.AddCompanyForm;
import com.piper.valley.models.domain.Brand;
import com.piper.valley.models.domain.Company;

import java.util.Collection;

public interface CompanyService {
    Collection<Company> getAllCompanies();
    Company addCompany(AddCompanyForm form);
}
