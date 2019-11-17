package ci.workshop.test.restController;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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
    @PostMapping(path= "bus/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addBus(@RequestBody Tmio1Bus bus) throws Exception 
    {       
    	busService.saveBus(bus);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bus.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
