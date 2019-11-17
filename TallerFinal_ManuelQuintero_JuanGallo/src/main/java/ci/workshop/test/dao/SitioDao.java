package ci.workshop.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ci.workshop.test.model.Tmio1Sitio;

@Repository
@Transactional
public class SitioDao implements ISitioDao{

	@PersistenceContext
	private EntityManager entityManager;

	
	
	@Override
	public void save(Tmio1Sitio sitio) {
		entityManager.persist(sitio);
	}

	@Override
	public List<Tmio1Sitio> findAll() {
		String jpql = "Select a from Tmio1Sitio a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void update(Tmio1Sitio sitio) {
		entityManager.merge(sitio);
	}

	@Override
	public void delete(Tmio1Sitio sitio) {
		entityManager.remove(sitio);
	}
}
