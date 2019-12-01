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

import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.service.TMioConductorService;

@RestController
public class ConductoresControllerRest {
	TMioConductorService conductorService;
	
	@Autowired
	public ConductoresControllerRest(TMioConductorService con) {
		conductorService= con;
	}
	@RequestMapping(path="api/driver/{id}")
	public TransactionBody<Tmio1Conductore> findById(@PathVariable("id") String id) {
		try {
			TransactionBody<Tmio1Conductore> tb = new TransactionBody<Tmio1Conductore>();
			tb.setBody(conductorService.finbyID(id));
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="api/driver/all")
	public TransactionBody<List<Tmio1Conductore>> findAll() {
		try {
			TransactionBody<List<Tmio1Conductore>> tb = new TransactionBody<List<Tmio1Conductore>>();
			tb.setBody((List<Tmio1Conductore>) conductorService.findAll());
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(path= "api/driver")
	public Tmio1Conductore addConductore(@RequestBody Tmio1Conductore driver) throws Exception 
    {       
		conductorService.saveDriver(driver);
		return driver;
    }
	

}
