package ci.workshop.test.testDelegate;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
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

import ci.workshop.test.delegate.BusDelegate;
import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.BusType;
import ci.workshop.test.model.Tmio1Bus;

public class TestBusDelegate {

	@InjectMocks
	private BusDelegate busDel;

	@Mock
	private RestTemplate rest;

//	@Mock
//	private BusControllerRest busRest;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testBusFindAll() {

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(150));
		bus.setMarca("Mercedes-Benz");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("KHA 430");
		bus.setTipo(BusType.A.toString());

		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(150));
		bus2.setMarca("Mercedes-Benz");
		bus2.setModelo(new BigDecimal(2019));
		bus2.setPlaca("HBL 802");
		bus2.setTipo(BusType.T.toString());

		ArrayList<Tmio1Bus> expected = new ArrayList<Tmio1Bus>();
		expected.add(bus);
		expected.add(bus2);

		TransactionBody<List<Tmio1Bus>> tb = new TransactionBody<List<Tmio1Bus>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Bus>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(busDel.REST_URI + "/bus/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1Bus>>>() {
				})).thenReturn(re);

		List<Tmio1Bus> actual = busDel.findAll();

		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}

	}

	@Test
	public void testBusSave() {

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(150));
		bus.setMarca("Mercedes-Benz");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("KHA 430");
		bus.setTipo(BusType.A.toString());

		ResponseEntity<Tmio1Bus> tb = new ResponseEntity<Tmio1Bus>(bus, HttpStatus.ACCEPTED);

		when(rest.postForEntity(busDel.REST_URI + "/bus", bus, Tmio1Bus.class)).thenReturn(tb);

		String x = busDel.saveBus(bus);
		String expected = "Guardado";

		assertTrue(x.equals(expected));
	}

	@Test
	public void testFindById() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(150));
		bus.setId(1);
		bus.setMarca("Mercedes-Benz");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("KHA 430");
		bus.setTipo(BusType.A.toString());

		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setId(2);
		bus2.setCapacidad(new BigDecimal(150));
		bus2.setMarca("Mercedes-Benz");
		bus2.setModelo(new BigDecimal(2019));
		bus2.setPlaca("HBL 802");
		bus2.setTipo(BusType.T.toString());

		TransactionBody<Tmio1Bus> tb = new TransactionBody<Tmio1Bus>();
		tb.setBody(bus);
		ResponseEntity<TransactionBody<Tmio1Bus>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(busDel.REST_URI + "/bus/" + bus.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
				})).thenReturn(re);

		Tmio1Bus actual = busDel.findById(bus.getId());

		assertEquals(actual, bus);

		when(rest.exchange(busDel.REST_URI + "/bus/" + bus2.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
				})).thenReturn(null);

		actual = busDel.findById(bus2.getId());
		assertEquals(actual, null);
	}

	@Test
	public void testBusFindAllTypes() {

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(150));
		bus.setMarca("Mercedes-Benz");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("KHA 430");
		bus.setTipo(BusType.A.toString());

		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(150));
		bus2.setMarca("Mercedes-Benz");
		bus2.setModelo(new BigDecimal(2019));
		bus2.setPlaca("HBL 802");
		bus2.setTipo(BusType.T.toString());

		ArrayList<String> expected = new ArrayList<String>();
		expected.add(bus.getTipo());
		expected.add(bus2.getTipo());

		TransactionBody<List<String>> tb = new TransactionBody<List<String>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<String>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(busDel.REST_URI + "/bus/types", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<String>>>() {
				})).thenReturn(re);

		List<String> actual = busDel.findAllTypes();

		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}

	}
}
