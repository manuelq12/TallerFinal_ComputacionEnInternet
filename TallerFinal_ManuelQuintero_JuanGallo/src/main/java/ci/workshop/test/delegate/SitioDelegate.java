package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ci.workshop.test.model.Tmio1Sitio;

public class SitioDelegate {
	private RestTemplate rest;
	public static final String REST_URI="http://localhost:8080/";
	
	public List<Tmio1Sitio> findAll() {
		ResponseEntity<TransactionBody<List<Tmio1Sitio>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitio/all",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Sitio>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1Sitio> sitio= response.getBody().getBody();
			return sitio;
		}
		return null;
	}
	public String saveSitio(Tmio1Sitio nuevo) {
		ResponseEntity<TransactionBody<Tmio1Sitio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitio/add", HttpMethod.POST,null, new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
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
	public String updateSitio(Tmio1Sitio nuevo) {
		ResponseEntity<TransactionBody<Tmio1Sitio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitio/update", HttpMethod.PATCH,null, new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
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
	
	public void delete(Tmio1Sitio sitio) {
		TransactionBody<Tmio1Sitio> transaction= new TransactionBody<>("apiContext", sitio);
		HttpEntity<TransactionBody<Tmio1Sitio>> request= new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitio/delete/"+sitio.getId(), HttpMethod.DELETE,request, new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
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
}
