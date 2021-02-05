package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Priority;
import com.backend.repository.IPrioritiesRepository;
import com.backend.service.IPrioritiesService;

@Service
public class PrioritiesServicesImpl implements IPrioritiesService {

    @Autowired
    private IPrioritiesRepository prioritiesDao;

    @Override
    @Transactional(readOnly = true)
    public Priority findById(long Id) {
        return prioritiesDao.findById(Id).orElse(null);
    }

    @Override
    @Transactional
    public Priority save(Priority cliente) {
        return prioritiesDao.save(cliente);
    }

    @Override
    public void delete(Long id) {
    }
	/*@PostConstruct
	public void init() {
    Priority priority = new Priority();
    priority.setName("Urgente");
    prioritiesDao.save(priority);
   
    priority = new Priority();
    priority.setName("Muy Alta");
    prioritiesDao.save(priority);

    priority = new Priority();
    priority.setName("Alta");
    prioritiesDao.save(priority);

    priority = new Priority();
    priority.setName("Media");
    prioritiesDao.save(priority);

    priority = new Priority();
    priority.setName("Baja");
    prioritiesDao.save(priority);

	}*/

}
