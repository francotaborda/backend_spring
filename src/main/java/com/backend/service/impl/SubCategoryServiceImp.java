package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Category;
import com.backend.entity.SubCategory;
import com.backend.models.SubCategoryRequest;
import com.backend.models.SubCategoryResponse;
import com.backend.repository.ICategoryRepository;
import com.backend.repository.ISubCategoryRepository;
import com.backend.service.ISubCategoryService;

import java.util.Date;
import java.util.List;

@Service
public class SubCategoryServiceImp implements ISubCategoryService {

    @Autowired
    ISubCategoryRepository subRepo;


    @Autowired
    ICategoryRepository catRepo;


    @Override
    @Transactional(readOnly = true)
    public SubCategoryResponse findById(Long id) {

        SubCategory sub = subRepo.findById(id).orElse(null);
        return new SubCategoryResponse(sub.getId(), sub.getSummary(), sub.getCategoryId().getId(),
                sub.getCreatedAt(), sub.getUpdatedAt());
    }

    @Override
    @Transactional
    public SubCategoryResponse save(SubCategoryRequest subCategoryRequest) {
        Category categ = catRepo.findById(subCategoryRequest.getCategory_id()).orElse(null);

        SubCategory sub = new SubCategory();
        sub.setSummary(subCategoryRequest.getSummary());
        sub.setCategoryId(categ);
        SubCategory result = subRepo.save(sub);
        return new SubCategoryResponse(result.getId(), result.getSummary(), result.getCategoryId().getId(), result.getCreatedAt(), result.getUpdatedAt());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        subRepo.deleteById(id);
    }

    @Override
    @Transactional
    public SubCategoryResponse updateSubCategory(Long id, SubCategoryRequest subCategoryRequest) {
        SubCategory sub = subRepo.findById(id).orElse(null);

        Date fecha = new Date();

        Category cat = catRepo.findById(subCategoryRequest.getCategory_id()).orElse(null);
        sub.setSummary(subCategoryRequest.getSummary());
        sub.setCategoryId(cat);
        sub.setUpdatedAt(fecha);

        SubCategory result = subRepo.save(sub);

        return new SubCategoryResponse(result.getId(), result.getSummary(), result.getCategoryId().getId(), result.getCreatedAt(), result.getUpdatedAt());

    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SubCategoryResponse> listar(Long category_id) {
        return jdbcTemplate.query("SELECT sub_categories.id, sub_categories.summary, sub_categories.category_id, sub_categories.created_at, sub_categories.updated_at\r\n" +
                        "FROM sub_categories where sub_categories.category_id= " + category_id + " GROUP BY sub_categories.id, sub_categories.summary, sub_categories.category_id, sub_categories.created_at, sub_categories.updated_at",
                (rs, rowNum) -> new SubCategoryResponse(rs.getLong("id"),
                        rs.getString("summary"), rs.getLong("category_id"), rs.getDate("created_at"), rs.getDate("updated_at")));

    }


}
