package ci.workshop.test.restController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
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
	@GetMapping("api/service/{id}")
	public Tmio1Servicio findByHash(@PathVariable("id") String hash) {
		try {
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
	@GetMapping("/api/service/date/ids")
	public TransactionBody<List<Tmio1ServicioPK>> findAllIDsNotRepeated() {
		TransactionBody<List<Tmio1ServicioPK>> tb = new TransactionBody<List<Tmio1ServicioPK>>();
		tb.setBody((List<Tmio1ServicioPK>) serviceService.findAllIDWithoutRepeatedFechaInicio());
		return tb;
	}
	@GetMapping("/api/service/date/all/{id}")
	public TransactionBody<List<Tmio1ServicioPK>> findAllIDsByFechaInicio(@PathVariable("id") String fecha) {
		String[] valor = fecha.split("-");
		
		TransactionBody<List<Tmio1ServicioPK>> tb = new TransactionBody<List<Tmio1ServicioPK>>();
		tb.setBody((List<Tmio1ServicioPK>) serviceService.findAllByFechaInicio(LocalDate.of(Integer.parseInt(valor[0]),Integer.parseInt(valor[1]),Integer.parseInt(valor[2]))));
		return tb;
	}
	
	@PostMapping("/api/service")
	public Tmio1Servicio addService(@RequestBody Tmio1Servicio service) throws Exception 
    {       
		serviceService.saveService(service);
		return service;
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
	
	@GetMapping("/api/service/bus/{id}")
	public TransactionBody<Tmio1Bus> findByBusId(@PathVariable("id")Integer idBus) {
		TransactionBody<Tmio1Bus> tb = new TransactionBody<Tmio1Bus>();
		tb.setBody(serviceService.findByBusId(idBus));
		return tb;
		
	}
	@GetMapping("/api/service/driver/{id}")
	public TransactionBody<Tmio1Conductore> findByDriverId(@PathVariable("id")String cedulaConductor) {
		TransactionBody<Tmio1Conductore> tb = new TransactionBody<Tmio1Conductore>();
		tb.setBody(serviceService.findByDriverId(cedulaConductor));
		return tb;
	}
	@GetMapping("/api/service/route/{id}")
	public TransactionBody<Tmio1Ruta> findByRouteId(@PathVariable("id")Integer idRuta) {
		TransactionBody<Tmio1Ruta> tb = new TransactionBody<Tmio1Ruta>();
		tb.setBody(serviceService.findByRouteId(idRuta));
		return tb;
	}
	
	@PatchMapping("/api/service")
	public Tmio1Servicio updateServicio(@RequestBody Tmio1Servicio servicio) {
		serviceService.saveService(servicio);
		return servicio;
	}
	@DeleteMapping("/api/service/{id}")
	public void deleteServicio (@PathVariable("id") String hash) {
		Tmio1Servicio ser = serviceService.findByHash(hash);
		serviceService.delete(ser);
	}
	
	
}
