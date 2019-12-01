package ci.workshop.test.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.service.TMioRutaService;

@RestController
public class RutaControllerRest {
	TMioRutaService rutaService;
	@Autowired
	public RutaControllerRest(TMioRutaService rutaServ) {
		rutaService= rutaServ;
	}
	@RequestMapping(path="api/rute/{id}")
	public TransactionBody<Tmio1Ruta> findById(@PathVariable("id") String id) {
		int codigo;
		try {
			codigo= Integer.parseInt(id);
			TransactionBody<Tmio1Ruta> tb = new TransactionBody<Tmio1Ruta>();
			tb.setBody(rutaService.findByID(codigo));
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="api/rute/all")
	public TransactionBody<List<Tmio1Ruta>> findAll() {
		try {
			TransactionBody<List<Tmio1Ruta>> tb = new TransactionBody<List<Tmio1Ruta>>();
			tb.setBody((List<Tmio1Ruta>) rutaService.findAll());
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="api/rute/states")
	public TransactionBody<List<String>> findStates() {
		try {
			TransactionBody<List<String>> tb = new TransactionBody<List<String>>();
			tb.setBody((List<String>) rutaService.findAllStates());
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping(path= "api/rute")
	public Tmio1Ruta addRuta (@RequestBody Tmio1Ruta rute) throws Exception 
    {       
		rutaService.saveRoute(rute);
		return rute;
		
    }
	@DeleteMapping("/api/rute/delete/{id}")
	public Tmio1Ruta deleteRuta(@PathVariable("id") String id)
    {       
		Tmio1Ruta r= rutaService.findByID(Integer.parseInt(id));
		rutaService.removeRuta(r);
		return r;
    }
}
