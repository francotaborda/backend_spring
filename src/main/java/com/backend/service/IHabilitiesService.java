package com.backend.service;

import java.util.List;

import com.backend.entity.Habilities;

public interface IHabilitiesService {

    Habilities findById(Long id);

    Habilities save(Habilities cliente);

    void delete(Long id);

    List<Habilities> findAll();
}
