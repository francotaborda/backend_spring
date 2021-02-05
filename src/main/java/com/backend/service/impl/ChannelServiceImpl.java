package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Channel;
import com.backend.repository.IChannelRepository;
import com.backend.service.IChannelService;

import java.util.List;

@Service
public class ChannelServiceImpl implements IChannelService {

    @Autowired
    private IChannelRepository channelDao;


    @Override
    @Transactional(readOnly = true)
    public List<Channel> findAll() {
        return channelDao.findAll();
    }

    @Override
    public Page<Channel> findAll(Pageable pageable) {
        return channelDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Channel findById(Long id) {
        return channelDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Channel save(Channel channel) {
        return channelDao.save(channel);
    }

    @Override
    public void delete(Long id) {
        channelDao.deleteById(id);
    }
}
