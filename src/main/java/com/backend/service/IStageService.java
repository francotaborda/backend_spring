package com.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.entity.Stage;

import java.util.List;

public interface IStageService {
    List<Stage> findAll();

    Page<Stage> findAll(Pageable pageable);

    Stage findById(Long id);

    Stage save(Stage stage);

    void delete(Long id);
}
