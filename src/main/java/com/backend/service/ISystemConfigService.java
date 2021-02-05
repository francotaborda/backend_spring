package com.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.entity.SystemConfig;

import java.util.List;

public interface ISystemConfigService {
    List<SystemConfig> findAll();

    Page<SystemConfig> findAll(Pageable pageable);

    SystemConfig findById(Long id);

    SystemConfig save(SystemConfig systemConfig);

    void delete(Long id);
}
