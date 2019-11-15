package ci.workshop.test.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;

@Repository
@Transactional
public class ServiciosDao implements IServiciosDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Servicio entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);	
		
	}

	@Override
	public void update(Tmio1Servicio entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);		

	}

	@Override
	public void delete(Tmio1Servicio entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Servicio findById(Tmio1ServicioPK codigo) {
		// TODO Auto-generated method stub
		return entityManager.find(Tmio1Servicio.class, codigo);
	}

	@Override
	public List<Tmio1Servicio> findAll() {
		String jpql = "Select a from Tmio1Servicio a";
		return 	entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Object[]> findServiceConductor(LocalDate fechaVigente){ 
		String jpql = "SELECT tc, COUNT(tc) FROM Tmio1Servicio ts INNER JOIN ts.tmio1Conductore tc"
				+ " WHERE " + "ts.id.fechaInicio <= '" + fechaVigente + 
				"' AND ts.id.fechaFin >= '" + fechaVigente + 
				"' GROUP BY ts.id.cedulaConductor";
		
		List<Object[]> count= entityManager.createQuery(jpql).getResultList();
		jpql = "SELECT tc FROM Tmio1Servicio ts INNER JOIN ts.tmio1Conductore tc"
				+ " WHERE " + "ts.id.fechaInicio <= '" + fechaVigente + 
				"' AND ts.id.fechaFin >= '" + fechaVigente + 
				"' ORDER BY ts.id.fechaInicio desc";
		List<Tmio1Conductore> order= entityManager.createQuery(jpql).getResultList();
		List<Tmio1Conductore> distinct = new ArrayList<Tmio1Conductore>();
		List<Object[]> results = new ArrayList<Object[]>();
		for (Tmio1Conductore  driver : order) {
			if(!distinct.contains(driver)) {		
				distinct.add(driver);
			}
		}
		for (Tmio1Conductore driver : distinct) {
			for (Object[] objects : count) {
				if(driver.equals(objects[0])) {
					results.add(objects);
					break;
				}
			}	
		}
		
		
		return results;
	}
	
	@Override
	public List<Tmio1Ruta> findRutaFechas(LocalDate fechaFinal) {
		List<Tmio1Ruta> retorno= new ArrayList<Tmio1Ruta>();
		String jpql = "SELECT r, COUNT(r) FROM Tmio1Servicio "
				+ "ts INNER JOIN ts.tmio1Ruta r WHERE ts.id.fechaInicio <= '" + fechaFinal + "' "
						+ "AND ts.id.fechaFin >= '" + fechaFinal + "'  GROUP BY r";
		
		List<Object[]> results= entityManager.createQuery(jpql).getResultList();
		for (Object[] result: results) {
			Tmio1Ruta n= (Tmio1Ruta) result[0];
			int count= ((Number) result[1]).intValue();
			if( count<10) retorno.add(n);
		}
		return retorno;
	}
	
	@Override
	public List<Tmio1Bus> busSameDayService(LocalDate diaEspecifico) {
		List<Tmio1Bus> retorno= new ArrayList<Tmio1Bus>();
		String jpql = "SELECT b, COUNT(b) FROM Tmio1Servicio "
				+ "ts INNER JOIN ts.tmio1Bus b WHERE ts.id.fechaInicio <= '" + diaEspecifico + "' "
						+ "AND ts.id.fechaFin >= '" + diaEspecifico + "'  GROUP BY b";
		List<Object[]> results= entityManager.createQuery(jpql).getResultList();
		for (Object[] result: results) {
			Tmio1Bus n= (Tmio1Bus) result[0];
			int count= ((Number) result[1]).intValue();
			if( count>1) retorno.add(n);
		}
		return retorno;
	}

}
