package ci.workshop.test.dao;



import java.time.LocalDate;
import java.util.List;

import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;


public interface IConductoresDao{

	public void save(Tmio1Conductore entity);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	public Tmio1Conductore findById(String cedula);
	public List<Tmio1Conductore> findAll();
	public List<Tmio1Conductore> findByNombre(String nombre);
	public List<Tmio1Conductore> findByApellido(String apellido);
}
