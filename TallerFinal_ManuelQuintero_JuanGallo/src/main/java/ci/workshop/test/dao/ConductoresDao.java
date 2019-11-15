package ci.workshop.test.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ci.workshop.test.model.Tmio1Conductore;

@Repository
@Transactional
public class ConductoresDao implements IConductoresDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Conductore entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);	
	}

	@Override
	public void update(Tmio1Conductore entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);		

	}

	@Override
	public void delete(Tmio1Conductore entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Conductore findById(String cedula) {
		// TODO Auto-generated method stub
		return entityManager.find(Tmio1Conductore.class, cedula);
	}
	
	@Override
	public List<Tmio1Conductore>  findByNombre(String nombre) {
		// TODO Auto-generated method stub
		String jpql = "Select a from Tmio1Conductore a Where a.nombre = '" + nombre + "'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Tmio1Conductore> findByApellido(String apellido) {
		// TODO Auto-generated method stub
		String jpql = "Select a from Tmio1Conductore a Where a.apellidos = '" + apellido + "'";
		return entityManager.createQuery(jpql).getResultList();
	}


	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql = "Select a from Tmio1Conductore a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

}
