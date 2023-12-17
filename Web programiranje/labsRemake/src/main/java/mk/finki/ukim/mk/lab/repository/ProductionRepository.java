package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductionRepository {
    public List<Production> findAll(){
        return DataHolder.productions;
    }

    public Production findById( Long productionId ) {
        return DataHolder.productions.stream().filter(p->p.getId().equals(productionId)).findFirst().get();
    }
}
