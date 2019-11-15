package ci.workshop.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.service.TMioBusService;
import lombok.Data;

@Controller
@Data
public class BusController {

	TMioBusService busService;
	
	@Autowired
	public BusController(TMioBusService busS) {
		// TODO Auto-generated constructor stub
		busService=busS;
	}
	
	@GetMapping("/bus")
	public String indexBus(Model model) {
		model.addAttribute("buses",busService.findAll());
		return "/bus/index";
	}
	@GetMapping("/bus/add-bus")
	public String addBus(Model model) {
		model.addAttribute("bus",new Tmio1Bus());
		model.addAttribute("types",busService.findAllTypes());
		
		return "/bus/add-bus";
	}
	
	@PostMapping("/bus/add-bus")
	public String saveBus (@Valid @ModelAttribute("bus") Tmio1Bus bus, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("bus",bus);
			model.addAttribute("types",busService.findAllTypes());
			return "/bus/add-bus";
		}
		
		busService.saveBus(bus);
		return "redirect:/bus/";
	}
}
