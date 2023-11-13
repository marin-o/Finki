package com.bosa.eshop.repository;

import com.bosa.eshop.bootstrap.DataHolder;
import com.bosa.eshop.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById( Long id){
        return DataHolder.manufacturers
                .stream()
                .filter(r->r.getId().equals(id))
                .findFirst();
    }

    public Optional<Manufacturer> findByName( String name ) {
        return DataHolder.manufacturers.stream().filter(m -> m.getName().equals(name)).findFirst();
    }

    public Optional<Manufacturer> save( String name, String address){
        DataHolder.manufacturers.removeIf(m->m.getName().equals(name));
        Manufacturer m = new Manufacturer(name, address);
        DataHolder.manufacturers.add(m);
        return Optional.of(m);
    }

    public void deleteById(Long id){
        DataHolder.products.removeIf(i->i.getId().equals(id));
    }
}
