package com.bosa.eshop.service.impl;

import com.bosa.eshop.model.Manufacturer;
import com.bosa.eshop.repository.impl.InMemoryManufacturerRepository;
import com.bosa.eshop.repository.jpa.ManufacturerRepository;
import com.bosa.eshop.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
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
        return Optional.of(manufacturerRepository.save(new Manufacturer(name, address)));
    }

    @Override
    public void deleteById(Long id ) {
        manufacturerRepository.deleteById(id);
    }
}
