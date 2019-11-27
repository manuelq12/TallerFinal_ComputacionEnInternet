package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ci.workshop.test.model.DateObject;
import ci.workshop.test.model.Tmio1Servicio;

public class ServiciosDelegate {
	private RestTemplate rest;
	public static final String REST_URI="http://localhost:8080/";
	
	public Tmio1Servicio findById(String id) {
		ResponseEntity<TransactionBody<Tmio1Servicio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/"+id,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Servicio service= response.getBody().getBody();
			return service;
		}
		return null;
	}
	public List<Tmio1Servicio> findAll() {
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/all",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Servicio>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1Servicio> rutes= response.getBody().getBody();
			return rutes;
		}
		return null;
	}
	public String saveService(Tmio1Servicio nuevo) {
		ResponseEntity<TransactionBody<Tmio1Servicio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/", HttpMethod.POST,null, new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
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
	public List<Tmio1Servicio> findByFechas(DateObject date){
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/date/"+ date.getFechaInicio().toString(),HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Servicio>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1Servicio> services= response.getBody().getBody();
			return services;
		}
		return null;
	}
	// MIRAR
	public String editService(Tmio1Servicio nuevo) {
		ResponseEntity<TransactionBody<Tmio1Servicio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/", HttpMethod.POST,null, new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
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
