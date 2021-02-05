package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Gender;
import com.backend.repository.IGendersRepository;
import com.backend.service.IGendersService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service
public class GendersServiceImp implements IGendersService {

    @Autowired
    IGendersRepository genderRepo;

    @Override
    @Transactional
    public Gender save(@Valid Gender gender) {
        return genderRepo.save(gender);
    }

    @Override
    @Transactional(readOnly = true)
    public Gender findById(Long id) {
        return genderRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        genderRepo.deleteById(id);

    }

    @Override
    @Transactional
    public Gender update(Long id, Gender genderNew) {
        Gender gender = genderRepo.findById(id).orElse(null);
        Date fecha = new Date();
        gender.setSummary(genderNew.getSummary());
        gender.setActive(genderNew.getActive());
        gender.setUpdated_at(fecha);
        return genderRepo.save(gender);
    }

    @Override
    @Transactional
    public void isActive(Long id) {
        Gender gender = genderRepo.findById(id).orElse(null);
        gender.setActive(!gender.getActive());
    }

    public Boolean existeGenero(Long id) {
        Gender gender = genderRepo.findById(id).orElse(null);
        return gender != null;
    }

    @Override
    public List<Gender> findAll() {
        return genderRepo.findAll();
    }

}
