package ci.workshop.test.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
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

import ci.workshop.test.TallerFinalManuelQuinteroJuanGalloApplication;
import ci.workshop.test.dao.IBusesDao;
import ci.workshop.test.dao.IConductoresDao;
import ci.workshop.test.dao.IRutasDao;
import ci.workshop.test.dao.IServiciosDao;
import ci.workshop.test.model.BusType;
import ci.workshop.test.model.RouteStateType;
import ci.workshop.test.model.Tmio1Bus;
import ci.workshop.test.model.Tmio1Conductore;
import ci.workshop.test.model.Tmio1Ruta;
import ci.workshop.test.model.Tmio1Servicio;
import ci.workshop.test.model.Tmio1ServicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TallerFinalManuelQuinteroJuanGalloApplication.class)
@Rollback(false)
public class TestServicioDao {

	@Autowired
	private IRutasDao rutasDao;
	@Autowired
	private IBusesDao busDao;
	@Autowired
	private IServiciosDao serviceDao;
	@Autowired
	private IConductoresDao conductDao;
	
	private List<Tmio1Servicio> expected0 = new ArrayList<Tmio1Servicio>();
	private List<Object[]> expected1 = new ArrayList<Object[]>();
	private List<Tmio1Bus> expected2 = new ArrayList<Tmio1Bus>();
	private List<Tmio1Ruta> expected3 = new ArrayList<Tmio1Ruta>();
	private ArrayList<Tmio1Ruta> delRutas = new ArrayList<Tmio1Ruta>();
	private ArrayList<Tmio1Bus> delBus = new ArrayList<Tmio1Bus>();
	private ArrayList<Tmio1Conductore> delConduct = new ArrayList<Tmio1Conductore>();
	private ArrayList<Tmio1Servicio> delServicio = new ArrayList<Tmio1Servicio>();
	
	public void contextPunto3() {
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
		
		Tmio1Bus bus3 = new Tmio1Bus();
		bus3.setCapacidad(new BigDecimal(8000));
		bus3.setMarca("Mercedes-Benz");
		bus3.setModelo(new BigDecimal(2008));
		bus3.setPlaca("DBT 232");
		bus3.setTipo(BusType.P.toString());
		
		Tmio1Bus bus4 = new Tmio1Bus();
		bus4.setCapacidad(new BigDecimal(7000));
		bus4.setMarca("Mercedes-Benz");
		bus4.setModelo(new BigDecimal(2010));
		bus4.setPlaca("URZ 395");
		bus4.setTipo(BusType.T.toString());
		
		busDao.save(bus1);
		busDao.save(bus2);
		busDao.save(bus3);
		busDao.save(bus4);
		
		delBus.add(bus1);
		delBus.add(bus2);
		delBus.add(bus3);
		delBus.add(bus4);
		
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
		
		delConduct.add(newConductore);
		delConduct.add(newConductore2);
		delConduct.add(newConductore3);
		
		Tmio1Ruta route1 = new Tmio1Ruta();
		
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		
		rutasDao.save(route1);
		
		Tmio1Ruta route2 = new Tmio1Ruta();
		
		route2.setActiva("Activa");
		route2.setDescripcion("D2");
		route2.setDiaInicio(new BigDecimal(1));
		route2.setDiaFin(new BigDecimal(5));
		route2.setHoraInicio(new BigDecimal(34000));
		route2.setHoraFin(new BigDecimal(62000));
		route2.setNumero("A12");
		
		
		rutasDao.save(route2);
		
		Tmio1Ruta route3 = new Tmio1Ruta();
		
		route3.setActiva("Activa");
		route3.setDescripcion("D3");
		route3.setDiaInicio(new BigDecimal(1));
		route3.setDiaFin(new BigDecimal(5));
		route3.setHoraInicio(new BigDecimal(13000));
		route3.setHoraFin(new BigDecimal(41000));
		route3.setNumero("A13");
		
		rutasDao.save(route3);
		
		delRutas.add(route1);
		delRutas.add(route2);
		delRutas.add(route3);
		
		Tmio1ServicioPK pk= new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		
		Tmio1Servicio newService= new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		
		serviceDao.save(newService);
		
		Tmio1ServicioPK pk2= new Tmio1ServicioPK();
		pk2.setCedulaConductor(newConductore2.getCedula());// Caicedo
		pk2.setFechaFin(LocalDate.of(2019, 12, 9));
		pk2.setFechaInicio(LocalDate.of(2015, 12, 9));
		pk2.setIdBus(bus2.getId());// KHA 430
		pk2.setIdRuta(route2.getId()); //  A12
		
		Tmio1Servicio newService2= new Tmio1Servicio();
		newService2.setId(pk2);
		newService2.setTmio1Bus(bus1);
		newService2.setTmio1Conductore(newConductore2);
		newService2.setTmio1Ruta(route2);
		
		serviceDao.save(newService2);
		
		Tmio1ServicioPK pk3= new Tmio1ServicioPK();
		pk3.setCedulaConductor(newConductore3.getCedula());// Julian
		pk3.setFechaFin(LocalDate.of(2019, 12, 9));
		pk3.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk3.setIdBus(bus2.getId());// KHA 430
		pk3.setIdRuta(route3.getId()); // A13
		
		Tmio1Servicio newService3= new Tmio1Servicio();
		newService3.setId(pk3);
		newService3.setTmio1Bus(bus2);
		newService3.setTmio1Conductore(newConductore3);
		newService3.setTmio1Ruta(route3);

		serviceDao.save(newService3);
		
		Tmio1ServicioPK pk4 = new Tmio1ServicioPK();
		pk4.setCedulaConductor(newConductore.getCedula());// gallo
		pk4.setFechaFin(LocalDate.of(2018, 11, 9));
		pk4.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk4.setIdBus(bus3.getId());// DBT 232
		pk4.setIdRuta(route1.getId()); // A11
		
		Tmio1Servicio newService4 = new Tmio1Servicio();
		newService4.setId(pk4);
		newService4.setTmio1Bus(bus2);
		newService4.setTmio1Conductore(newConductore);
		newService4.setTmio1Ruta(route1);
		
		serviceDao.save(newService4);

		delServicio.add(newService);
		delServicio.add(newService2);
		delServicio.add(newService3);
		delServicio.add(newService4);
		
		Object[] n = new Object[2];
		Object[] m = new Object[2];
		
		n[0] = newConductore;
		n[1] = 2;
		
		expected1.add(n);
		
		m[0] = newConductore3;
		m[1] = 1;
		
		expected0.add(newService);
		expected0.add(newService2);
		expected0.add(newService3);
		expected0.add(newService4);
		
		expected1.add(m);
		
		expected2.add(bus2);
		
		expected3.add(route1);
		expected3.add(route2);
		expected3.add(route3);
		
	}
	
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		
		busDao.save(bus1);
		
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		
		conductDao.save(newConductore);
		
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		
		rutasDao.save(route1);
		
