package ci.workshop.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ci.workshop.test.delegate.BusDelegate;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.service.TMioBusService;
import lombok.Data;

@Controller
@Data
public class BusController {

	
	BusDelegate busDelegate;
	
	@Autowired
	public BusController(BusDelegate busS) {
		// TODO Auto-generated constructor stub
		busDelegate=busS;
	}
	
	@GetMapping("/bus")
	public String indexBus(Model model) {
		model.addAttribute("buses",busDelegate.findAll());
		return "/bus/index";
	}
	@GetMapping("/bus/add-bus")
	public String addBus(Model model) {
		model.addAttribute("bus",new Tmio1Bus());
		model.addAttribute("types",busDelegate.findAllTypes());
		
		return "/bus/add-bus";
	}
	@GetMapping("/bus/delete-bus/{id}")
	public String deleteBus(@PathVariable("id") String id,Model model) {
		model.addAttribute("buses",busDelegate.findAll());
		busDelegate.removeBus(id);
		return "/bus/index";
	}
	
	@PostMapping("/bus/add-bus")
	public String saveBus (@Valid @ModelAttribute("bus") Tmio1Bus bus, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("bus",bus);
			model.addAttribute("types",busDelegate.findAllTypes());
			return "/bus/add-bus";
		}
		busDelegate.saveBus(bus);
		
		return "redirect:/bus/";
	}
}
