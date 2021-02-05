package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Knowledge;

public interface IKnowledgeRepository extends JpaRepository<Knowledge, Long> {
}
