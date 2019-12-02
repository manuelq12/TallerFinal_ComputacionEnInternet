package ci.workshop.test.testDelegate;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import ci.workshop.test.delegate.ConductoresDelegate;
import ci.workshop.test.delegate.TransactionBody;
import ci.workshop.test.model.Tmio1Conductore;

public class TestConductoresDelegate {
	
	@InjectMocks
	private ConductoresDelegate conDel;
	
	@Mock
	private RestTemplate rest;
	
	@BeforeTest
	public void beforeClass() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	  public void testDriversFindAll() {
		  
			Tmio1Conductore d1 = new Tmio1Conductore();
			d1.setCedula("1");
			d1.setApellidos("Quintero");
			d1.setFechaContratacion(LocalDate.of(2019, 1, 1));
			d1.setFechaNacimiento(LocalDate.of(1999, 2, 12));
			d1.setNombre("Manuel");
			
			
			Tmio1Conductore d2 = new Tmio1Conductore();
			d2.setCedula("2");
			d2.setApellidos("Gallo");
			d2.setFechaContratacion(LocalDate.of(2019, 1, 1));
			d2.setFechaNacimiento(LocalDate.of(1999, 12, 9));
			d2.setNombre("Juan");
			
			ArrayList<Tmio1Conductore> expected = new ArrayList<Tmio1Conductore>();
			expected.add(d1);
			expected.add(d2);
			
			TransactionBody<List<Tmio1Conductore>> tb = new TransactionBody<List<Tmio1Conductore>>();
			tb.setBody(expected);
			ResponseEntity<TransactionBody<List<Tmio1Conductore>>> re = new ResponseEntity<>(tb,HttpStatus.ACCEPTED);
			
			when(rest.exchange(conDel.REST_URI +"/driver/all",HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<List<Tmio1Conductore>>>() {
			})).thenReturn(re);
			
			List<Tmio1Conductore> actual = conDel.findAll();
			
			assertEquals(actual, expected);
			assertEquals(actual.size(),expected.size());
			for (int i = 0; i < expected.size(); i++) {
				assertEquals(actual.get(i),expected.get(i));
			}
			
	  }	
	
	@Test
	public void testDriverSave() {
	  
		Tmio1Conductore d1 = new Tmio1Conductore();
		d1.setCedula("1");
		d1.setApellidos("Quintero");
		d1.setFechaContratacion(LocalDate.of(2019, 1, 1));
		d1.setFechaNacimiento(LocalDate.of(1999, 2, 12));
		d1.setNombre("Manuel");
		
		ResponseEntity<Tmio1Conductore> tb = new ResponseEntity<Tmio1Conductore>(d1,HttpStatus.ACCEPTED);
	  
		when(rest.postForEntity(conDel.REST_URI + "/driver", d1, Tmio1Conductore.class)).thenReturn(tb);
		
		String x = conDel.saveDriver(d1);
		String expected = "Guardado";
		
		assertTrue(x.equals(expected));
}
	
	@Test
	public void testFindById() {
		Tmio1Conductore d1 = new Tmio1Conductore();
		d1.setCedula("1");
		d1.setApellidos("Quintero");
		d1.setFechaContratacion(LocalDate.of(2019, 1, 1));
		d1.setFechaNacimiento(LocalDate.of(1999, 2, 12));
		d1.setNombre("Manuel");
		
		
		Tmio1Conductore d2 = new Tmio1Conductore();
		d2.setCedula("2");
		d2.setApellidos("Gallo");
		d2.setFechaContratacion(LocalDate.of(2019, 1, 1));
		d2.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		d2.setNombre("Juan");
		
		TransactionBody<Tmio1Conductore> tb = new TransactionBody<Tmio1Conductore>();
		tb.setBody(d1);
		ResponseEntity<TransactionBody<Tmio1Conductore>> re = new ResponseEntity<>(tb,HttpStatus.ACCEPTED);
		
		when(rest.exchange(conDel.REST_URI +"/driver/"+d1.getCedula(),HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
		})).thenReturn(re);
		
		
		Tmio1Conductore actual =  conDel.findById(d1.getCedula());
		
		assertEquals(actual, d1);
		
		
		when(rest.exchange(conDel.REST_URI +"/driver/"+d2.getCedula(),HttpMethod.GET,null, new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
		})).thenReturn(null);
		
		actual =  conDel.findById(d2.getCedula());	
		assertEquals(actual, null);
	}
	
	
}
