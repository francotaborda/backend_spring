package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
