package ci.workshop.test.repository;

import org.springframework.data.repository.CrudRepository;

import ci.workshop.test.model.Tmio1Conductore;


public interface ConductoresRepository extends CrudRepository<Tmio1Conductore, String> {

}
