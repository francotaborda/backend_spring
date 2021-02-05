package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.SystemConfig;

public interface ISystemConfigRepository extends JpaRepository<SystemConfig, Long> {
}
