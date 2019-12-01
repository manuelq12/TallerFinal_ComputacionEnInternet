package ci.workshop.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ci.workshop.test.delegate.SitioDelegate;
import ci.workshop.test.model.Tmio1Sitio;
import lombok.Data;

@Controller
@Data
public class SitioController {
	SitioDelegate sitioDelegate;
	@Autowired
	public SitioController(SitioDelegate sitioService){
		this.sitioDelegate= sitioService;
	}
	@GetMapping("/sitio/")
	public String index(Model model) {
		model.addAttribute("sitios",sitioDelegate.findAll());
		return "/sitio/index";
	}
	@GetMapping("/sitio/add-sitio")
	public String addSitio(Model model){
		model.addAttribute("sitio",new Tmio1Sitio());
		return "/sitio/add-sitio";
	}
	@PostMapping("/sitio/add-sitio/")
	public String saveSitio(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitio",sitio);
			return "/sitio/add-sitio";
		}
		sitioDelegate.saveSitio(sitio);
		return "redirect:/sitio/";
	}
	@GetMapping("/sitio/edit/{id}")
	public String editSitio(@PathVariable("id") String id,Model model){
		model.addAttribute("sitio",sitioDelegate.findById(Integer.parseInt(id)));	
		return "/sitio/edit-sitio";
	}
	@GetMapping("/sitio/delete-sitio/{id}")
	public String deleteSitio(@PathVariable("id") String id) {
		sitioDelegate.removeSitio(id);
		return "redirect:/sitio/";
	}
	@PostMapping("/sitio/edit/")
	public String editSitio(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("sitio",sitio);
			return "/sitio/edit-sitio";
		}
		sitioDelegate.updateSitio(sitio);
		return "redirect:/sitio/";
	}
}
