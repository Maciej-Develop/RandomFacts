package maciej.develop.random_facts.repository;

import org.springframework.data.repository.CrudRepository;

import maciej.develop.random_facts.model.Fact;

public interface FactRepository extends CrudRepository<Fact, Long> {

}
