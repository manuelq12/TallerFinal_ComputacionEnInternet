package ci.workshop.test.restController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public Tmio1Bus findById(@PathVariable("id") String id) {
		int codigo;
		try {
			codigo= Integer.parseInt(id);
			return busService.findById(codigo);
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
	
	@GetMapping("/api/bus/types/")
	public TransactionBody<List<String>> findAllTypesTP() {
		TransactionBody<List<String>> tb = new TransactionBody<List<String>>();
		tb.setBody((List<String>) busService.findAllTypes());
		return tb;
	}
	
//    @PostMapping(path= "bus/save", consumes = "application/json", produces = "application/json")
	@PostMapping("/api/bus/aBus")
	public TransactionBody<Tmio1Bus> addBus (@RequestBody TransactionBody<Tmio1Bus> newBus) throws Exception 
    {       
		Tmio1Bus bus = newBus.getBody();
		System.out.println("NADAAAA !"+bus.getMarca());
		System.out.println(bus);
    	busService.saveBus(bus);
    	TransactionBody<Tmio1Bus> transaction = new TransactionBody<Tmio1Bus>();
    	transaction.setBody(bus);
//		ResponseEntity<TransactionBody<Tmio1Bus>> response = new ResponseEntity<TransactionBody<Tmio1Bus>>(transaction,
//				HttpStatus.SEE_OTHER);
		return transaction;
    }
}
