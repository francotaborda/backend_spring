package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Category;
import com.backend.entity.Ticket;
import com.backend.repository.ITicketRepository;
import com.backend.service.ITicketService;

import java.util.List;

@Service

public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAll() {

        return ticketRepository.findAll();
    }

    @Override
    @Transactional
    public Ticket save(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public Ticket findById(Long id) {

        return ticketRepository.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> findByCategoriesEquals(Category category) {
        return ticketRepository.findByCategoriesEquals(category);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////

}
