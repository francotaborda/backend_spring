package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Permission;
import com.backend.repository.IPermissionRepository;
import com.backend.service.IPermissionService;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {


    @Autowired
    private IPermissionRepository permissionDao;

    @Autowired
    private IPermissionService permission;

    @Override
    @Transactional(readOnly = true)
    public List<Permission> findAll() {

        return permissionDao.findAll();
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {

        return permissionDao.save(permission);
    }

    @Override
    @Transactional(readOnly = true)
    public Permission findById(Long id) {

        return permissionDao.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        permissionDao.deleteById(id);
    }

   /* @PostConstruct
    public void init(){
        Permission permission = new Permission();
        permission.setName("Crear usuarios");
        permissionDao.save(permission);

        permission = new Permission();
        permission.setName("Reasignar ticket");
        permissionDao.save(permission);

        permission = new Permission();
        permission.setName("Facturar");
        permissionDao.save(permission);

        permission = new Permission();
        permission.setName("Subir archivos");
        permissionDao.save(permission);

        permission = new Permission();
        permission.setName("Ver reportes");
        permissionDao.save(permission);
    }*/


}
