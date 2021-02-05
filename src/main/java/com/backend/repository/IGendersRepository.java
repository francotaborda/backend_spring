package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Gender;


public interface IGendersRepository extends JpaRepository<Gender, Long> {

}
