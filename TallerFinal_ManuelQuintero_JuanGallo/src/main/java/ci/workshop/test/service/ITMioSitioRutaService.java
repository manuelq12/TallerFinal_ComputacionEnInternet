package ci.workshop.test.service;

import ci.workshop.test.model.Tmio1SitiosRuta;

public interface ITMioSitioRutaService {
	public Iterable<Tmio1SitiosRuta> findAll();
	public Tmio1SitiosRuta saveSitio(Tmio1SitiosRuta sitio);
	public Tmio1SitiosRuta updateSitioRuta(Tmio1SitiosRuta sitio);
	public Tmio1SitiosRuta removeSitioRuta(Tmio1SitiosRuta sitio);
}
