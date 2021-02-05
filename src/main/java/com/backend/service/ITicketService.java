package com.backend.service;

import java.util.List;

import javax.validation.Valid;

import com.backend.entity.Category;
import com.backend.entity.Ticket;

public interface ITicketService {

	List<Ticket> findAll();

	Ticket findById(Long id);

	Ticket save(@Valid Ticket ticket);

	void delete(Long id);

	List<Ticket> findByCategoriesEquals(Category category);

}
