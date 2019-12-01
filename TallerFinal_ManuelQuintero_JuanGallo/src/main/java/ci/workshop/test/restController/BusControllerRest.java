package ci.workshop.test.restController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.service.TMioBusService;

@RestController
public class BusControllerRest {

	TMioBusService busService;
	
	@Autowired
	public BusControllerRest(TMioBusService busS) {
		busService=busS;
	}
	
	@RequestMapping(path="api/bus/{id}")
	public TransactionBody<Tmio1Bus> findById(@PathVariable("id") String id) {
		int codigo;
		TransactionBody<Tmio1Bus> tb = new TransactionBody<Tmio1Bus>();
		try {
			codigo= Integer.parseInt(id);
			Tmio1Bus bus = busService.findById(codigo);
			tb.setBody(bus);
			return tb;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/api/bus/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public TransactionBody<List<Tmio1Bus>> findAllTP() {
		TransactionBody<List<Tmio1Bus>> tb = new TransactionBody<List<Tmio1Bus>>();
		tb.setBody((List<Tmio1Bus>) busService.findAll());
		return tb;

	}
	
	@GetMapping("/api/bus/types")
	public TransactionBody<List<String>> findAllTypesTP() {
		TransactionBody<List<String>> tb = new TransactionBody<List<String>>();
		tb.setBody((List<String>) busService.findAllTypes());
		return tb;
	}
	
	@PostMapping("/api/bus")
	public Tmio1Bus addBus (@RequestBody Tmio1Bus newBus) throws Exception 
    {       
		System.out.println("Llego");
		busService.saveBus(newBus);
		return newBus;
    }
}
