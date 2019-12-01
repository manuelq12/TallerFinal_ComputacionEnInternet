package ci.workshop.test.restController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.service.TMioSitioService;

@RestController
public class SitioControllerRest {
	TMioSitioService sitioService;
	@Autowired
	public SitioControllerRest(TMioSitioService sitioService) {
		this.sitioService= sitioService;
	}
	

	@RequestMapping(path="api/sitio/all")
	public TransactionBody<List<Tmio1Sitio>> findAll() {
		TransactionBody<List<Tmio1Sitio>> tb = new TransactionBody<List<Tmio1Sitio>>();
		tb.setBody((List<Tmio1Sitio>) sitioService.findAll());
		return tb;
	}
	
	@RequestMapping(path="api/sitio/{id}")
	public TransactionBody<Tmio1Sitio> findById(@PathVariable("id")int id) {
		TransactionBody<Tmio1Sitio> tb = new TransactionBody<Tmio1Sitio>();
		tb.setBody(sitioService.findById(id));
		return tb;
	}
	@RequestMapping(path="api/sitio/add")
	public ResponseEntity<Object> saveSitio(@RequestBody Tmio1Sitio sitio) {
		sitioService.saveSitio(sitio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sitio.getId()).toUri();
        return ResponseEntity.created(location).build();
	}
	@PostMapping("api/sitio/edit")
	public Tmio1Sitio updateSitio(@RequestBody Tmio1Sitio sitio) {
		sitioService.updateSitio(sitio);
		return sitio;
	}
	
	
	
	//falta el editar o el update
	
}
