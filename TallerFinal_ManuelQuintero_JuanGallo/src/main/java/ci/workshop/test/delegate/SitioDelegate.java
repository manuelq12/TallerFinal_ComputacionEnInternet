package ci.workshop.test.delegate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ci.workshop.test.model.Tmio1Sitio;

@Component
public class SitioDelegate {
	
	private RestTemplate rest = new RestTemplate();
	public static final String REST_URI="http://localhost:8080/api";
	
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
	public Tmio1Sitio findById(int id) {
		ResponseEntity<TransactionBody<Tmio1Sitio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitio/" + id,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Sitio sitio= response.getBody().getBody();
			return sitio;
		}
		return null;
	}
	public String saveSitio(Tmio1Sitio nuevo) {
		Tmio1Sitio x = rest.postForEntity(REST_URI + "/sitio", nuevo, Tmio1Sitio.class).getBody();
		return "Enviado";		
	}
	public String updateSitio(Tmio1Sitio nuevo) {
		try {
			Tmio1Sitio x = rest.patchForObject(REST_URI + "/sitio", nuevo, Tmio1Sitio.class);	
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Error";
		}
		return "Enviado";
	}
	
	public void delete(Tmio1Sitio sitio) {
		TransactionBody<Tmio1Sitio> transaction= new TransactionBody<>("apiContext", sitio);
		HttpEntity<TransactionBody<Tmio1Sitio>> request= new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Sitio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/sitio/"+sitio.getId(), HttpMethod.DELETE,request, new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
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
