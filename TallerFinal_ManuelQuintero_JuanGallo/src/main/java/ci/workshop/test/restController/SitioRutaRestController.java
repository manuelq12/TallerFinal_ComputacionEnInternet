package ci.workshop.test.restController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ci.workshop.test.model.Tmio1SitiosRuta;
import ci.workshop.test.service.TMioSitioRutaService;

@RestController
public class SitioRutaRestController {
	TMioSitioRutaService TmisitioService;
	@Autowired
	public SitioRutaRestController(TMioSitioRutaService sitioService) {
		this.TmisitioService= sitioService;
	}
	

	@RequestMapping(path="/sitioRuta/all")
	public List<Tmio1SitiosRuta> findAll() {
		try {
			return (List<Tmio1SitiosRuta>) TmisitioService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="/sitioRuta/add")
	public ResponseEntity<Object> saveSitioRuta(@RequestBody Tmio1SitiosRuta sitio) {
		TmisitioService.saveSitio(sitio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sitio.getId()).toUri();
        return ResponseEntity.created(location).build();
	}
	
	// falta el editar
}
