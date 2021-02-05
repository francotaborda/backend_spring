package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Role;
import com.backend.repository.IRoleRepository;
import com.backend.service.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Autowired
    IRoleService roleService;

   /* @PostConstruct
    public void init() {
        Role role = new Role();
        role.setName("SysAdmin");
        role.setDescription("Control General del Sistema");
        role.setReportToRole("Adalove");
        roleService.save(role);

        role = new Role();
        role.setName("Administrador");
        role.setDescription("Administrador del sistema");
        role.setReportToRole("Adalove");
        roleService.save(role);

        role = new Role();
        role.setName("Supervisor");
        role.setDescription("Supervisor de Operadores");
        role.setReportToRole("Adalove");
        roleService.save(role);

        role = new Role();
        role.setName("Operador");
        role.setDescription("Operador basico");
        role.setReportToRole("Adalove");
        roleService.save(role);

        role = new Role();
        role.setName("Adalove");
        role.setDescription("Soporte Tecnico");
        role.setReportToRole("Adalove");
        roleService.save(role);

    }*/
}
