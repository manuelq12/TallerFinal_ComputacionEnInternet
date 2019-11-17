package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ci.workshop.test.model.Tmio1Bus;

@Component
public class BusDelegate {
	private RestTemplate rest;
	public static final String REST_URI="http://localhost:8080/";
	
	public Tmio1Bus findById(int id) {
		ResponseEntity<TransactionBody<Tmio1Bus>> response= null;
		try {
			response= rest.exchange(REST_URI+"/bus/"+id,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Bus bus= response.getBody().getBody();
			return bus;
		}
		return null;
	}
	public List<Tmio1Bus> findAll() {
		ResponseEntity<TransactionBody<List<Tmio1Bus>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/bus/all",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Bus>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1Bus> buses= response.getBody().getBody();
			return buses;
		}
		return null;
	}
	public String saveBus(Tmio1Bus nuevo) {
		ResponseEntity<TransactionBody<Tmio1Bus>> response= null;
		try {
			response= rest.exchange(REST_URI+"/bus/add", HttpMethod.POST,null, new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
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
