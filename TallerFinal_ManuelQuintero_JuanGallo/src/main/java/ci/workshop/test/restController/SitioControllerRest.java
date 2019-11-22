package ci.workshop.test.restController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.service.TMioSitioService;

@RestController
public class SitioControllerRest {
	TMioSitioService sitioService;
	@Autowired
	public SitioControllerRest(TMioSitioService sitioService) {
		this.sitioService= sitioService;
	}
	

	@RequestMapping(path="/sitio/all")
	public List<Tmio1Sitio> findAll() {
		try {
			return (List<Tmio1Sitio>) sitioService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="/sitio/add")
	public ResponseEntity<Object> saveSitio(@RequestBody Tmio1Sitio sitio) {
		sitioService.saveSitio(sitio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sitio.getId()).toUri();
        return ResponseEntity.created(location).build();
	}
	
	//falta el editar o el update
	
}
