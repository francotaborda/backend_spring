package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.State;


public interface IStateRepository extends JpaRepository<State, Long>{

}
