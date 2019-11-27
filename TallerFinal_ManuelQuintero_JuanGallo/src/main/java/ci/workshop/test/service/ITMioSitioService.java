package ci.workshop.test.service;

import ci.workshop.test.model.Tmio1Sitio;

public interface ITMioSitioService {
	public Iterable<Tmio1Sitio> findAll();
	public Tmio1Sitio saveSitio(Tmio1Sitio sitio);
	public Tmio1Sitio updateSitio(Tmio1Sitio sitio);
	public Tmio1Sitio removeSitio(Tmio1Sitio sitio);
	public Tmio1Sitio findById(int id);
}
