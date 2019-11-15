package ci.workshop.test.repository;



import org.springframework.data.repository.CrudRepository;

import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;


public interface ServiciosRepository extends CrudRepository<Tmio1Servicio, Tmio1ServicioPK> {

}
