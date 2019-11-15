package ci.workshop.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.service.TMioRutaService;
import lombok.Data;

@Controller
@Data
public class RouteController {

	TMioRutaService routeService;
	
	@Autowired
	public RouteController(TMioRutaService rutaS) {
		// TODO Auto-generated constructor stub
		routeService = rutaS;
	}
	
	
	@GetMapping("/route/")
	public String indexRuta(Model model) {
		model.addAttribute("routes",routeService.findAll());
		return "/route/index";
	}
	
	@GetMapping("/route/add-route")
	public String addRuta(Model model) {
		
		model.addAttribute("states",routeService.findAllStates());
		model.addAttribute("route", new Tmio1Ruta());
		
		
		
		return "/route/add-route";
	}
	
	@PostMapping("/route/add-route")
	public String saveRuta(@Valid Tmio1Ruta ruta, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("states",routeService.findAllStates());
			model.addAttribute("route", ruta);
			return "/route/add-route";
		}
		
		routeService.saveRoute(ruta);
		return"redirect:/route/";
	}
	
}
