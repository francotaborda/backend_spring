package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Company;
import com.backend.repository.ICompanyRepository;
import com.backend.service.ICompanyService;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImp implements ICompanyService {
    @Autowired
    ICompanyRepository companyRepo;
    @Autowired
    ICompanyService companyService;

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return companyRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Company findById(Long id) {
        return companyRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Company save(Company company) {
        return companyRepo.save(company);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        companyRepo.deleteById(id);

    }

    @Override
    @Transactional
    public Company update(Long id, Company companyNew) {
        Company company = companyRepo.findById(id).orElse(null);

        Date fecha = new Date();
        company.setName(companyNew.getName());
        company.setActive(companyNew.getActive());
        company.setCreated_at(company.getCreated_at());
        company.setUpdated_at(fecha);
        return companyRepo.save(company);
    }

    @Override
    @Transactional
    public void isActive(Long id) {
        Company company = companyRepo.findById(id).orElse(null);
        company.setActive(!company.getActive());
    }

   /* @PostConstruct
    public void init() {
        Company company = new Company();
        company.setName("Adalove");
        company.setActive(true);
        companyService.save(company);

    }*/


}
