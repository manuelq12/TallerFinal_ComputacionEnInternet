package ci.workshop.test.restController;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(path="/bus/{id}")
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
	@RequestMapping(value="/api/bus/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Tmio1Bus> findAll() {

		return (List<Tmio1Bus>) busService.findAll();
	}
	
//	@RequestMapping(value="/api/bus/buses/", method = RequestMethod.GET)
//	@ResponseBody
//	public TransactionBody<List<String>> findAllTypes() {
//		TransactionBody<List<String>> tb = new TransactionBody<List<String>>();
//		tb.setBody((List<String>) busService.findAllTypes());
//		return tb;
//	}
	@GetMapping("/api/bus/buses/")
	public List<String> findAllTypes() {
		return (List<String>) busService.findAllTypes();
	}
	
	@GetMapping("/api/bus/buses/")
	public TransactionBody<List<String>> findAllTypesTP() {
		TransactionBody<List<String>> tb = new TransactionBody<List<String>>();
		tb.setBody((List<String>) busService.findAllTypes());
		return tb;
	}
	
//    @PostMapping(path= "bus/save", consumes = "application/json", produces = "application/json")
	@PostMapping(path= "/bus/")
	public ResponseEntity<Object> addBus(@RequestBody Tmio1Bus bus) throws Exception 
    {       
    	busService.saveBus(bus);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bus.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
