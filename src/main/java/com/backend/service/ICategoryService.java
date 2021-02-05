package com.backend.service;

import java.util.List;

import com.backend.entity.Category;

public interface ICategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    void delete(Long id);

    Category updateCategory(Long id, Category categoryNew);

}
