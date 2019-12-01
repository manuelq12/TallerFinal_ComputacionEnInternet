package ci.workshop.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ci.workshop.test.delegate.ConductoresDelegate;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.service.TMioConductorService;
import lombok.Data;

@Controller
@Data
public class DriverController {

	
	ConductoresDelegate driverDelegate;
	 
	@Autowired
	public DriverController(ConductoresDelegate s) {
		// TODO Auto-generated constructor stub
		driverDelegate = s;
	}
	
	@GetMapping("/driver/")
	public String indexDriver(Model model) {
		model.addAttribute("drivers", driverDelegate.findAll());
		return "/driver/index";
	}
	
	@GetMapping("/driver/add-driver")
	public String addDriver(Model model) {
		model.addAttribute("driver", new Tmio1Conductore());
		return "/driver/add-driver";
	}
	@PostMapping("/driver/add-driver")
	public String saveDriver(@Valid Tmio1Conductore driver, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("driver", driver);
			return "/driver/add-driver";
		}
		
		driverDelegate.saveDriver(driver);
		return "redirect:/driver/";
	}
}
