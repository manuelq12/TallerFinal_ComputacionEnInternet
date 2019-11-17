package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ci.workshop.test.model.Tmio1Conductore;

@Component
public class ConductoresDelegate {
	private RestTemplate rest;
	public static final String REST_URI="http://localhost:8080/";
	
	public Tmio1Conductore findById(String cedula) {
		ResponseEntity<TransactionBody<Tmio1Conductore>> response= null;
		try {
			response= rest.exchange(REST_URI+"/driver/"+cedula,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Conductore driver= response.getBody().getBody();
			return driver;
		}
		return null;
	}
	public List<Tmio1Conductore> findAll() {
		ResponseEntity<TransactionBody<List<Tmio1Conductore>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/driver/all",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Conductore>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1Conductore> drivers= response.getBody().getBody();
			return drivers;
		}
		return null;
	}
	public String saveDriver(Tmio1Conductore nuevo) {
		ResponseEntity<TransactionBody<Tmio1Conductore>> response= null;
		try {
			response= rest.exchange(REST_URI+"/driver/add", HttpMethod.POST,null, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
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
