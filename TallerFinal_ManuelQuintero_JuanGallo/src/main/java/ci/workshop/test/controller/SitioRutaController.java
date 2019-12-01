package ci.workshop.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ci.workshop.test.delegate.SitioRutaDelegate;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.model.Tmio1SitiosRuta;
import ci.workshop.test.model.Tmio1SitiosRutaPK;
import ci.workshop.test.service.TMioSitioRutaService;
import lombok.Data;

@Controller
@Data
public class SitioRutaController {
	
	SitioRutaDelegate sitioDelegate;
	
	@Autowired
	public SitioRutaController(SitioRutaDelegate sitioService){
		this.sitioDelegate= sitioService;
	}

	@GetMapping("/sitioRuta/")
	public String index(Model model) {
		model.addAttribute("sitiosRuta",sitioDelegate.findAll());
		return "/sitioRuta/index";
	}
	@GetMapping("/sitioRuta/add-sitioRuta")
	public String addSitioRuta(Model model){
		model.addAttribute("ruotes", sitioDelegate.getAllRoutes());	
		model.addAttribute("sitios", sitioDelegate.getAllSitio());
		return "/sitioRuta/add-sitioRuta";
	}
	
	@PostMapping("/sitioRuta/add-sitioRuta/")
	public String saveSitioRuta(@Valid @ModelAttribute("sitio") Tmio1SitiosRutaPK sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitioRuta",sitio);
			return "/sitioRuta/add-sitioRuta";
		}
		Tmio1SitiosRuta n= new Tmio1SitiosRuta();
		n.setId(sitio);
		n.setHash(sitio.hashCode());
		n.setTmio1Ruta1(sitioDelegate.findRutaByID(sitio.getIdRuta()));
		n.setTmio1Ruta1(sitioDelegate.findSitioByID(sitio.getIdSitio()));
		sitioDelegate.saveSitio(n);
		return "redirect:/sitio/";
	}
	@GetMapping("/sitioRuta/edit-sitio/{id}")
	public String editSitioRuta(@PathVariable("id") String id,Model model){
		model.addAttribute("sitioRuta",sitioDelegate.getByID(id));	
		return "/sitioRuta/edit-sitio";
	}
	
	@PostMapping("/sitioRuta/edit-sitio/")
	public String editSitioRuta(@Valid @ModelAttribute("sitio") Tmio1SitiosRutaPK sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitio",sitio);
			return "/sitio/edit-sitio";
		}
		sitioDelegate.updateSitio(sitio);
		return "redirect:/sitio/";
	}
	
	@DeleteMapping("/sitioRuta/delete-sitio/{id}")
	public String deleteSitioRuta(@PathVariable("id") String id,Model model){
		sitioDelegate.removeSitio(sitioDelegate.findById(Integer.parseInt(id)));
		return "/sitio/";
	}
}
