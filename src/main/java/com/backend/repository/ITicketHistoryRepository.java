package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.TicketHistory;

public interface ITicketHistoryRepository extends JpaRepository<TicketHistory, Long>{

}
