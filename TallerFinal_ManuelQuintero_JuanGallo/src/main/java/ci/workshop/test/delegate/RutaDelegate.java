package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ci.workshop.test.model.Tmio1Ruta;

@Component
public class RutaDelegate {
	private RestTemplate rest;
	public static final String REST_URI="http://localhost:8080/";
	
	public Tmio1Ruta findById(String id) {
		ResponseEntity<TransactionBody<Tmio1Ruta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/rute/"+id,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
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
	public List<Tmio1Ruta> findAll() {
		ResponseEntity<TransactionBody<List<Tmio1Ruta>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/rute/all",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Ruta>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1Ruta> rutes= response.getBody().getBody();
			return rutes;
		}
		return null;
	}
	public String saveRute(Tmio1Ruta nuevo) {
		ResponseEntity<TransactionBody<Tmio1Ruta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/rute/add", HttpMethod.POST,null, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
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
}