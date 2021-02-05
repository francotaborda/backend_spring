package com.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.entity.Knowledge;

import java.util.List;

public interface IKnowledgeService {
    List<Knowledge> findAll();

    Page<Knowledge> findAll(Pageable pageable);

    Knowledge findById(Long id);

    Knowledge save(Knowledge cliente);

    void delete(Long id);
}
