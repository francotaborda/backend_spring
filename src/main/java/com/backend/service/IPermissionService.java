package com.backend.service;

import javax.validation.Valid;

import com.backend.entity.Permission;

import java.util.List;


public interface IPermissionService {


    List<Permission> findAll();

    Permission save(@Valid Permission permission);

    Permission findById(Long id);

    void delete(Long id);


}