		Tmio1ServicioPK pk= new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		
		Tmio1Servicio newService= new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		try {
			serviceDao.save(newService);		
		}
		catch (Exception e) {
			// TODO: handle exception
			fail();
		}
		
		Tmio1Servicio servicioS = serviceDao.findById(pk);
		
		assertEquals(servicioS.getTmio1Bus(), newService.getTmio1Bus());
		assertEquals(servicioS.getTmio1Ruta(), newService.getTmio1Ruta());
		assertEquals(servicioS.getTmio1Conductore(), newService.getTmio1Conductore());
		assertEquals(servicioS.getId(), newService.getId());
		assertTrue(servicioS.getId().getFechaInicio().compareTo(newService.getId().getFechaInicio()) == 0);
		assertTrue(servicioS.getId().getFechaFin().compareTo(newService.getId().getFechaFin()) == 0);
		
		serviceDao.delete(servicioS);
		rutasDao.delete(route1);
		busDao.delete(bus1);
		conductDao.delete(newConductore);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		
		busDao.save(bus1);
		
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		
		conductDao.save(newConductore);
		
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		
		rutasDao.save(route1);
		
		Tmio1ServicioPK pk= new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		
		Tmio1Servicio newService= new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		
		serviceDao.save(newService);
		
		Tmio1Servicio servicioS = null;
		try {
			servicioS = serviceDao.findById(pk);		
		}
		catch (Exception e) {
			// TODO: handle exception
			fail();
		}

		
		assertEquals(servicioS.getTmio1Bus(), newService.getTmio1Bus());
		assertEquals(servicioS.getTmio1Ruta(), newService.getTmio1Ruta());
		assertEquals(servicioS.getTmio1Conductore(), newService.getTmio1Conductore());
		assertEquals(servicioS.getId(), newService.getId());
		assertTrue(servicioS.getId().getFechaInicio().compareTo(newService.getId().getFechaInicio()) == 0);
		assertTrue(servicioS.getId().getFechaFin().compareTo(newService.getId().getFechaFin()) == 0);
	
