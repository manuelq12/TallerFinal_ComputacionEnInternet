package ci.workshop.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ci.workshop.test.delegate.RutaDelegate;
import ci.workshop.test.model.Tmio1Ruta;
import lombok.Data;

@Controller
@Data
public class RouteController {
	RutaDelegate rutaDelegate;
	@Autowired
	public RouteController(RutaDelegate rutaS) {
		// TODO Auto-generated constructor stub
		rutaDelegate = rutaS;
	}
	@GetMapping("/route/")
	public String indexRuta(Model model) {
		model.addAttribute("routes",rutaDelegate.findAll());
		return "/route/index";
	}
	
	@GetMapping("/route/add-route")
	public String addRuta(Model model) {
		
		model.addAttribute("states",rutaDelegate.findAllStates());
		model.addAttribute("route", new Tmio1Ruta());
		return "/route/add-route";
	}
	
	@GetMapping("/route/delete-route/{id}")
	public String deleteRoute(@PathVariable("id") String id) {
		rutaDelegate.removeRuta(id);
		return "redirect:/route/";
	}
	
	@PostMapping("/route/add-route")
	public String saveRuta(@Valid Tmio1Ruta ruta, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("states",rutaDelegate.findAllStates());
			model.addAttribute("route", ruta);
			return "/route/add-route";
		}
		rutaDelegate.saveRute(ruta);
		return"redirect:/route/";
	}
}
