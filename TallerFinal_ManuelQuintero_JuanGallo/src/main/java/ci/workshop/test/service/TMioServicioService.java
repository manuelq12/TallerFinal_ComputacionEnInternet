package ci.workshop.test.service;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.workshop.test.dao.BusesDao;
import ci.workshop.test.dao.ConductoresDao;
import ci.workshop.test.dao.RutasDao;
import ci.workshop.test.dao.ServiciosDao;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;


@Service
public class TMioServicioService implements ITMioServicioService{
	
	@Autowired
	private ServiciosDao repository;
	@Autowired
	private ConductoresDao driverRepos;
	@Autowired
	private RutasDao routeRepos;
	@Autowired
	private BusesDao busRepos;
	
	
	@Override
	public boolean validNewService(Tmio1Servicio service) {
		// TODO Auto-generated method stub
	boolean  valid = false;
	boolean busExist = false;
	boolean driverExist = false;
	boolean routeExist = false;
	boolean driverDateInicio = false;
	boolean driverDateFinal = false;
	boolean serviceDateCorrespond = false;
	if(service != null) {
			if(service.getTmio1Bus() != null) {
				
				busExist =  !busRepos.findById(service.getId().getIdBus()).equals(null);

				if(service.getTmio1Conductore() != null) {
					driverExist = !driverRepos.findById(service.getId().getCedulaConductor()).equals(null);

					if(service.getId().getFechaInicio() != null &&  service.getId().getFechaFin() != null && driverExist
							&& service.getTmio1Conductore().getFechaContratacion() != null) {
						
						driverDateInicio = service.getId().getFechaInicio().isAfter(service.getTmio1Conductore().getFechaContratacion());
						driverDateFinal = service.getId().getFechaFin().isAfter(service.getTmio1Conductore().getFechaContratacion());
						serviceDateCorrespond = service.getId().getFechaInicio().isBefore( service.getId().getFechaFin());
						
						if(service.getTmio1Ruta()!=null) {
							routeExist = !routeRepos.findById(service.getTmio1Ruta().getId()).equals(null);
						}
						
					}
						
				}
				
			}
		}
		if(busExist && driverExist && routeExist && driverDateInicio && driverDateFinal
				&& serviceDateCorrespond) {
			valid  = true;
		}
		return valid;
	}
	
	@Override
	@Transactional
	public Tmio1Servicio saveService(Tmio1Servicio service) {
		// TODO Auto-generated method stub
		boolean save = validNewService(service);
		if(save) {
			repository.save(service);
			return service;
		}
		return null;
	}

	@Override
	public Iterable<Tmio1Servicio> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Iterable<Tmio1Bus> findAllBuses() {
		// TODO Auto-generated method stub
		return busRepos.findAll();
	}

	@Override
	public Iterable<Tmio1Conductore> findAllDrivers() {
		// TODO Auto-generated method stub
		return driverRepos.findAll();
	}

	@Override
	public Iterable<Tmio1Ruta> findAllRoutes() {
		// TODO Auto-generated method stub
		return routeRepos.findAll();
	}

	@Override
	public Iterable<Tmio1ServicioPK> findAllID() {
		// TODO Auto-generated method stub
		Iterable<Tmio1Servicio> servicios = findAll();
		ArrayList<Tmio1ServicioPK> id = new ArrayList<Tmio1ServicioPK>();
		for (Tmio1Servicio s : servicios) {
			id.add(s.getId());
		}
		
		return (Iterable<Tmio1ServicioPK>)id;
	}

	@Override
	public Tmio1Bus findByBusId(Integer busId) {
		// TODO Auto-generated method stub
		return busRepos.findById(busId);
	}

	@Override
	public Tmio1Conductore findByDriverId(String driverId) {
		// TODO Auto-generated method stub
		return driverRepos.findById(driverId);
	}

	@Override
	public Tmio1Ruta findByRouteId(Integer routeId) {
		// TODO Auto-generated method stub
		return routeRepos.findById(routeId);
	}

	@Override
	public Tmio1Servicio findByHash(String hash) {
		// TODO Auto-generated method stub
		Tmio1Servicio respuesta = null;
		
		Iterable<Tmio1Servicio> todos = findAll();

		for (Tmio1Servicio s : todos) {	
			if(s.getHash().equals(hash)){
				respuesta = s;
				break;
			}
		}
		
		
		return respuesta;
	}

	@Override
	public void delete(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub
		repository.delete(servicio);
		
	}

	@Override
	public Iterable<Tmio1ServicioPK> findAllIDWithoutRepeatedFechaInicio() {
		Iterable<Tmio1Servicio> servicios = findAll();
		ArrayList<Tmio1ServicioPK> id = new ArrayList<Tmio1ServicioPK>();
		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
		LocalDate temp = null;
		for (Tmio1Servicio s : servicios) {
			temp = s.getId().getFechaInicio();
			if(!dates.contains(temp)) {
				dates.add(temp);
				id.add(s.getId());
			}
		}
		
		return (Iterable<Tmio1ServicioPK>)id;
	}

	@Override
	public Iterable<Tmio1ServicioPK> findAllByFechaInicio(LocalDate date) {
		// TODO Auto-generated method stub
		Iterable<Tmio1Servicio> servicios = findAll();
		ArrayList<Tmio1ServicioPK> id = new ArrayList<Tmio1ServicioPK>();
		LocalDate temp = null;
		for (Tmio1Servicio s : servicios) {
			temp = s.getId().getFechaInicio();
			if(date.equals(temp)) {
				id.add(s.getId());
			}
		}
		return (Iterable<Tmio1ServicioPK>)id;

	}
	
	
	
	

	
	

}
