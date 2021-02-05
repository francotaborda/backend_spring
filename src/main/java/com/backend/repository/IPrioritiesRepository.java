package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Priority;

public interface IPrioritiesRepository extends JpaRepository<Priority, Long> {

}
