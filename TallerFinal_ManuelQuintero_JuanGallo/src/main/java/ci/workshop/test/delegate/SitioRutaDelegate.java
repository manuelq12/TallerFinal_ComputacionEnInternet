package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.model.Tmio1SitiosRuta;
import ci.workshop.test.model.Tmio1SitiosRutaPK;

@Component
public class SitioRutaDelegate {
	private RestTemplate rest = new RestTemplate();
	public static final String REST_URI = "http://localhost:8080/api";

	public List<Tmio1SitiosRuta> findAll() {
		ResponseEntity<TransactionBody<List<Tmio1SitiosRuta>>> response = null;
		try {
			response = rest.exchange(REST_URI + "/sitioRuta/all", HttpMethod.GET, null,
					new ParameterizedTypeReference<TransactionBody<List<Tmio1SitiosRuta>>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			System.out.println(response);
			System.out.println(response.getBody());
			List<Tmio1SitiosRuta> sitio = response.getBody().getBody();
			return sitio;
		}
		return null;
	}

	public String saveSitio(Tmio1SitiosRuta nuevo) {
		Tmio1SitiosRuta sitioRuta = rest.postForEntity(REST_URI + "/sitioRuta", nuevo, Tmio1SitiosRuta.class).getBody();
		if (sitioRuta == null) {
			return "Fallo";
		}
		return "Guardado";
	}

	public String updateSitio(Tmio1SitiosRuta nuevo) {
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = null;
		try {
			response = rest.exchange(REST_URI + "/sitioRuta/", HttpMethod.PATCH, null,
					new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null && response.getBody().getBody() != null) {
			return "Actualizado!";
		}
		return "Error";
	}

	public List<Tmio1Ruta> getAllRoutes() {
		ResponseEntity<TransactionBody<List<Tmio1Ruta>>> response = null;
		try {
			response = rest.exchange(REST_URI + "/sitioRuta/routes", HttpMethod.GET, null,
					new ParameterizedTypeReference<TransactionBody<List<Tmio1Ruta>>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			List<Tmio1Ruta> sitio = response.getBody().getBody();
			return sitio;
		}
		return null;
	}

	public List<Tmio1Sitio> getAllSitio() {
		ResponseEntity<TransactionBody<List<Tmio1Sitio>>> response = null;
		try {
			response = rest.exchange(REST_URI + "/sitioRuta/sitios", HttpMethod.GET, null,
					new ParameterizedTypeReference<TransactionBody<List<Tmio1Sitio>>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			List<Tmio1Sitio> sitio = response.getBody().getBody();
			return sitio;
		}
		return null;
	}

	public Tmio1SitiosRuta getByID(String id) {
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response = null;
		try {
			response = rest.exchange(REST_URI + "/sitioRuta/" + id, HttpMethod.GET, null,
					new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			Tmio1SitiosRuta sitio = response.getBody().getBody();
			return sitio;
		}
		return null;
	}

	public Tmio1Ruta findRutaByID(Integer idRuta) {
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
		try {
			response = rest.exchange(REST_URI + "/rute/" + idRuta, HttpMethod.GET, null,
					new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			Tmio1Ruta ruta = response.getBody().getBody();
			return ruta;
		}
		return null;
	}

	public Tmio1Sitio findSitioByID(Integer idSitio) {
		ResponseEntity<TransactionBody<Tmio1Sitio>> response = null;
		try {
			response = rest.exchange(REST_URI + "/sitio/" + idSitio, HttpMethod.GET, null,
					new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			Tmio1Sitio ruta = response.getBody().getBody();
			return ruta;
		}
		return null;
	}
    
	public void delete(String id) {
		rest.delete(REST_URI + "/sitioRuta/delete/" + id);
	}

	public Tmio1SitiosRutaPK getByIDPK(String id) {
		ResponseEntity<TransactionBody<Tmio1SitiosRutaPK>> response = null;
		try {
			response = rest.exchange(REST_URI + "/sitioRuta/pk/" + id, HttpMethod.GET, null,
					new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRutaPK>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			Tmio1SitiosRutaPK sitio = response.getBody().getBody();
			return sitio;
		}
		return null;
	}
}