		serviceDao.delete(servicioS);
		rutasDao.delete(route1);
		busDao.delete(bus1);
		conductDao.delete(newConductore);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(5000));
		bus1.setMarca("Mercedes-Benz");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("HBL 802");
		bus1.setTipo(BusType.A.toString());
		
		busDao.save(bus1);
		
		Tmio1Conductore newConductore = new Tmio1Conductore();
		newConductore.setApellidos("Gallo");
		newConductore.setCedula("1104820995");
		newConductore.setNombre("Juan");
		newConductore.setFechaContratacion(LocalDate.of(2018, 3, 10));
		newConductore.setFechaNacimiento(LocalDate.of(1999, 12, 9));
		
		conductDao.save(newConductore);
		
		Tmio1Ruta route1 = new Tmio1Ruta();
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		
		rutasDao.save(route1);
		
		Tmio1ServicioPK pk= new Tmio1ServicioPK();
		pk.setCedulaConductor(newConductore.getCedula());// gallo
		pk.setFechaFin(LocalDate.of(2018, 12, 9));
		pk.setFechaInicio(LocalDate.of(2017, 12, 9));
		pk.setIdBus(bus1.getId());// HBL 802
		pk.setIdRuta(route1.getId()); // A11
		
		Tmio1Servicio newService= new Tmio1Servicio();
		newService.setId(pk);
		newService.setTmio1Bus(bus1);
		newService.setTmio1Conductore(newConductore);
		newService.setTmio1Ruta(route1);
		
		serviceDao.save(newService);
		
		Tmio1Servicio servicioS = null;
		servicioS = serviceDao.findById(pk);		

		assertEquals(servicioS.getTmio1Bus(), newService.getTmio1Bus());
		assertEquals(servicioS.getTmio1Ruta(), newService.getTmio1Ruta());
		assertEquals(servicioS.getTmio1Conductore(), newService.getTmio1Conductore());
		assertEquals(servicioS.getId(), newService.getId());
		assertTrue(servicioS.getId().getFechaInicio().compareTo(newService.getId().getFechaInicio()) == 0);
		assertTrue(servicioS.getId().getFechaFin().compareTo(newService.getId().getFechaFin()) == 0);
		
		serviceDao.delete(servicioS);
		
		servicioS = serviceDao.findById(pk);
		
		assertNull(servicioS);
		
		rutasDao.delete(route1);
		busDao.delete(bus1);
		conductDao.delete(newConductore);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		contextPunto3();
		
		List<Tmio1Servicio> all = serviceDao.findAll();
		for (int i = 0; i < all.size(); i++) {
			assertTrue(expected0.contains(all.get(i)));
		}
		
		
		deleteAll();
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByServiceConductor() {
		
		assertNotNull(busDao);
		assertNotNull(conductDao);
		assertNotNull(rutasDao);
		assertNotNull(serviceDao);
		
		
		List<Object[]> list  = serviceDao.findServiceConductor(LocalDate.of(2018, 1, 1));
		
		
		Tmio1Conductore conduct = null;
		int count = 0;
		int index = 0;

		for (Object[] obj: list) {
			
		     conduct = (Tmio1Conductore) obj[0]; 
		     count = ((Number) obj[1]).intValue();
		     
		     assertEquals(conduct,expected1.get(index)[0]);
		     assertEquals(count,expected1.get(index)[1]);
		     index++;
		}
		
		deleteAll();
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSameDayService() {
		
		assertNotNull(busDao);
		assertNotNull(conductDao);
		assertNotNull(rutasDao);
		assertNotNull(serviceDao);
		
		contextPunto3();
		
		List<Tmio1Bus> list  = serviceDao.busSameDayService(LocalDate.of(2018, 1, 1));
		
		Tmio1Bus bus = null;
		int index = 0;

		for (Tmio1Bus obj: list) {		     
		     assertEquals(obj,expected2.get(index));
		     index++;
		}
		
		
		deleteAll();
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testRutaFechas() {
		
		assertNotNull(busDao);
		assertNotNull(conductDao);
		assertNotNull(rutasDao);
		assertNotNull(serviceDao);
		
		contextPunto3();
		
		List<Tmio1Ruta> list  = serviceDao.findRutaFechas(LocalDate.of(2018, 1, 1));
		
		Tmio1Bus ruta = null;
		int index = 0;

		for (Tmio1Ruta obj: list) {		     
		     assertEquals(obj,expected3.get(index));
		     index++;
		}
		
		
		deleteAll();
	}
	
	

	
	private void deleteAll() {
		
		for (Tmio1Servicio tmio1Ser : delServicio) {
			serviceDao.delete(tmio1Ser);
		}
		
		for (Tmio1Bus tmio1Bus : delBus) {
			busDao.delete(tmio1Bus);
		}
		for (Tmio1Ruta tmio1Ruta : delRutas) {
			rutasDao.delete(tmio1Ruta);
		}
		for (Tmio1Conductore tmio1Conductore : delConduct) {
			conductDao.delete(tmio1Conductore);
		}
			
	}

}
