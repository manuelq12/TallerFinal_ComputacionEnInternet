package ci.workshop.test.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.workshop.test.dao.ConductoresDao;
import ci.workshop.test.model.Tmio1Conductore;
import lombok.Data;

@Service
@Data
public class TMioConductorService implements ITMioConductorService {
	
	@Autowired
	private ConductoresDao dao;
	
	@Override
	public boolean validNewDriver(Tmio1Conductore driver) {
		// TODO Auto-generated method stub
		
		boolean valid = false;
		if(!(driver == null)) {
			LocalDate nacimiento = driver.getFechaNacimiento();
			LocalDate contratacion = driver.getFechaContratacion();
			
			if(nacimiento.isBefore(contratacion)) {
				valid = true;
			}
			
		}
		return valid;
	}
	
	@Override
	@Transactional
	public Tmio1Conductore saveDriver(Tmio1Conductore driver) {
		// TODO Auto-generated method stub
		
		boolean save  = validNewDriver(driver);
		if(save) {
			dao.save(driver);
			return driver;
		}
		return null;
	}

	@Override
	public Iterable<Tmio1Conductore> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Tmio1Conductore finbyID(String cedula) {
		return dao.findById(cedula);
	}

}
