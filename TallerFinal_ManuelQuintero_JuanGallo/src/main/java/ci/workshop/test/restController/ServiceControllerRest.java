package ci.workshop.test.restController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;
import ci.workshop.test.service.TMioServicioService;

@RestController
public class ServiceControllerRest {
	TMioServicioService serviceService;
	@Autowired
	public ServiceControllerRest(TMioServicioService service) {
		serviceService= service;
	}
	@RequestMapping(path="/service/{id}")
	public Tmio1Servicio findById(@PathVariable("id") String hash) {
		int codigo;
		try {
			codigo= Integer.parseInt(hash);
			return serviceService.findByHash(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/api/service/all")
	public TransactionBody<List<Tmio1Servicio>> findAll() {
		TransactionBody<List<Tmio1Servicio>> tb = new TransactionBody<List<Tmio1Servicio>>();
		tb.setBody((List<Tmio1Servicio>) serviceService.findAll());
		return tb;
	}
	@GetMapping("/api/service/buses")
	public TransactionBody<List<Tmio1Bus>> findAllBuses() {
		TransactionBody<List<Tmio1Bus>> tb = new TransactionBody<List<Tmio1Bus>>();
		tb.setBody((List<Tmio1Bus>) serviceService.findAllBuses());
		return tb;
	}
	@GetMapping("/api/service/drivers")
	public TransactionBody<List<Tmio1Conductore>> findAllDrivers() {
		TransactionBody<List<Tmio1Conductore>> tb = new TransactionBody<List<Tmio1Conductore>>();
		tb.setBody((List<Tmio1Conductore>) serviceService.findAllDrivers());
		return tb;
	}
	@GetMapping("/api/service/routes")
	public TransactionBody<List<Tmio1Ruta>> findAllRoutes() {
		TransactionBody<List<Tmio1Ruta>> tb = new TransactionBody<List<Tmio1Ruta>>();
		tb.setBody((List<Tmio1Ruta>) serviceService.findAllRoutes());
		return tb;
	}
	@GetMapping("/api/service/ids")
	public TransactionBody<List<Tmio1ServicioPK>> findAllIDs() {
		TransactionBody<List<Tmio1ServicioPK>> tb = new TransactionBody<List<Tmio1ServicioPK>>();
		tb.setBody((List<Tmio1ServicioPK>) serviceService.findAllID());
		return tb;
	}
	
	@PostMapping(path= "/service/add")
	public ResponseEntity<Object> addService(@RequestBody Tmio1Servicio service) throws Exception 
    {       
		serviceService.saveService(service);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.getHash()).toUri();
        return ResponseEntity.created(location).build();
    }
	@RequestMapping(path="/service/date/{date}")
	public List<Tmio1ServicioPK> findByFechas(@PathVariable("date") String date) {
		try {
			return (List<Tmio1ServicioPK>) serviceService.findAllByFechaInicio(LocalDate.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// FALTA EL EDIT 
	
	
}
