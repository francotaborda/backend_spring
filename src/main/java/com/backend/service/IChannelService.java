package com.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.entity.Channel;

import java.util.List;

public interface IChannelService {
    List<Channel> findAll();

    Page<Channel> findAll(Pageable pageable);

    Channel findById(Long id);

    Channel save(Channel cliente);

    void delete(Long id);
}
