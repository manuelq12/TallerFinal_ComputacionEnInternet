package ci.workshop.test.dao;



import java.time.LocalDate;
import java.util.List;

import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;


public interface IServiciosDao{
	public void save(Tmio1Servicio entity);
	public void update(Tmio1Servicio entity);
	public void delete(Tmio1Servicio entity);
	public Tmio1Servicio findById(Tmio1ServicioPK codigo);
	public List<Tmio1Servicio> findAll();
	List<Object[]> findServiceConductor(LocalDate fechaVigente);
	List<Tmio1Ruta> findRutaFechas(LocalDate fechaFinal);
	List<Tmio1Bus> busSameDayService(LocalDate diaEspecifico);

}
