package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Knowledge;
import com.backend.repository.IKnowledgeRepository;
import com.backend.service.IKnowledgeService;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements IKnowledgeService {

    @Autowired
    private IKnowledgeRepository knowledgeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Knowledge> findAll() {
        return knowledgeDao.findAll();
    }

    @Override
    public Page<Knowledge> findAll(Pageable pageable) {
        return knowledgeDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Knowledge findById(Long id) {
        return knowledgeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Knowledge save(Knowledge knowledge) {
        return knowledgeDao.save(knowledge);
    }

    @Override
    public void delete(Long id) {
        knowledgeDao.deleteById(id);
    }
}
