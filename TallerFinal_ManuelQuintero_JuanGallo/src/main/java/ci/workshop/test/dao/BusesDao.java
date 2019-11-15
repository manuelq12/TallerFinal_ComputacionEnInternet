package ci.workshop.test.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ci.workshop.test.model.Tmio1Bus;

@Repository
@Transactional
public class BusesDao implements IBusesDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Bus entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);	
	}

	@Override
	public void update(Tmio1Bus entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);		

	}

	@Override
	public void delete(Tmio1Bus entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public Tmio1Bus findById(Integer codigo) {
		// TODO Auto-generated method stub
		return entityManager.find(Tmio1Bus.class, codigo);	
	}
	
	@Override
	public Tmio1Bus findByPlaca(String placa) {
		// TODO Auto-generated method stub
		String jpql = "Select a from Tmio1Bus a Where a.placa = '" + placa + "'";
		return (Tmio1Bus) entityManager.createQuery(jpql).getSingleResult();
	}
	
	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		// TODO Auto-generated method stub
		String jpql = "Select a from Tmio1Bus a Where a.marca = '" + marca + "'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal modelo) {
		// TODO Auto-generated method stub
		String jpql = "Select a from Tmio1Bus a Where a.modelo = '" + modelo + "'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	
	@Override
	public List<Tmio1Bus> findAll() {
		// TODO Auto-generated method stub
		String jpql = "Select a from Tmio1Bus a";
		return 	entityManager.createQuery(jpql).getResultList();
	}
	


}
