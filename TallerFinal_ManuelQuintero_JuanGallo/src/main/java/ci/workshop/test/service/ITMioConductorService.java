package ci.workshop.test.service;

import ci.workshop.test.model.Tmio1Conductore;

public interface ITMioConductorService {

	public Tmio1Conductore saveDriver(Tmio1Conductore driver);
	public boolean validNewDriver(Tmio1Conductore driver);
	public Iterable<Tmio1Conductore> findAll();
	
}
