package ci.workshop.test.repository;

import org.springframework.data.repository.CrudRepository;

import ci.workshop.test.model.Tmio1Bus;

public interface BusesRepository extends CrudRepository<Tmio1Bus, Integer> {
	
}
