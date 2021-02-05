package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Habilities;

public interface IHabilitiesRepository extends JpaRepository<Habilities, Long> {
}
