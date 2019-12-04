package ci.workshop.test.testDelegate;

import static org.junit.Assert.assertNull;
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
import ci.workshop.test.delegate.SitioRutaDelegate;
import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.BusType;
import ci.workshop.test.model.RouteStateType;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Sitio;
import ci.workshop.test.model.Tmio1SitiosRuta;
import ci.workshop.test.model.Tmio1SitiosRutaPK;

public class TestSitioRutaDelegate {
	@InjectMocks
	private SitioRutaDelegate sitioDel;

	@Mock
	private RestTemplate rest;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSitioRutaFindAll() {

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

		Tmio1Sitio s1 = new Tmio1Sitio();
		s1.setNombre("Simon Bolivar");
		s1.setDescripcion("Parada 1");
		s1.setId(1);

		Tmio1Sitio s2 = new Tmio1Sitio();
		s2.setNombre("Chipichape");
		s2.setDescripcion("Parada 2");
		s2.setId(2);

		Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
		pk.setIdRuta(ruta.getId());
		pk.setIdSitio((int) s1.getId());
		pk.setHashId(pk.hashCode());

		Tmio1SitiosRutaPK pk2 = new Tmio1SitiosRutaPK();
		pk2.setIdRuta(ruta2.getId());
		pk2.setIdSitio((int) s2.getId());
		pk2.setHashId(pk2.hashCode());

		Tmio1SitiosRuta n = new Tmio1SitiosRuta();
		Tmio1SitiosRuta n2 = new Tmio1SitiosRuta();
		n.setHash(pk.getHashId());
		n.setId(pk);
		n.setTmio1Ruta1(ruta);
		n.setTmio1Sitio1(s1);

		n2.setHash(pk2.getHashId());
		n2.setId(pk2);
		n2.setTmio1Ruta1(ruta2);
		n2.setTmio1Sitio1(s2);

		ArrayList<Tmio1SitiosRuta> expected = new ArrayList<Tmio1SitiosRuta>();
		expected.add(n);
		expected.add(n2);

		TransactionBody<List<Tmio1SitiosRuta>> tb = new TransactionBody<List<Tmio1SitiosRuta>>();
		tb.setBody(expected);
		ResponseEntity<TransactionBody<List<Tmio1SitiosRuta>>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);

		when(rest.exchange(sitioDel.REST_URI + "/sitioRuta/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<List<Tmio1SitiosRuta>>>() {
				})).thenReturn(re);

		List<Tmio1SitiosRuta> actual = sitioDel.findAll();

		assertEquals(actual, expected);
		assertEquals(actual.size(), expected.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(actual.get(i), expected.get(i));
		}

	}

	@Test
	public void testSitioRutaSave() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");
		Tmio1Sitio s1 = new Tmio1Sitio();
		s1.setNombre("Simon Bolivar");
		s1.setDescripcion("Parada 1");
		s1.setId(1);
		Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
		pk.setIdRuta(1);
		pk.setIdSitio(1);
		pk.setHashId(pk.hashCode());
		Tmio1SitiosRuta n = new Tmio1SitiosRuta();
		n.setHash(pk.getHashId());
		n.setId(pk);
		n.setTmio1Ruta1(ruta);
		n.setTmio1Sitio1(s1);

		ResponseEntity<Tmio1SitiosRuta> tb = new ResponseEntity<Tmio1SitiosRuta>(n, HttpStatus.ACCEPTED);
		when(rest.postForEntity(sitioDel.REST_URI + "/sitioRuta", n, Tmio1SitiosRuta.class)).thenReturn(tb);

		String x = sitioDel.saveSitio(n);
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
		Tmio1Sitio s1 = new Tmio1Sitio();
		s1.setNombre("Simon Bolivar");
		s1.setDescripcion("Parada 1");
		s1.setId(1);
		Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
		pk.setIdRuta(1);
		pk.setIdSitio(1);
		pk.hashCode();
		Tmio1SitiosRuta n = new Tmio1SitiosRuta();
		n.setHash(pk.getHashId());
		n.setId(pk);
		n.setTmio1Ruta1(ruta);
		n.setTmio1Sitio1(s1);

		TransactionBody<Tmio1SitiosRuta> tb = new TransactionBody<Tmio1SitiosRuta>();
		tb.setBody(n);
		ResponseEntity<TransactionBody<Tmio1SitiosRuta>> re = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		when(rest.exchange(sitioDel.REST_URI + "/sitioRuta/" + n.getHash(), HttpMethod.GET, null,
				new ParameterizedTypeReference<TransactionBody<Tmio1SitiosRuta>>() {
				})).thenReturn(re);
		Tmio1SitiosRuta actual = sitioDel.getByID(n.getHash() + "");
		assertEquals(actual, n);
	}
}
