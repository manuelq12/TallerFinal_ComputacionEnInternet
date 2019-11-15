package ci.workshop.test.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ci.workshop.test.Taller3JPAManuelQuinteroApplication;
import ci.workshop.test.dao.IConductoresDao;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Servicio;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Taller3JPAManuelQuinteroApplication.class)
@Rollback(false)
public class TestConductore {
	@Autowired
	private IConductoresDao conductDao;
		
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(conductDao);
		
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Wilder");
		driver.setApellidos("Quintero Parra");
		driver.setCedula("16271754");
		driver.setFechaContratacion(LocalDate.of(2019, 06, 18));
		driver.setFechaNacimiento(LocalDate.of(1965, 04, 19));
		
		conductDao.save(driver);
		
		Tmio1Conductore driverS = conductDao.findById("16271754");
		assertEquals(driver.getCedula(),driverS.getCedula());
		assertEquals(driver.getNombre(), driverS.getNombre());
		assertEquals(driver.getApellidos(), driverS.getApellidos());
		assertTrue(driver.getFechaNacimiento().compareTo(driverS.getFechaNacimiento()) == 0);
		assertTrue(driver.getFechaContratacion().compareTo(driverS.getFechaContratacion()) == 0);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		assertNotNull(conductDao);
		
		Tmio1Conductore driver = new Tmio1Conductore();
		driver.setNombre("Camilo");
		driver.setApellidos("Quintero Cubillos");
		driver.setCedula("1113686421");
		driver.setFechaContratacion(LocalDate.of(2019, 06, 18));
		driver.setFechaNacimiento(LocalDate.of(1997, 01, 28));
		
		conductDao.save(driver);
		
		Tmio1Conductore driverS = conductDao.findById("1113686421");
		assertEquals(driver.getCedula(),driverS.getCedula());
		assertEquals(driver.getNombre(), driverS.getNombre());
		assertEquals(driver.getApellidos(), driverS.getApellidos());
		assertTrue(driver.getFechaNacimiento().compareTo(driverS.getFechaNacimiento()) == 0);
		assertTrue(driver.getFechaContratacion().compareTo(driverS.getFechaContratacion()) == 0);

		driver.setNombre("Camilo Andres");
		driver.setApellidos("Quintero");
		driver.setFechaContratacion(LocalDate.of(2020, 10, 7));
		driver.setFechaNacimiento(LocalDate.of(1997, 6, 11));
		
		conductDao.update(driver);
		driverS = conductDao.findById("1113686421");
		assertEquals(driver.getCedula(),driverS.getCedula());
		assertEquals(driver.getNombre(), driverS.getNombre());
		assertEquals(driver.getApellidos(), driverS.getApellidos());
		assertTrue(driver.getFechaNacimiento().compareTo(driverS.getFechaNacimiento()) == 0);
		assertTrue(driver.getFechaContratacion().compareTo(driverS.getFechaContratacion()) == 0);
		
		conductDao.delete(driver);
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		assertNotNull(conductDao);
		
		Tmio1Conductore el = new Tmio1Conductore();
		el.setNombre("Paulina");
		el.setApellidos("Cubillos");
		el.setCedula("123456789");
		el.setFechaContratacion(LocalDate.of(2019, 06, 18));
		el.setFechaNacimiento(LocalDate.of(1980, 11, 7));
		 
		conductDao.save(el);
		
		Tmio1Conductore driverS = conductDao.findById("123456789");

		assertNotNull(el);
		
		conductDao.delete(el);
		
		driverS = conductDao.findById("123456789");
		assertNull(driverS);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		assertNotNull(conductDao);	
		Tmio1Conductore stub = new Tmio1Conductore();
		stub.setNombre("Camilo");
		stub.setApellidos("Quintero Cubillos");
		stub.setCedula("1113686421");
		stub.setFechaContratacion(LocalDate.of(2019, 06, 18));
		stub.setFechaNacimiento(LocalDate.of(1997, 01, 28));
		
		conductDao.save(stub);
		
		Tmio1Conductore driverS = conductDao.findById("1113686421");
		assertEquals(stub.getCedula(),driverS.getCedula());
		assertEquals(stub.getNombre(), driverS.getNombre());
		assertEquals(stub.getApellidos(), driverS.getApellidos());
		assertTrue(stub.getFechaNacimiento().compareTo(driverS.getFechaNacimiento()) == 0);
		assertTrue(stub.getFechaContratacion().compareTo(driverS.getFechaContratacion()) == 0);
		
