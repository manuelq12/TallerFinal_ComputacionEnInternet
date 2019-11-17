package ci.workshop.test.dao;

import java.util.List;

import ci.workshop.test.model.Tmio1SitiosRuta;

public interface ISitioRutaDao {
	public void save(Tmio1SitiosRuta sitio);
	public List<Tmio1SitiosRuta> findAll();
	public void update(Tmio1SitiosRuta sitio);
	public void delete(Tmio1SitiosRuta sitio);
}
