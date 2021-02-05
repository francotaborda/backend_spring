package com.backend.service;

import javax.validation.Valid;

import com.backend.entity.Client;
import com.backend.models.ClientRequest;
import com.backend.models.ClientResponse;

import java.util.List;


public interface IClientService {

    List<ClientResponse> findAll();

    //public Page<ClientResponse> findAll(Pageable pageable);

    ClientResponse findById(Long id);

    ClientResponse save(@Valid ClientRequest clientRequest);

    ClientResponse update(Long id, ClientRequest clientRquest);

    void delete(Long id);

    List<ClientResponse> listarGenders(Long genders_id);

    List<ClientResponse> listaClientActive(Boolean active);

    void isActive(Long id);

    Client findByIdClient(Long id);

}
