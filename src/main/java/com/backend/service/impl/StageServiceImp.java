package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.entity.Stage;
import com.backend.repository.IStageRepository;
import com.backend.service.IStageService;

import java.util.List;

@Service
public class StageServiceImp implements IStageService {

    @Autowired
    IStageRepository stageRepo;

    /**
     * Lleno la tabla de estados
     */
   /* @PostConstruct
    public void init() {
        Stage stage = new Stage();
        stage.setName("Abierto");

        stageRepo.save(stage);

        stage = new Stage();
        stage.setName("Cerrado");
        stageRepo.save(stage);

        stage = new Stage();
        stage.setName("En Tramite");
        stageRepo.save(stage);

        stage = new Stage();
        stage.setName("Con Reclamo");
        stageRepo.save(stage);


    }
*/
    @Override
    public List<Stage> findAll() {
        return stageRepo.findAll();
    }

    @Override
    public Page<Stage> findAll(Pageable pageable) {
        return stageRepo.findAll(pageable);
    }

    @Override
    public Stage findById(Long id) {
        return stageRepo.findById(id).orElse(null);
    }

    @Override
    public Stage save(Stage stage) {
        return stageRepo.save(stage);
    }

    @Override
    public void delete(Long id) {
        stageRepo.deleteById(id);
    }
}
