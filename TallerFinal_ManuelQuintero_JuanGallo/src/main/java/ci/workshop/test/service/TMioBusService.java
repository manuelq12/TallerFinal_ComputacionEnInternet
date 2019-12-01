package ci.workshop.test.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.workshop.test.dao.IBusesDao;
import ci.workshop.test.model.BusType;
import ci.workshop.test.model.Tmio1Bus;
import lombok.Data;

@Service
@Data
public class TMioBusService implements ITMioBusService{
	

	@Autowired
	private IBusesDao busDao;

	@Override
	public boolean validNewBus(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		boolean valid = false;
		if(!(bus == null)){
			if(!(bus.getCapacidad().intValue()<1)) {
				String tipo = bus.getTipo();
				switch (tipo) {
				case "A":
					valid = true;
					break;
				case "P":
					valid = true;
					break;
				case "T":
					valid = true;
					break;
				default:
					break;
				}
			}
		}
		return valid;
	}
	
	@Override
	@Transactional
	public Tmio1Bus saveBus(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		boolean save = validNewBus(bus);
		if(save) {
			busDao.save(bus);
			return bus;
		}
		return null;
	}

	@Override
	public Iterable<Tmio1Bus> findAll() {
		// TODO Auto-generated method stub
		return busDao.findAll();
	}

	@Override
	public Iterable<String> findAllTypes() {
		// TODO Auto-generated method stub
		ArrayList<String> types = new ArrayList<>();
		types.add(BusType.A.toString());
		types.add(BusType.P.toString());
		types.add(BusType.T.toString());
		return (Iterable<String>) types;
	}

	@Override
	public Tmio1Bus findById(int id) {
		return busDao.findById(id);
	}

	public void removeCar(String id) {
		busDao.delete(busDao.findById(Integer.parseInt(id)));
	}

	

}
