package ci.workshop.test.service;

import org.springframework.stereotype.Service;

import ci.workshop.test.dao.SitioRutaDao;
import ci.workshop.test.model.Tmio1SitiosRuta;

@Service
public class TMioSitioRutaService implements ITMioSitioRutaService{

	private SitioRutaDao sitioDao;
	
	@Override
	public Iterable<Tmio1SitiosRuta> findAll() {
		return sitioDao.findAll();
	}

	@Override
	public Tmio1SitiosRuta saveSitio(Tmio1SitiosRuta sitio) {
		try {
			sitioDao.save(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1SitiosRuta updateSitioRuta(Tmio1SitiosRuta sitio) {
		try {
			sitioDao.update(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

	@Override
	public Tmio1SitiosRuta removeSitioRuta(Tmio1SitiosRuta sitio) {
		try {
			sitioDao.delete(sitio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sitio;
	}

}
