package com.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    Page<Role> findAll(Pageable pageable);

    Role findById(Long id);

    Role save(Role cliente);

    void delete(Long id);
}
