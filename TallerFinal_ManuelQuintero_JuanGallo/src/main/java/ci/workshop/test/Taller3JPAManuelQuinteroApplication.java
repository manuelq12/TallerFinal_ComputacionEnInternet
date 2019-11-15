package ci.workshop.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.User;
import ci.workshop.test.model.UserType;
import ci.workshop.test.repository.BusesRepository;
import ci.workshop.test.repository.ConductoresRepository;
import ci.workshop.test.repository.RutasRepository;
import ci.workshop.test.service.UserService;

@SpringBootApplication
public class Taller3JPAManuelQuinteroApplication {
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	@Autowired 
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(Taller3JPAManuelQuinteroApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ConductoresRepository driverR, 
			BusesRepository busRepository, RutasRepository routeR) {
		return (args) -> {
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        
			User user = new User();			
			user.setUsername("admin");
			//user.setPassword(passwordEncoder.encode("123"));
			user.setPassword("{noop}123");
			user.setType(UserType.admin);
			userService.saveUser(user);
			
			
			User user2 = new User();			
			user2.setUsername("juan");
			//user2.setPassword(passwordEncoder.encode("123"));
			user2.setPassword("{noop}123");
			user2.setType(UserType.operador);
			userService.saveUser(user2);
			
			
			Tmio1Bus bus1 = new Tmio1Bus();
			bus1.setId(1);
			bus1.setCapacidad(new BigDecimal(5000));
			bus1.setMarca("Mercedes-Benz");
			bus1.setModelo(new BigDecimal(2019));
			bus1.setPlaca("HBL 802");
			bus1.setTipo("A");
			
			busRepository.save(bus1);
			
			Tmio1Bus bus2 = new Tmio1Bus();
			bus2.setId(2);
			bus2.setCapacidad(new BigDecimal(10000));
			bus2.setMarca("Mercedes-Benz");
			bus2.setModelo(new BigDecimal(2017));
			bus2.setPlaca("KHA 430");
			bus2.setTipo("P");
			
			busRepository.save(bus2);
			
			Tmio1Conductore driver = new Tmio1Conductore();
			
			driver.setNombre("Manuel");
			driver.setApellidos("Quintero Cubillos");
			driver.setCedula("1113695637");
			driver.setFechaContratacion(LocalDate.of(2019, 02, 19));
			driver.setFechaNacimiento(LocalDate.of(1999, 03, 28));
			
			driverR.save(driver);
			
			Tmio1Conductore driver1 = new Tmio1Conductore();
			
			driver1.setNombre("Nathalia");
			driver1.setApellidos("Lopez");
			driver1.setCedula("1003456789");
			driver1.setFechaContratacion(LocalDate.of(2017, 07, 11));
			driver1.setFechaNacimiento(LocalDate.of(2000, 07, 11));
			
			driverR.save(driver1);
			
			
			Tmio1Conductore driver2 = new Tmio1Conductore();
			
			driver2.setNombre("Gustavo");
			driver2.setApellidos("Saavedra");
			driver2.setCedula("11136864752");
			driver2.setFechaContratacion(LocalDate.of(2015, 03, 24));
			driver2.setFechaNacimiento(LocalDate.of(1998, 01, 03));
			
			driverR.save(driver2);
			
			
			Tmio1Ruta route1 = new Tmio1Ruta();
			
			route1.setActiva("Activa");
			route1.setDescripcion("D1");
			route1.setDiaInicio(new BigDecimal(1));
			route1.setDiaFin(new BigDecimal(5));
			route1.setHoraInicio(new BigDecimal(24000));
			route1.setHoraFin(new BigDecimal(72000));
			route1.setNumero("A11");
			
			routeR.save(route1);
			
			Tmio1Ruta route2 = new Tmio1Ruta();
			
			route2.setActiva("Activa");
			route2.setDescripcion("D2");
			route2.setDiaInicio(new BigDecimal(1));
			route2.setDiaFin(new BigDecimal(5));
			route2.setHoraInicio(new BigDecimal(34000));
			route2.setHoraFin(new BigDecimal(62000));
			route2.setNumero("A12");
			
			
			routeR.save(route2);
			
			Tmio1Ruta route3 = new Tmio1Ruta();
			
			route3.setActiva("Activa");
			route3.setDescripcion("D3");
			route3.setDiaInicio(new BigDecimal(1));
			route3.setDiaFin(new BigDecimal(5));
			route3.setHoraInicio(new BigDecimal(13000));
			route3.setHoraFin(new BigDecimal(41000));
			route3.setNumero("A13");
			
			routeR.save(route3);
			
			
		};
	}
}
