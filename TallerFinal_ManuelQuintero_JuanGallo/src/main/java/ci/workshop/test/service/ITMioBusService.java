package ci.workshop.test.service;

import ci.workshop.test.model.Tmio1Bus;

public interface ITMioBusService {

	public Tmio1Bus saveBus(Tmio1Bus bus);
	public boolean validNewBus(Tmio1Bus bus);
	public Iterable<Tmio1Bus> findAll();
	public Iterable<String> findAllTypes();
	
}
