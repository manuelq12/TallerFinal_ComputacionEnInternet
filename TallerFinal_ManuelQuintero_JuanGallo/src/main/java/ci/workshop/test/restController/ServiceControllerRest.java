package ci.workshop.test.restController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	@RequestMapping(path="/service/all")
	public List<Tmio1Servicio> findAll() {
		try {
			return (List<Tmio1Servicio>) serviceService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
