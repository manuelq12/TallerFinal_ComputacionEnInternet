package ci.workshop.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.workshop.test.dao.SitioDao;
import ci.workshop.test.model.Tmio1Sitio;

@Service
public class TMioSitioService implements ITMioSitioService{
	@Autowired
	private SitioDao sitioDao;

	@Override
	public Iterable<Tmio1Sitio> findAll() {
		return sitioDao.findAll();
	}

	@Override
	public Tmio1Sitio saveSitio(Tmio1Sitio sitio) {
		try {
			sitioDao.save(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1Sitio updateSitio(Tmio1Sitio sitio) {
		try {
			sitioDao.update(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1Sitio removeSitio(Tmio1Sitio sitio) {
		try {
			sitioDao.delete(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1Sitio findById(int id) {
		try {
			return sitioDao.findByID(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
