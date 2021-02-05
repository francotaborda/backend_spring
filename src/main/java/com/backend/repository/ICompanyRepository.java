package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Company;

public interface ICompanyRepository extends JpaRepository<Company, Long> {

}
