package ci.workshop.test.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


import ci.workshop.test.model.Tmio1Bus;

@Component
public class BusDelegate {
	
	private RestTemplate rest = new RestTemplate();
	public static final String REST_URI="http://localhost:8080/api";
	
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
	public List<String> findAllTypes() {
		ResponseEntity<TransactionBody<List<String>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/bus/types",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<String>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<String> buses= response.getBody().getBody();
			return buses;
		}
		return null;
	}

	public String saveBus(Tmio1Bus nuevo) {
//		TransactionBody<Tmio1Bus> transaction = new TransactionBody<>("newBus", nuevo);
//		HttpEntity<TransactionBody<Tmio1Bus>> request = new HttpEntity<>(transaction);
//		ResponseEntity<TransactionBody<Tmio1Bus>> response= null;
//		
//		try {
//			response= rest.exchange(REST_URI+"/bus/aBus", HttpMethod.POST,request, new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
//			});
//		}catch (HttpStatusCodeException e) {
//			int statusCode=e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
//			e.printStackTrace();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		if(response!=null) {
//			System.out.println(response.getStatusCodeValue());
//			return "Guardado!";
//		}
//
//		
//		return "Error";

		Tmio1Bus car = rest.postForEntity(REST_URI + "/bus", nuevo, Tmio1Bus.class).getBody();
		if(car == null) {
			System.out.println("Fallo");
			return "Fallo";
		}
		
		return "Guardado";
	}
}