		conductDao.delete(stub);
	}

	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNombre() {
		Tmio1Conductore d1 = new Tmio1Conductore();
		d1.setNombre("Camilo");
		d1.setApellidos("Quintero Cubillos");
		d1.setCedula("1113686421");
		d1.setFechaContratacion(LocalDate.of(2019, 06, 18));
		d1.setFechaNacimiento(LocalDate.of(1997, 01, 28));
		
		Tmio1Conductore d2 = new Tmio1Conductore();
		d2.setNombre("Manuel");
		d2.setApellidos("Quintero Cubillos");
		d2.setCedula("1113695637");
		d2.setFechaContratacion(LocalDate.of(2019, 06, 18));
		d2.setFechaNacimiento(LocalDate.of(1999, 02, 12));
		
		Tmio1Conductore d3 = new Tmio1Conductore();
		d3.setNombre("Manuel");
		d3.setApellidos("Pascuas");
		d3.setCedula("111484152");
		d3.setFechaContratacion(LocalDate.of(2019, 06, 18));
		d3.setFechaNacimiento(LocalDate.of(1998, 05, 19));
		
		conductDao.save(d1);
		conductDao.save(d2);
		conductDao.save(d3);
		
		List<Tmio1Conductore> driverS = conductDao.findByNombre("Manuel");

		
		int count = 0;
		for (Tmio1Conductore tmio1Conductore : driverS) {			
			if(count == 0) {
				assertEquals(d2.getCedula(), tmio1Conductore.getCedula());
				assertEquals(d2.getNombre(), tmio1Conductore.getNombre());
				assertEquals(d2.getApellidos(), tmio1Conductore.getApellidos());
				assertTrue(d2.getFechaNacimiento().compareTo(tmio1Conductore.getFechaNacimiento()) == 0);
				assertTrue(d2.getFechaContratacion().compareTo(tmio1Conductore.getFechaContratacion()) == 0);
				count++;
			}
			else if (count == 1) {
				assertEquals(d3.getCedula(), tmio1Conductore.getCedula());
				assertEquals(d3.getNombre(), tmio1Conductore.getNombre());
				assertEquals(d3.getApellidos(), tmio1Conductore.getApellidos());
				assertTrue(d3.getFechaNacimiento().compareTo(tmio1Conductore.getFechaNacimiento()) == 0);
				assertTrue(d3.getFechaContratacion().compareTo(tmio1Conductore.getFechaContratacion()) == 0);
				count++;
			}
			else {
				fail();
			}
		}
	
		conductDao.delete(d1);
		conductDao.delete(d2);
		conductDao.delete(d3);
	}
	
	//TODO arreglar

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByApellido() {
		assertNotNull(conductDao);
		
		Tmio1Conductore d1 = new Tmio1Conductore();
		d1.setNombre("Camilo");
		d1.setApellidos("Quintero Cubillos");
		d1.setCedula("1113686421");
		d1.setFechaContratacion(LocalDate.of(2019, 06, 18));
		d1.setFechaNacimiento(LocalDate.of(1997, 01, 28));
		
		Tmio1Conductore d2 = new Tmio1Conductore();
		d2.setNombre("Manuel");
		d2.setApellidos("Quintero Cubillos");
		d2.setCedula("1113695637");
		d2.setFechaContratacion(LocalDate.of(2019, 06, 18));
		d2.setFechaNacimiento(LocalDate.of(1999, 02, 12));
		
		Tmio1Conductore d3 = new Tmio1Conductore();
		d3.setNombre("Juan");
		d3.setApellidos("Gallo Plaza");
		d3.setCedula("111484152");
		d3.setFechaContratacion(LocalDate.of(2019, 06, 18));
		d3.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		
		conductDao.save(d1);
		conductDao.save(d2);
		conductDao.save(d3);
		
		List<Tmio1Conductore> driverS = conductDao.findByApellido("Quintero Cubillos");

		
		int count = 0;
		for (Tmio1Conductore tmio1Conductore : driverS) {
			if(count == 0) {
				assertEquals(d1.getCedula(), tmio1Conductore.getCedula());
				assertEquals(d1.getNombre(), tmio1Conductore.getNombre());
				assertEquals(d1.getApellidos(), tmio1Conductore.getApellidos());
				assertTrue(d1.getFechaNacimiento().compareTo(tmio1Conductore.getFechaNacimiento()) == 0);
				assertTrue(d1.getFechaContratacion().compareTo(tmio1Conductore.getFechaContratacion()) == 0);
				count++;
			}
			else if (count == 1) {
				assertEquals(d2.getCedula(), tmio1Conductore.getCedula());
				assertEquals(d2.getNombre(), tmio1Conductore.getNombre());
				assertEquals(d2.getApellidos(), tmio1Conductore.getApellidos());
				assertTrue(d2.getFechaNacimiento().compareTo(tmio1Conductore.getFechaNacimiento()) == 0);
				assertTrue(d2.getFechaContratacion().compareTo(tmio1Conductore.getFechaContratacion()) == 0);
				count++;
			}
			else {
				fail();
			}
		}
	
		conductDao.delete(d1);
		conductDao.delete(d2);
		conductDao.delete(d3);
	}

	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {

		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		
		conductDao.save(newConductore);
		
		Tmio1Conductore newConductore2 = new Tmio1Conductore();
		newConductore2.setApellidos("Caicedo");
		newConductore2.setCedula("16356137");
		newConductore2.setNombre("Camilo");
		newConductore2.setFechaContratacion(LocalDate.of(2017, 3, 1));
		newConductore2.setFechaNacimiento(LocalDate.of(1999, 7, 1));
	
		conductDao.save(newConductore2);
		
		Tmio1Conductore newConductore3 = new Tmio1Conductore();
		newConductore3.setApellidos("Quintero");
		newConductore3.setCedula("1113695637");
		newConductore3.setNombre("Manuel");
		newConductore3.setFechaContratacion(LocalDate.of(2016, 10, 31));
		newConductore3.setFechaNacimiento(LocalDate.of(1999, 2, 12));
	
		conductDao.save(newConductore3);
		
		List<Tmio1Conductore> all = conductDao.findAll();
		
		ArrayList<Tmio1Conductore> expected = new ArrayList<Tmio1Conductore>();
		expected.add(newConductore);
		expected.add(newConductore2);
		expected.add(newConductore3);
		
		for (int i = 0; i < all.size(); i++) {
			assertTrue(expected.contains(all.get(i)));
		}
		
		conductDao.delete(newConductore);
		conductDao.delete(newConductore2);
		conductDao.delete(newConductore3);
		
	}

}
