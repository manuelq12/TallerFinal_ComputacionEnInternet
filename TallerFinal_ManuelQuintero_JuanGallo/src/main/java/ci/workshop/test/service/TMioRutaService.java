package ci.workshop.test.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.workshop.test.dao.RutasDao;
import ci.workshop.test.model.RouteStateType;
import ci.workshop.test.model.Tmio1Ruta;
import lombok.Data;

@Service
@Data
public class TMioRutaService implements ITMioRutaService{
	
	@Autowired
	private RutasDao dao;
	
	@Override
	public boolean validNewRoute(Tmio1Ruta route) {
		// TODO Auto-generated method stub
		
		boolean valid = false;
		if (!(route == null)) {
			
			BigDecimal inicioDia = route.getDiaInicio();
			BigDecimal finDia = route.getDiaFin();
			
			if(inicioDia.compareTo(finDia) < 1) {
				//Se maneja la convención de que los segundos van de 0 cuando es la 00:00 horas
				//hasta cuando son las 23:59 que serían 86399 segundos.
				
				BigDecimal inicioHora = route.getHoraInicio();
				BigDecimal finHora = route.getHoraFin();
				
				if(inicioHora.compareTo(finHora) < 0 && inicioHora.intValue()<86399 && finHora.intValue()<86399
					&& inicioHora.intValue()>-1 && finHora.intValue()>-1) {
					valid = true;
				}
										
			}
			
		}
		return valid;
	}
	
	@Override
	public Tmio1Ruta saveRoute(Tmio1Ruta route) {
		// TODO Auto-generated method stub
		boolean save = validNewRoute(route);
		if(save) {
			dao.save(route);	
			return route;
		}
		return null;
	}

	@Override
	public Iterable<Tmio1Ruta> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Iterable<String> findAllStates() {
		// TODO Auto-generated method stub
		ArrayList<String> types = new ArrayList<>();
		types.add(RouteStateType.Activa.toString());
		types.add(RouteStateType.Inactiva.toString());

		return (Iterable<String>) types;
	}

}
