package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Client;
import com.backend.entity.Gender;

import java.util.List;
import java.util.Optional;

public interface IClientRepository extends JpaRepository<Client, Long>{

    Optional<Client> findById(Long id);
    List<Client> findClientByActive(Boolean active);
    List<Client> findClientByGenderId(Gender gender);
}
