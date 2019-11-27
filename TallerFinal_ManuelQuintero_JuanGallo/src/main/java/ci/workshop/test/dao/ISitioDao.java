package ci.workshop.test.dao;

import java.util.List;

import ci.workshop.test.model.Tmio1Sitio;

public interface ISitioDao {
	public void save(Tmio1Sitio sitio);
	public List<Tmio1Sitio> findAll();
	public void update(Tmio1Sitio sitio);
	public void delete(Tmio1Sitio sitio);
	public Tmio1Sitio findByID(int sitio);
}
