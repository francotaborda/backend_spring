package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Stage;


public interface IStageRepository extends JpaRepository<Stage, Long> {

}
