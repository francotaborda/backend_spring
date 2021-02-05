package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Category;
import com.backend.repository.ICategoryRepository;
import com.backend.service.ICategoryService;

import java.util.List;

@Service
public class CategoryServiceImp implements ICategoryService {
    private final ICategoryRepository categoryDao;

    public CategoryServiceImp(ICategoryRepository categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryDao.deleteById(id);
    }

    @Transactional
    public Category updateCategory(Long id, Category categoryNew) {
        Category category = categoryDao.findById(id).orElse(null);
        category.setSummary(categoryNew.getSummary());
        return categoryDao.save(category);
    }

}
