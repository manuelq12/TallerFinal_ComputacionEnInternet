package ci.workshop.test.service;

import java.time.LocalDate;
import java.util.Optional;

import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;

public interface ITMioServicioService {

	public Tmio1Servicio saveService(Tmio1Servicio service);
	public boolean validNewService(Tmio1Servicio service);
	public Iterable<Tmio1Servicio> findAll();
	public Iterable<Tmio1ServicioPK> findAllID();
	public Tmio1Bus findByBusId(Integer busId);
	public Tmio1Conductore findByDriverId(String driverId);
	public Tmio1Ruta findByRouteId(Integer routeId);
	public Tmio1Servicio findByHash(String hash);
	public Iterable<Tmio1Bus> findAllBuses();
	public Iterable<Tmio1Conductore> findAllDrivers();
	public Iterable<Tmio1Ruta> findAllRoutes();
	public void delete (Tmio1Servicio servicio);
	public Iterable<Tmio1ServicioPK> findAllIDWithoutRepeatedFechaInicio();
	public Iterable<Tmio1ServicioPK> findAllByFechaInicio(LocalDate date);
}
