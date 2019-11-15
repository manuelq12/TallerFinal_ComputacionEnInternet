package ci.workshop.test.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import ci.workshop.test.model.Tmio1Ruta;

@Repository
@Transactional
public class RutasDao implements IRutasDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Ruta entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);	
	}

	@Override
	public void update(Tmio1Ruta entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);		

	}

	@Override
	public void delete(Tmio1Ruta entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Ruta findById(Integer codigo) {
		// TODO Auto-generated method stub
		return entityManager.find(Tmio1Ruta.class, codigo);
	}
	
	@Override
	public List<Tmio1Ruta> findByDias(BigDecimal inicio, BigDecimal fin) {
		// TODO Auto-generated method stub
		String jpql = "Select a from Tmio1Ruta a Where a.diaInicio >= " + "'" + inicio + "' AND " +
		"a.diaFin <= " + "'" + fin + "'"; 
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Tmio1Ruta> findByHoras(BigDecimal inicio, BigDecimal fin) {
		String jpql = "Select a from Tmio1Ruta a Where a.horaInicio >= " + "'" + inicio + "' AND " +
		"a.horaFin <= " + "'" + fin + "'"; 
		return entityManager.createQuery(jpql).getResultList();
	}
	

	@Override
	public List<Tmio1Ruta> findAll() {
		String jpql = "Select a from Tmio1Ruta a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

}
