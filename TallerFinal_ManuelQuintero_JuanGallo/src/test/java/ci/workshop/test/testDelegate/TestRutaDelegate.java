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

import ci.workshop.test.delegate.RutaDelegate;
import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.RouteStateType;
import ci.workshop.test.model.Tmio1Ruta;

public class TestRutaDelegate {

	@InjectMocks
	private RutaDelegate rutaDel;

	@Mock
	private RestTemplate rest;

//	@Mock
//	private BusControllerRest busRest;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRouteFindAll() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva(RouteStateType.Inactiva.toString());
		ruta2.setDescripcion("Descripcion 2 ");
		ruta2.setDiaInicio(new BigDecimal(2));
		ruta2.setDiaFin(new BigDecimal(4));
		ruta2.setHoraInicio(new BigDecimal(18000));
		ruta2.setHoraFin(new BigDecimal(55000));
		ruta2.setNumero("B14");
		ArrayList<Tmio1Ruta> expected = new ArrayList<Tmio1Ruta>();
		expected.add(ruta);
		expected.add(ruta2);
		TransactionBody<List<Tmio1Ruta>> tb = new TransactionBody<List<Tmio1Ruta>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Ruta>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(rutaDel.REST_URI + "/rute/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1Ruta>>>() {
				})).thenReturn(re);
		List<Tmio1Ruta> actual = rutaDel.findAll();
		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}

	}
	@Test
	public void testBusSave() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");
		ResponseEntity<Tmio1Ruta> tb = new ResponseEntity<Tmio1Ruta>(ruta, HttpStatus.ACCEPTED);
		when(rest.postForEntity(rutaDel.REST_URI + "/rute", ruta, Tmio1Ruta.class)).thenReturn(tb);
		String x = rutaDel.saveRute(ruta);
		String expected = "Guardado";
		assertTrue(x.equals(expected));
	}

	@Test
	public void testFindById() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva(RouteStateType.Inactiva.toString());
		ruta2.setDescripcion("Descripcion 2 ");
		ruta2.setDiaInicio(new BigDecimal(2));
		ruta2.setDiaFin(new BigDecimal(4));
		ruta2.setHoraInicio(new BigDecimal(18000));
		ruta2.setHoraFin(new BigDecimal(55000));
		ruta2.setNumero("B14");
		TransactionBody<Tmio1Ruta> tb = new TransactionBody<Tmio1Ruta>();
		tb.setBody(ruta);
		ResponseEntity<TransactionBody<Tmio1Ruta>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(rutaDel.REST_URI + "/rute/" + ruta.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
				})).thenReturn(re);
		Tmio1Ruta actual = rutaDel.findById("" + ruta.getId());
		assertEquals(actual, ruta);
		when(rest.exchange(rutaDel.REST_URI + "/rute/" + ruta2.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
				})).thenReturn(null);
		actual = rutaDel.findById("" + ruta2.getId());
		assertEquals(actual, null);
	}
	@Test
	public void testDelete() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva(RouteStateType.Inactiva.toString());
		ruta2.setDescripcion("Descripcion 2 ");
		ruta2.setDiaInicio(new BigDecimal(2));
		ruta2.setDiaFin(new BigDecimal(4));
		ruta2.setHoraInicio(new BigDecimal(18000));
		ruta2.setHoraFin(new BigDecimal(55000));
		ruta2.setNumero("B14");
		TransactionBody<Tmio1Ruta> tb = new TransactionBody<Tmio1Ruta>();
		tb.setBody(ruta);
		ResponseEntity<TransactionBody<Tmio1Ruta>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(rutaDel.REST_URI + "/rute/" + ruta.getId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
				})).thenReturn(re);
		Tmio1Ruta actual = rutaDel.findById("" + ruta.getId());
		assertEquals(actual, ruta);
		rutaDel.removeRuta(ruta.getId()+"");
	}

	@Test
	public void testRutaFindAllStates() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");

		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva(RouteStateType.Inactiva.toString());
		ruta2.setDescripcion("Descripcion 2 ");
		ruta2.setDiaInicio(new BigDecimal(2));
		ruta2.setDiaFin(new BigDecimal(4));
		ruta2.setHoraInicio(new BigDecimal(18000));
		ruta2.setHoraFin(new BigDecimal(55000));
		ruta2.setNumero("B14");

		ArrayList<String> expected = new ArrayList<String>();
		expected.add(ruta.getActiva());
		expected.add(ruta2.getActiva());

		TransactionBody<List<String>> tb = new TransactionBody<List<String>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<String>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(rutaDel.REST_URI + "/rute/states", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<String>>>() {
				})).thenReturn(re);

		List<String> actual = rutaDel.findAllStates();

		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}

	}
}
