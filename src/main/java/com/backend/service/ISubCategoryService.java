package com.backend.service;

import javax.validation.Valid;

import com.backend.models.SubCategoryRequest;
import com.backend.models.SubCategoryResponse;

import java.util.List;

public interface ISubCategoryService {

    SubCategoryResponse findById(Long id);

    SubCategoryResponse save(@Valid SubCategoryRequest subCategoryRequest);

    void delete(Long id);

    SubCategoryResponse updateSubCategory(Long id, SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> listar(Long company_id);


}
