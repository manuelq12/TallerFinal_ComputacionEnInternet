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

import ci.workshop.test.delegate.ServiciosDelegate;
import ci.workshop.test.model.DateObject;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;
import ci.workshop.test.service.TMioServicioService;

@Controller
public class ServiceController {

	
	private ServiciosDelegate service;
	private String tempHash = "";
	
	 
	@Autowired
	public ServiceController(ServiciosDelegate s) {
		// TODO Auto-generated constructor stub
		service = s;
	}
	
	@GetMapping("/login")
	public String login() {
		return "/customLogin";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/customLogin";
	}
	
	@GetMapping("/service/")
	public String index(Model model) {
		
		model.addAttribute("services",service.findAllID());
		return "/service/index";
	}
	
	@GetMapping("/service/add-service")
	public String addService(Model model){
		model.addAttribute("buses", service.findAllBuses());
		model.addAttribute("routes",service.findAllRoutes());
		model.addAttribute("drivers",service.findAllDrivers());
		model.addAttribute("service", new Tmio1ServicioPK());
		
		return "/service/add-service";
	}
	
	@PostMapping("/service/add-service")
	public String saveService(@Valid @ModelAttribute Tmio1ServicioPK id, BindingResult bindingResult, Model model){
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("buses", service.findAllBuses());
			model.addAttribute("routes",service.findAllRoutes());
			model.addAttribute("drivers",service.findAllDrivers());
			model.addAttribute("service", new Tmio1ServicioPK());
			
			return "/service/add-service";
		}
		Tmio1Bus bus = service.findByBusId(id.getIdBus());
		Tmio1Conductore driver = service.findByDriverId(id.getCedulaConductor());
		Tmio1Ruta route = service.findByRouteId(id.getIdRuta());
		Tmio1Servicio s = new Tmio1Servicio();
		
		s.setId(id);
		s.setTmio1Bus(bus);
		s.setTmio1Conductore(driver);
		s.setTmio1Ruta(route);
		s.setHash(s.getId().getHashId());
		
		service.saveService(s);
		
		return "redirect:/service/";
	}
	
	@GetMapping("/service/edit/{id}")
	public String updateService(@PathVariable("id") String id, Model model) {
		
		tempHash = id;		
		model.addAttribute("buses", service.findAllBuses());
		model.addAttribute("routes",service.findAllRoutes());
		model.addAttribute("drivers",service.findAllDrivers());
		model.addAttribute("tempHash", tempHash);
		model.addAttribute("service", new Tmio1ServicioPK());
		
		return "/service/update-service";
	}
	
	@PostMapping("/service/edit/{id}")
	public String saveUpdateService(@PathVariable("id") String hash, Tmio1ServicioPK nuevo, BindingResult bindingResult,  Model model) {

		Tmio1Servicio ser = service.findByHash(tempHash);
	
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("buses", service.findAllBuses());
			model.addAttribute("routes",service.findAllRoutes());
			model.addAttribute("drivers",service.findAllDrivers());
			tempHash = hash;
			model.addAttribute("tempHash", tempHash);
			
			return "/service/update-service";
		}
		
		Tmio1Bus bus = service.findByBusId(nuevo.getIdBus());
		Tmio1Conductore driver = service.findByDriverId(nuevo.getCedulaConductor());
		Tmio1Ruta route = service.findByRouteId(nuevo.getIdRuta());

		
		Tmio1Servicio s = new Tmio1Servicio();
		s.setId(nuevo);
		s.setTmio1Bus(bus);
		s.setTmio1Conductore(driver);
		s.setTmio1Ruta(route);
		s.setHash(s.getId().getHashId());
	
	
		service.saveService(s);
		service.delete(ser);

		return "redirect:/service/";
	}
	
	@GetMapping("/service/dates")
	public String consultarFecha(Model model) {
		model.addAttribute("dates", new DateObject());
		model.addAttribute("services",service.findAllIDWithoutRepeatedFechaInicio());
		return "/service/dates-search";
	}
	
	@PostMapping("/service/dates")
	public String resultadoFecha(DateObject date, Model model) {		
		model.addAttribute("services", service.findAllByFechaInicio(date.getFechaInicio()));
		return "/service/dates-result";
	}
}
