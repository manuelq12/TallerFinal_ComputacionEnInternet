package ci.workshop.test.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.model.Tmio1SitiosRuta;
import ci.workshop.test.model.Tmio1SitiosRutaPK;
import ci.workshop.test.service.TMioRutaService;
import ci.workshop.test.service.TMioSitioRutaService;
import ci.workshop.test.service.TMioSitioService;

@RestController
public class SitioRutaRestController {
	TMioSitioRutaService sitioRutaService;
	TMioRutaService rutaService;
	TMioSitioService sitioService;
	
	@Autowired
	public SitioRutaRestController(TMioSitioRutaService sitioRutaService,TMioRutaService rutaService, TMioSitioService sitioService) {
		this.sitioRutaService= sitioRutaService;
		this.rutaService=rutaService;
		this.sitioService= sitioService;
	}
	

	@GetMapping(path="api/sitioRuta/all")
	public TransactionBody<List<Tmio1SitiosRuta>> findAll() {
		try {
			TransactionBody<List<Tmio1SitiosRuta>> tb = new TransactionBody<List<Tmio1SitiosRuta>>();
			tb.setBody((List<Tmio1SitiosRuta>)sitioRutaService.findAll());
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(path="api/sitioRuta/routes")
	public TransactionBody<List<Tmio1Ruta>> findAllRoutes() {
		try {
			TransactionBody<List<Tmio1Ruta>> tb = new TransactionBody<List<Tmio1Ruta>>();
			tb.setBody((List<Tmio1Ruta>)rutaService.findAll());
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="api/sitioRuta/sitios")
	public TransactionBody<List<Tmio1Sitio>> findAllSitio() {
		try {
			TransactionBody<List<Tmio1Sitio>> tb = new TransactionBody<List<Tmio1Sitio>>();
			tb.setBody((List<Tmio1Sitio>)sitioService.findAll());
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/api/sitioRuta")
	public Tmio1SitiosRuta saveSitioRuta(@RequestBody Tmio1SitiosRuta sitio) {
		sitioRutaService.saveSitio(sitio);
        return sitio;
	}
	
	@RequestMapping(path="api/sitioRuta/{id}")
	public TransactionBody<Tmio1SitiosRuta> findByID(@PathVariable("id") String id) {
		try {
			TransactionBody<Tmio1SitiosRuta> tb = new TransactionBody<Tmio1SitiosRuta>();
			tb.setBody(sitioRutaService.findById(id));
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path="api/sitioRuta/pk/{id}")
	public TransactionBody<Tmio1SitiosRutaPK> findByIDPK(@PathVariable("id") String id) {
		try {
			TransactionBody<Tmio1SitiosRutaPK> tb = new TransactionBody<Tmio1SitiosRutaPK>();
			tb.setBody(sitioRutaService.findById(id).getId());
			return tb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@DeleteMapping("/api/sitioRuta/delete/{id}")
	public Tmio1SitiosRuta deleteSitioRuta(@PathVariable("id") String id)
    {       
		Tmio1SitiosRuta r= sitioRutaService.findById(id);
		sitioRutaService.removeSitioRuta(r);
		return r;
    }
}
