package ci.workshop.test.restController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.service.TMioConductorService;

@RestController
public class ConductoresControllerRest {
	TMioConductorService conductorService;
	
	@Autowired
	public ConductoresControllerRest(TMioConductorService con) {
		conductorService= con;
	}
	@RequestMapping(path="/driver/{id}")
	public Tmio1Conductore findById(@PathVariable("id") String id) {
		int codigo;
		try {
			codigo= Integer.parseInt(id);
			return conductorService.finbyID(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="/driver/all")
	public List<Tmio1Conductore> findAll() {
		try {
			return (List<Tmio1Conductore>) conductorService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//    @PostMapping(path= "bus/save", consumes = "application/json", produces = "application/json")
	@PostMapping(path= "/driver/add")
	public ResponseEntity<Object> addConductore(@RequestBody Tmio1Conductore driver) throws Exception 
    {       
    	conductorService.saveDriver(driver);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(driver.getCedula()).toUri();
        return ResponseEntity.created(location).build();
    }
	

}
