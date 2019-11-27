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

import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1ServicioPK;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.service.TMioSitioService;
import lombok.Data;

@Controller
@Data
public class SitioController {
	
	TMioSitioService sitioService;
	
	@Autowired
	public SitioController(TMioSitioService sitioService){
		this.sitioService= sitioService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "/customLogin";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/customLogin";
	}
	@GetMapping("/sitio/")
	public String index(Model model) {
		model.addAttribute("sitios",sitioService.findAll());
		return "/sitio/index";
	}
	@GetMapping("/sitio/add-sitio")
	public String addSitio(Model model){
		return "/sitio/add-sitio";
	}
	
	@PostMapping("/sitio/add-sitio/")
	public String saveSitio(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitio",sitio);
			return "/sitio/add-sitio";
		}
		sitioService.saveSitio(sitio);
		return "redirect:/sitio/";
	}
	@GetMapping("/sitio/edit-sitio/{id}")
	public String editSitio(@PathVariable("id") String id,Model model){
		model.addAttribute("sitio",sitioService.findById(Integer.parseInt(id)));	
		return "/sitio/edit-sitio";
	}
	@DeleteMapping("/sitio/delete-sitio/{id}")
	public String deleteSitio(@PathVariable("id") String id,Model model){
		sitioService.removeSitio(sitioService.findById(Integer.parseInt(id)));
		return "/sitio/";
	}
	
	@PostMapping("/sitio/edit-sitio/")
	public String editSitio(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitio",sitio);
			return "/sitio/edit-sitio";
		}
		sitioService.updateSitio(sitio);
		return "redirect:/sitio/";
	}
}
