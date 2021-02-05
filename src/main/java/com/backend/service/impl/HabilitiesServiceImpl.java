package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Habilities;
import com.backend.repository.IHabilitiesRepository;
import com.backend.service.IHabilitiesService;

import java.util.List;

@Service
public class HabilitiesServiceImpl implements IHabilitiesService {

    @Autowired
    private IHabilitiesRepository habilitiesDao;


    @Override
    @Transactional(readOnly = true)
    public Habilities findById(Long id) {
        return habilitiesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Habilities save(Habilities cliente) {
        return habilitiesDao.save(cliente);
    }

    @Override
    public void delete(Long id) {
        habilitiesDao.deleteById(id);
    }

    @Override
    public List<Habilities> findAll() {
        return habilitiesDao.findAll();
    }


    /*@PostConstruct
    public void init() {
        Habilities hability = new Habilities();
        hability.setName("Mantenimiento Windows");
        hability.setExperience("Alto");
        habilitiesDao.save(hability);

        hability = new Habilities();
        hability.setName("Mantenimiento Windows");
        hability.setExperience("Medio");
        habilitiesDao.save(hability);

        hability = new Habilities();
        hability.setName("Mantenimiento Windows");
        hability.setExperience("Bajo");
        habilitiesDao.save(hability);

        hability = new Habilities();
        hability.setName("Programacion Java");
        hability.setExperience("Alto");
        habilitiesDao.save(hability);

        hability = new Habilities();
        hability.setName("Programacion Java");
        hability.setExperience("Medio");
        habilitiesDao.save(hability);

        hability = new Habilities();
        hability.setName("Programacion Java");
        hability.setExperience("Bajo");
        habilitiesDao.save(hability);
    }*/


}
