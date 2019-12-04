package ci.workshop.test.testDelegate;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ci.workshop.test.delegate.SitioDelegate;
import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Sitio;

public class TestSitioDelegate {

	@InjectMocks
	private SitioDelegate sitioDel;

	@Mock
	private RestTemplate rest;

//	@Mock
//	private BusControllerRest busRest;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSitioFindAll() {

		Tmio1Sitio s1 = new Tmio1Sitio();
		s1.setNombre("Simon Bolivar");
		s1.setDescripcion("Parada 1");
		s1.setId(1);

		Tmio1Sitio s2 = new Tmio1Sitio();
		s2.setNombre("Chipichape");
		s2.setDescripcion("Parada 2");
		s2.setId(2);

		ArrayList<Tmio1Sitio> expected = new ArrayList<Tmio1Sitio>();
		expected.add(s1);
		expected.add(s2);

		TransactionBody<List<Tmio1Sitio>> tb = new TransactionBody<List<Tmio1Sitio>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Sitio>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(sitioDel.REST_URI + "/sitio/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1Sitio>>>() {
				})).thenReturn(re);

		List<Tmio1Sitio> actual = sitioDel.findAll();

		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}

	}

	@Test
	public void testSitioSave() {

		Tmio1Sitio s1 = new Tmio1Sitio();
		s1.setNombre("Simon Bolivar");
		s1.setDescripcion("Parada 1");
		s1.setId(1);

		ResponseEntity<Tmio1Sitio> tb = new ResponseEntity<Tmio1Sitio>(s1, HttpStatus.ACCEPTED);

		when(rest.postForEntity(sitioDel.REST_URI + "/sitio", s1, Tmio1Sitio.class)).thenReturn(tb);

		String x = sitioDel.saveSitio(s1);
		String expected = "Guardado";

		assertTrue(x.equals(expected));
	}

	@Test
	public void testFindById() {
		Tmio1Sitio s1 = new Tmio1Sitio();
		s1.setNombre("Simon Bolivar");
		s1.setDescripcion("Parada 1");
		s1.setId(1);

		Tmio1Sitio s2 = new Tmio1Sitio();
		s2.setNombre("Chipichape");
		s2.setDescripcion("Parada 2");
		s2.setId(2);

		TransactionBody<Tmio1Sitio> tb = new TransactionBody<Tmio1Sitio>();
		tb.setBody(s1);
		ResponseEntity<TransactionBody<Tmio1Sitio>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(sitioDel.REST_URI + "/sitio/" + s1.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
				})).thenReturn(re);

		Tmio1Sitio actual = sitioDel.findById((int) s1.getId());

		assertEquals(actual, s1);

		when(rest.exchange(sitioDel.REST_URI + "/sitio/" + s2.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
				})).thenReturn(null);

		actual = sitioDel.findById((int) s2.getId());
		assertEquals(actual, null);
	}

	

	@Test
	public void testUpdate() {
		Tmio1Sitio s1 = new Tmio1Sitio();
		s1.setNombre("Simon Bolivar");
		s1.setDescripcion("Parada 1");
		s1.setId(1);
		TransactionBody<Tmio1Sitio> tb = new TransactionBody<Tmio1Sitio>();
		tb.setBody(s1);
		ResponseEntity<TransactionBody<Tmio1Sitio>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(sitioDel.REST_URI + "/sitio/" + s1.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
				})).thenReturn(re);
		Tmio1Sitio actual = sitioDel.findById((int) s1.getId());
		assertEquals(actual, s1);
		s1.setDescripcion("Parada 2");
		s1.setNombre("Chipichape");
		when(rest.patchForObject(sitioDel.REST_URI + "/sitio/e", s1, Tmio1Sitio.class)).thenReturn(s1);
		String x = sitioDel.updateSitio(s1);
		String expected = "Enviado";
		assertEquals(x, expected);
		tb = new TransactionBody<Tmio1Sitio>();
		tb.setBody(s1);
		re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(sitioDel.REST_URI + "/sitio/" + s1.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Sitio>>() {
				})).thenReturn(re);

		actual = sitioDel.findById((int) s1.getId());
		assertEquals(actual, s1);
		assertEquals(actual.getDescripcion(), "Parada 2");
		assertEquals(actual.getNombre(), "Chipichape");

	}
}
