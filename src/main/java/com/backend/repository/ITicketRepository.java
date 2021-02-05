package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Category;
import com.backend.entity.Ticket;

import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket, Long>{

    List<Ticket> findByCategoriesEquals(Category category);
}
