package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Team;

public interface ITeamRepository extends JpaRepository<Team, Long> {
}
