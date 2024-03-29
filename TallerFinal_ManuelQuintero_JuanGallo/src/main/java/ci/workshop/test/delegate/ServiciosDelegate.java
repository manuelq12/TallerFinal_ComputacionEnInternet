package ci.workshop.test.delegate;

import java.time.LocalDate;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ci.workshop.test.model.DateObject;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;

@Component
public class ServiciosDelegate {
	private RestTemplate rest = new RestTemplate();
	public static final String REST_URI="http://localhost:8080/api";
	

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
		Tmio1Servicio service = rest.postForEntity(REST_URI + "/service", nuevo, Tmio1Servicio.class).getBody();
		return "Guardado";
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

	public String editService(Tmio1Servicio nuevo) {
		HttpClient client = HttpClients.createDefault();
		rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
		Tmio1Servicio service = rest.patchForObject(REST_URI + "/service", nuevo, Tmio1Servicio.class);
		return "Guardado";
	}
	public List<Tmio1Bus> findAllBuses() {
		// TODO Auto-generated method stub
		ResponseEntity<TransactionBody<List<Tmio1Bus>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/buses",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Bus>>>() {
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
	public List<Tmio1Ruta> findAllRoutes() {
		ResponseEntity<TransactionBody<List<Tmio1Ruta>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/routes",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Ruta>>>() {
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
	public List<Tmio1Conductore> findAllDrivers() {
		ResponseEntity<TransactionBody<List<Tmio1Conductore>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/drivers",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Conductore>>>() {
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
	public List<Tmio1ServicioPK> findAllID() {
		ResponseEntity<TransactionBody<List<Tmio1ServicioPK>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/ids",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1ServicioPK>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1ServicioPK> ids= response.getBody().getBody();
			return ids;
		}
		return null;
	}
	public List<Tmio1ServicioPK> findAllIDWithoutRepeatedFechaInicio() {
		ResponseEntity<TransactionBody<List<Tmio1ServicioPK>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/date/ids",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1ServicioPK>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1ServicioPK> ids= response.getBody().getBody();
			return ids;
		}
		return null;
	}
	public List<Tmio1ServicioPK> findAllByFechaInicio(LocalDate fechaInicio) {
		ResponseEntity<TransactionBody<List<Tmio1ServicioPK>>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/date/all/"+ fechaInicio,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1ServicioPK>>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			List<Tmio1ServicioPK> ids= response.getBody().getBody();
			return ids;
		}
		return null;
	}
	public void delete(String hash) {
		rest.delete(REST_URI + "/service/" + hash);
	}
	public Tmio1Bus findByBusId(Integer idBus) {
		// TODO Auto-generated method stub
		ResponseEntity<TransactionBody<Tmio1Bus>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/bus/" +idBus,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Bus service= response.getBody().getBody();
			return service;
		}
		return null;
	}
	public Tmio1Conductore findByDriverId(String cedulaConductor) {
		// TODO Auto-generated method stub
		ResponseEntity<TransactionBody<Tmio1Conductore>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/driver/" +cedulaConductor,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Conductore service= response.getBody().getBody();
			return service;
		}
		return null;
	}
	public Tmio1Ruta findByRouteId(Integer idRuta) {
		ResponseEntity<TransactionBody<Tmio1Ruta>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/route/" +idRuta,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
			});
		} catch (HttpStatusCodeException e) {
			int statusCode=e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode+ " "+ e.getResponseBodyAsString());
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(response!=null) {
			Tmio1Ruta service= response.getBody().getBody();
			return service;
		}
		return null;
	}
	public Tmio1Servicio findByHash(String tempHash) {
		ResponseEntity<TransactionBody<Tmio1Servicio>> response= null;
		try {
			response= rest.exchange(REST_URI+"/service/"+tempHash,HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
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
}
