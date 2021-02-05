package com.backend.service;

import java.util.List;

import com.backend.entity.Company;



public interface ICompanyService {
List<Company> findAll();
	
	//public Page<Company> findAll(Pageable pageable);

	Company findById(Long id);

	Company save(Company company);

	void delete(Long id);
	
	Company update(Long id, Company companyNew);
	
	void isActive(Long id);

}
