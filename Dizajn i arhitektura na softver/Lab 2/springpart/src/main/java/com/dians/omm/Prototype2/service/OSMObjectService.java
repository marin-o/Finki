package com.dians.omm.Prototype2.service;

import com.dians.omm.Prototype2.model.OSMObject;

import java.util.List;
import java.util.Optional;

public interface OSMObjectService {
    public List<OSMObject> findAll();
    public Optional<OSMObject> findById(Long id);
}
