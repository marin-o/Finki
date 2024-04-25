package com.dians.omm.Prototype2.service.impl;

import com.dians.omm.Prototype2.model.OSMObject;
import com.dians.omm.Prototype2.repository.OSMObjectRepository;
import com.dians.omm.Prototype2.service.OSMObjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OSMObjectServiceImpl implements OSMObjectService {
    private final OSMObjectRepository repository;

    public OSMObjectServiceImpl(OSMObjectRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<OSMObject> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<OSMObject> findById(Long id){
        return repository.findById(id);
    }
}
