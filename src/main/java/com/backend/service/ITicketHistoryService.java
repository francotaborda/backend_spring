package com.backend.service;

import java.util.List;

import javax.validation.Valid;

import com.backend.entity.TicketHistory;

public interface ITicketHistoryService {

	List<TicketHistory> findAll();

	TicketHistory findById(Long id);

	TicketHistory save(@Valid TicketHistory ticket);

	void delete(Long id);

}
