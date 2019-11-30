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

import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.service.TMioSitioRutaService;
import lombok.Data;

@Controller
@Data
public class SitioRutaController {
	
TMioSitioRutaService sitioRService;
	
	@Autowired
	public SitioRutaController(TMioSitioRutaService sitioService){
		this.sitioRService= sitioService;
	}

	@GetMapping("/sitioRuta/")
	public String index(Model model) {
		model.addAttribute("sitiosRuta",sitioRService.findAll());
		return "/sitioRuta/index";
	}
	@GetMapping("/sitioRuta/add-sitioRuta")
	public String addSitioRuta(Model model){
		return "/sitioRuta/add-sitioRuta";
	}
	
//	@PostMapping("/sitio/add-sitioRuta/")
//	public String saveSitioRuta(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
//		if(bindingResult.hasErrors()) {
//			model.addAttribute("sitioRuta",sitio);
//			return "/sitio/add-sitioRuta";
//		}
//		sitioService.saveSitio(sitio);
//		return "redirect:/sitio/";
//	}
//	@GetMapping("/sitioRuta/edit-sitio/{id}")
//	public String editSitioRuta(@PathVariable("id") String id,Model model){
//		model.addAttribute("sitioRuta",sitioService.findById(Integer.parseInt(id)));	
//		return "/sitioRuta/edit-sitio";
//	}
//	@DeleteMapping("/sitioRuta/delete-sitio/{id}")
//	public String deleteSitioRuta(@PathVariable("id") String id,Model model){
//		sitioService.removeSitio(sitioService.findById(Integer.parseInt(id)));
//		return "/sitio/";
//	}
//	
//	@PostMapping("/sitioRuta/edit-sitio/")
//	public String editSitioRuta(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
//		if(bindingResult.hasErrors()) {
//			model.addAttribute("sitio",sitio);
//			return "/sitio/edit-sitio";
//		}
//		sitioService.updateSitio(sitio);
//		return "redirect:/sitio/";
//	}
}
