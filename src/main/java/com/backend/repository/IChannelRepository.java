package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Channel;

public interface IChannelRepository extends JpaRepository<Channel, Long> {
}
