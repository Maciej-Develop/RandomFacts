package maciej.develop.random_facts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import maciej.develop.random_facts.model.Fact;
import maciej.develop.random_facts.repository.FactRepository;

@Service
public class FactService {

    @Autowired
    private FactRepository factRepository;

    public void create(Fact fact) {
        factRepository.save(fact);
    }

    public List<Fact> getAll() {
        List<Fact> facts = new ArrayList<>();
        for (Fact fact : factRepository.findAll()) {
            facts.add(fact);
        }
        return facts;
    }

    public Fact getById(Long id) {
        return factRepository.findById(id).get();
    }

    public List<Long> getIds() {
        List<Long> ids = new ArrayList<>();
        for (Long id : factRepository.findAllIds()) {
            ids.add(id);
        }
        return ids;
    }
}
