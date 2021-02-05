package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.IDType;

public interface IDocumentRepository extends JpaRepository<IDType, Long> {

}
