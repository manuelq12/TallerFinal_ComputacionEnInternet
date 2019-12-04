package ci.workshop.test.testDelegate;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

import ci.workshop.test.delegate.ServiciosDelegate;
import ci.workshop.test.delegate.SitioRutaDelegate;
import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.BusType;
import ci.workshop.test.model.DateObject;
import ci.workshop.test.model.RouteStateType;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.model.Tmio1SitiosRuta;
import ci.workshop.test.model.Tmio1SitiosRutaPK;

public class TestServicioDelegate {
	@InjectMocks
	private ServiciosDelegate serviceDel;
	@Mock
	private RestTemplate rest;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testServiceFindAll() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(10000));
		bus2.setMarca("Mercedes-Benz");
		bus2.setModelo(new BigDecimal(2011));
		bus2.setPlaca("KHA 430");
		bus2.setTipo(BusType.A.toString());
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		Tmio1Conductore newConductore2 = new Tmio1Conductore();
		newConductore2.setApellidos("Caicedo");
		newConductore2.setCedula("16356137");
		newConductore2.setNombre("Camilo");
		newConductore2.setFechaContratacion(LocalDate.of(2017, 3, 1));
		newConductore2.setFechaNacimiento(LocalDate.of(1999, 7, 1));
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		Tmio1Ruta route2 = new Tmio1Ruta();
		route2.setActiva("Activa");
		route2.setDescripcion("D2");
		route2.setDiaInicio(new BigDecimal(1));
		route2.setDiaFin(new BigDecimal(5));
		route2.setHoraInicio(new BigDecimal(34000));
		route2.setHoraFin(new BigDecimal(62000));
		route2.setNumero("A12");
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		Tmio1Servicio newService = new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		Tmio1ServicioPK pk2 = new Tmio1ServicioPK();
		pk2.setCedulaConductor(newConductore2.getCedula());// Caicedo
		pk2.setFechaFin(LocalDate.of(2019, 12, 9));
		pk2.setFechaInicio(LocalDate.of(2015, 12, 9));
		pk2.setIdBus(bus2.getId());// KHA 430
		pk2.setIdRuta(route2.getId()); // A12
		Tmio1Servicio newService2 = new Tmio1Servicio();
		newService2.setId(pk2);
		newService2.setTmio1Bus(bus1);
		newService2.setTmio1Conductore(newConductore2);
		newService2.setTmio1Ruta(route2);
		ArrayList<Tmio1Servicio> expected = new ArrayList<Tmio1Servicio>();
		expected.add(newService);
		expected.add(newService2);
		TransactionBody<List<Tmio1Servicio>> tb = new TransactionBody<List<Tmio1Servicio>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(serviceDel.REST_URI + "/service/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1Servicio>>>() {
				})).thenReturn(re);
		List<Tmio1Servicio> actual = serviceDel.findAll();
		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}
	}

	@Test
	public void testSaveService() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		Tmio1Servicio newService = new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		ResponseEntity<Tmio1Servicio> tb = new ResponseEntity<Tmio1Servicio>(newService, HttpStatus.ACCEPTED);
		when(rest.postForEntity(serviceDel.REST_URI + "/service", newService, Tmio1Servicio.class)).thenReturn(tb);
		String x = serviceDel.saveService(newService);
		String expected = "Guardado";
		assertTrue(x.equals(expected));
	}

	@Test
	public void testFindByHash() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		Tmio1Servicio newService = new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		TransactionBody<Tmio1Servicio> tb = new TransactionBody<Tmio1Servicio>();
		tb.setBody(newService);
		ResponseEntity<TransactionBody<Tmio1Servicio>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(serviceDel.REST_URI + "/service/" + newService.getHash(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
				})).thenReturn(re);
		Tmio1Servicio actual = serviceDel.findByHash(newService.getHash() + "");
		assertEquals(actual, newService);
	}


	public void testfindByFechas() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		Tmio1Servicio newService = new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		ArrayList<Tmio1Servicio> expected = new ArrayList<Tmio1Servicio>();
		expected.add(newService);
		TransactionBody<List<Tmio1Servicio>> tb = new TransactionBody<List<Tmio1Servicio>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(serviceDel.REST_URI + "/service/date/" + newService.getId().getFechaInicio().toString(),
				HttpMethod.GET, null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Servicio>>>() {
				})).thenReturn(re);
		List<Tmio1Servicio> actual = serviceDel
				.findByFechas(new DateObject(newService.getId().getFechaInicio(), newService.getId().getFechaFin()));
		assertEquals(actual, expected);
	}

	public void findAllBuses() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(10000));
		bus2.setMarca("Mercedes-Benz");
		bus2.setModelo(new BigDecimal(2011));
		bus2.setPlaca("KHA 430");
		bus2.setTipo(BusType.A.toString());
		ArrayList<Tmio1Bus> expected = new ArrayList<Tmio1Bus>();
		expected.add(bus1);
		expected.add(bus2);
		TransactionBody<List<Tmio1Bus>> tb = new TransactionBody<List<Tmio1Bus>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Bus>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(serviceDel.REST_URI + "/service/buses", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1Bus>>>() {
				})).thenReturn(re);

		List<Tmio1Bus> actual = serviceDel.findAllBuses();
		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());

		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}
	}

	public void findAllRoutes() {
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		Tmio1Ruta route2 = new Tmio1Ruta();
		route2.setActiva("Activa");
		route2.setDescripcion("D2");
		route2.setDiaInicio(new BigDecimal(1));
		route2.setDiaFin(new BigDecimal(5));
		route2.setHoraInicio(new BigDecimal(34000));
		route2.setHoraFin(new BigDecimal(62000));
		route2.setNumero("A12");

		ArrayList<Tmio1Ruta> expected = new ArrayList<Tmio1Ruta>();
		expected.add(route1);
		expected.add(route2);

		TransactionBody<List<Tmio1Ruta>> tb = new TransactionBody<List<Tmio1Ruta>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Ruta>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(serviceDel.REST_URI + "/service/routes", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1Ruta>>>() {
				})).thenReturn(re);

		List<Tmio1Ruta> actual = serviceDel.findAllRoutes();
		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());

		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}
	}

	public void findAllDrivers() {

		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		Tmio1Conductore newConductore2 = new Tmio1Conductore();
		newConductore2.setApellidos("Caicedo");
		newConductore2.setCedula("16356137");
		newConductore2.setNombre("Camilo");
		newConductore2.setFechaContratacion(LocalDate.of(2017, 3, 1));
		newConductore2.setFechaNacimiento(LocalDate.of(1999, 7, 1));

		ArrayList<Tmio1Conductore> expected = new ArrayList<Tmio1Conductore>();
		expected.add(newConductore);
		expected.add(newConductore2);

		TransactionBody<List<Tmio1Conductore>> tb = new TransactionBody<List<Tmio1Conductore>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1Conductore>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(serviceDel.REST_URI + "/service/drivers", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1Conductore>>>() {
				})).thenReturn(re);

		List<Tmio1Conductore> actual = serviceDel.findAllDrivers();
		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());

		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}
	}

	public void findAllID() {
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor("16356137");// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(1);// HBL 802
		pk.setIdRuta(1); // A11
		Tmio1ServicioPK pk2 = new Tmio1ServicioPK();
		pk2.setCedulaConductor("1104820995");// Caicedo
		pk2.setFechaFin(LocalDate.of(2019, 12, 9));
		pk2.setFechaInicio(LocalDate.of(2015, 12, 9));
		pk2.setIdBus(2);// KHA 430
		pk2.setIdRuta(2); // A12
		ArrayList<Tmio1ServicioPK> expected = new ArrayList<Tmio1ServicioPK>();
		expected.add(pk);
		expected.add(pk2);
		TransactionBody<List<Tmio1ServicioPK>> tb = new TransactionBody<List<Tmio1ServicioPK>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1ServicioPK>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(serviceDel.REST_URI + "/service/ids", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1ServicioPK>>>() {
				})).thenReturn(re);
		List<Tmio1ServicioPK> actual = serviceDel.findAllID();
		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}
	}
}
