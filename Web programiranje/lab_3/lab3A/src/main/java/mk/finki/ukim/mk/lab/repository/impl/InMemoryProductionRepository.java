package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductionRepository {
    public List<Production> findAll(){
        return DataHolder.productions;
    }

    public Optional<Production> findById( Long production ) {
        return DataHolder.productions.stream()
                .filter(p->p.getId().equals(production)).findFirst();
    }
}
