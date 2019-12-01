package ci.workshop.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.model.Tmio1SitiosRuta;

@Repository
@Transactional
public class SitioRutaDao implements ISitioRutaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1SitiosRuta sitio) {
		entityManager.persist(sitio);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tmio1SitiosRuta> findAll() {
		String jpql = "Select a from Tmio1SitiosRuta a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void update(Tmio1SitiosRuta sitio) {
		entityManager.merge(sitio);
	}

	@Override
	public void delete(Tmio1SitiosRuta sitio) {
		entityManager.remove(sitio);
	}
	
	public Tmio1SitiosRuta findByID(String id) {
		return entityManager.find(Tmio1SitiosRuta.class, Integer.parseInt(id));
	}
}
