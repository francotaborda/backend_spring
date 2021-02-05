package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Team;
import com.backend.repository.ITeamRepository;
import com.backend.service.ITeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private ITeamRepository teamsDao;


    @Override
    public List<Team> findAll() {
        return teamsDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Team findById(Long id) {
        return teamsDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Team save(Team cliente) {
        return teamsDao.save(cliente);
    }

    @Override
    public void delete(Long id) {
        teamsDao.deleteById(id);
    }


}
