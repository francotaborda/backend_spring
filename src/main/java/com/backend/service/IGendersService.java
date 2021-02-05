package com.backend.service;

import javax.validation.Valid;

import com.backend.entity.Gender;

import java.util.List;

public interface IGendersService {

    Gender save(@Valid Gender gender);

    Gender findById(Long id);

    void delete(Long id);

    Gender update(Long id, Gender genderNew);

    void isActive(Long id);

    List<Gender> findAll();

    Boolean existeGenero(Long id);
}
