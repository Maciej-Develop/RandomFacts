package maciej.develop.random_facts.service;

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

    public Iterable<Fact> getAll() {
        return factRepository.findAll();
    }

    public Fact getById(Long id) {
        return factRepository.findById(id).get();
    }

    public long getSize() {
        return factRepository.count();
    }
}
