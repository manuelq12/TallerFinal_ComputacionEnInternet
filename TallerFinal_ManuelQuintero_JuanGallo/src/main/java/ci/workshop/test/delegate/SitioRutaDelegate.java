package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1SitiosRuta;

@Component
public class SitioRutaDelegate {
	private RestTemplate rest;
	public static final String REST_URI="http://localhost:8080/api";
	
	public List<Tmio1SitiosRuta> findAll() {
		ResponseEntity<TransactionBody<List<Tmio1SitiosRuta>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitioRuta/",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1SitiosRuta>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1SitiosRuta> sitio= response.getBody().getBody();
			return sitio;
		}
		return null;
	}
	public String saveSitio(Tmio1SitiosRuta nuevo) {
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitioRuta/", HttpMethod.POST,null, new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
			});
		}catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null && response.getBody().getBody() !=null) {
			return "Guardado!";
		}
		return "Error";
	}
	public String updateSitio(Tmio1SitiosRuta nuevo) {
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitioRuta/", HttpMethod.PATCH,null, new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
			});
		}catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null && response.getBody().getBody() !=null) {
			return "Actualizado!";
		}
		return "Error";
	}
	public void delete(Tmio1SitiosRuta sitio) {
		TransactionBody<Tmio1SitiosRuta> transaction= new TransactionBody<>("apiContext", sitio);
		HttpEntity<TransactionBody<Tmio1SitiosRuta>> request= new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitio/"+sitio.getId(), HttpMethod.DELETE,request, new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
			});
		}catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.getBody();
	}
	public List<String> getAllRoutes() {
		ResponseEntity<TransactionBody<List<String>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitioRuta/routes",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<String>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<String> sitio= response.getBody().getBody();
			return sitio;
		}
		return null;
	}
	public List<String> getAllSitio() {
		ResponseEntity<TransactionBody<List<String>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitioRuta/sitios",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<String>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<String> sitio= response.getBody().getBody();
			return sitio;
		}
		return null;
	}
	public Tmio1SitiosRuta getByID(String id) {
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitioRuta/"+id,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1SitiosRuta sitio= response.getBody().getBody();
			return sitio;
		}
		return null;
	}
	public Tmio1Ruta findRutaByID(Integer idRuta) {
		ResponseEntity<TransactionBody<Tmio1Ruta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/rute/"+idRuta,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Ruta ruta= response.getBody().getBody();
			return ruta;
		}
		return null;
	}
	public Tmio1Ruta findSitioByID(Integer idSitio) {
		ResponseEntity<TransactionBody<Tmio1Ruta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sito/"+idSitio,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Ruta ruta= response.getBody().getBody();
			return ruta;
		}
		return null;
	}
}
