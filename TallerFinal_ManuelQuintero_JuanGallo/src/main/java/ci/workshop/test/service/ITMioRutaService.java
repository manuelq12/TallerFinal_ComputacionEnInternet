package ci.workshop.test.service;

import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1SitiosRuta;

public interface ITMioRutaService {
	public Tmio1Ruta saveRoute (Tmio1Ruta route);
	public boolean validNewRoute (Tmio1Ruta route);
	public Iterable<Tmio1Ruta> findAll();
	public Iterable<String> findAllStates();
	public Tmio1Ruta findByID(int id);

}
