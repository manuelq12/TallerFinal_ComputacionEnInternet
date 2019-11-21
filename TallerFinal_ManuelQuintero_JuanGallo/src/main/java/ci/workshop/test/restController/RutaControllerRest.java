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

import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.service.TMioRutaService;

@RestController
public class RutaControllerRest {
	TMioRutaService rutaService;
	@Autowired
	public RutaControllerRest(TMioRutaService rutaServ) {
		rutaService= rutaServ;
	}
	
	@RequestMapping(path="/rute/{id}")
	public Tmio1Ruta findById(@PathVariable("id") String id) {
		int codigo;
		try {
			codigo= Integer.parseInt(id);
			return rutaService.findByID(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="/rute/all")
	public List<Tmio1Ruta> findAll() {
		try {
			return (List<Tmio1Ruta>) rutaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//    @PostMapping(path= "bus/save", consumes = "application/json", produces = "application/json")
	@PostMapping(path= "/rute/add")
	public ResponseEntity<Object> addRuta(@RequestBody Tmio1Ruta rute) throws Exception 
    {       
		rutaService.saveRoute(rute);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rute.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
	
}
