package com.piper.valley.models.service;

import com.piper.valley.forms.AddBrandForm;
import com.piper.valley.models.common.SearchResult;
import com.piper.valley.models.domain.Brand;

import java.util.Collection;
import java.util.List;

public interface SearchService {
    List<SearchResult> generalSearch(String query);
}
