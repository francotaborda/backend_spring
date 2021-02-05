package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.IDType;
import com.backend.repository.IDocumentRepository;
import com.backend.service.IDocumentService;

import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements IDocumentService {
    @Autowired
    IDocumentRepository documentRepo;

    @Override
    @Transactional(readOnly = true)
    public List<IDType> findAll() {
        return documentRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public IDType findById(Long id) {
        return documentRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public IDType save(IDType IDType) {
        return documentRepo.save(IDType);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        documentRepo.deleteById(id);
    }

    @Override
    @Transactional
    public IDType update(Long id, IDType IDTypeNew) {
        IDType IDType = documentRepo.findById(id).orElse(null);
        Date fecha = new Date();
        IDType.setName(IDTypeNew.getName());
        IDType.setCreated_at(IDType.getCreated_at());
        IDType.setUpdated_at(fecha);
        return documentRepo.save(IDType);
    }
}
