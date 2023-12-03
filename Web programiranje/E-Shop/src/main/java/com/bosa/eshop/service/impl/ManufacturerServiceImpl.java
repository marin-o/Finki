package com.bosa.eshop.service.impl;

import com.bosa.eshop.model.Manufacturer;
import com.bosa.eshop.repository.InMemoryManufacturerRepository;
import com.bosa.eshop.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final InMemoryManufacturerRepository manufacturerRepository;
    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById( Long id ) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByName( String name ) {
        return manufacturerRepository.findByName( name );
    }

    @Override
    public Optional<Manufacturer> save( String name, String address ) {
        return manufacturerRepository.save(name, address);
    }

    @Override
    public boolean deleteById(Long id ) {
        return manufacturerRepository.deleteById(id);
    }
}
