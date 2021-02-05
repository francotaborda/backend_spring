package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.SystemConfig;
import com.backend.repository.ISystemConfigRepository;
import com.backend.service.ISystemConfigService;

import java.util.List;

@Service
public class SystemConfigServiceImpl implements ISystemConfigService {

    @Autowired
    private ISystemConfigRepository configDao;


    @Override
    @Transactional(readOnly = true)
    public List<SystemConfig> findAll() {
        return configDao.findAll();
    }

    @Override
    public Page<SystemConfig> findAll(Pageable pageable) {
        return configDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public SystemConfig findById(Long id) {
        return configDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public SystemConfig save(SystemConfig systemConfig) {
        return configDao.save(systemConfig);
    }

    @Override
    public void delete(Long id) {
        configDao.deleteById(id);
    }
}
