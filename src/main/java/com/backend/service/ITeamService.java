package com.backend.service;

import java.util.List;

import com.backend.entity.Team;

public interface ITeamService {

    List<Team> findAll();

    Team findById(Long id);

    Team save(Team cliente);

    void delete(Long id);

}
