package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.SubCategory;

public interface ISubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
