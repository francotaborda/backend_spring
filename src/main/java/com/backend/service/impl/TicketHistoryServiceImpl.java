package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.TicketHistory;
import com.backend.repository.ITicketHistoryRepository;
import com.backend.service.ITicketHistoryService;

import javax.validation.Valid;
import java.util.List;


@Service

public class TicketHistoryServiceImpl implements ITicketHistoryService {


    @Autowired
    private ITicketHistoryRepository ticketHistoryRepository;

    @Override
    public List<TicketHistory> findAll() {
        return ticketHistoryRepository.findAll();
    }

    @Override
    public TicketHistory findById(Long id) {
        return ticketHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public TicketHistory save(@Valid TicketHistory ticket) {
        return ticketHistoryRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        ticketHistoryRepository.deleteById(id);

    }


}
