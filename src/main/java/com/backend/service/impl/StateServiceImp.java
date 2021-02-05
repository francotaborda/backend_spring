package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.repository.IStateRepository;

@Service
public class StateServiceImp {

    @Autowired
    IStateRepository stateRepo;
    /**
     * Lleno la tabla de estados
     */
  /*  @PostConstruct
    public void init() {
        State state = new State();
        state.setName("Desconectado");
        stateRepo.save(state);
        
        state = new State();
        state.setName("Conectado");
        stateRepo.save(state);
        
        state = new State();
        state.setName("Ocupado");
        stateRepo.save(state);
        
        state = new State();
        state.setName("Ausente");
        stateRepo.save(state);
        

    }*/

}
