package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Long>{

}
