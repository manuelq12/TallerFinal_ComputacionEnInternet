package ci.workshop.test.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
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
import ci.workshop.test.model.BusType;
import ci.workshop.test.model.Tmio1Bus;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TallerFinalManuelQuinteroJuanGalloApplication.class)
@Rollback(false)
public class TestBus {

	@Autowired
	private IBusesDao busDao;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(busDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(150));
		bus.setMarca("Mercedes-Benz");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("KHA 430");
		bus.setTipo(BusType.A.toString());
		
		
		
		busDao.save(bus);
		
		Tmio1Bus busS = busDao.findByPlaca(bus.getPlaca());
		int id = busS.getId();
		busS = busDao.findById(id);
		assertEquals(bus.getCapacidad(), busS.getCapacidad());
		assertEquals(bus.getMarca(), busS.getMarca());
		assertEquals(bus.getModelo(), busS.getModelo());
		assertEquals(bus.getPlaca(),busS.getPlaca());
		assertEquals(bus.getTipo(), busS.getTipo());
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		assertNotNull(busDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(150));
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("TTN 445");
		bus.setTipo(BusType.A.toString());
		
		busDao.save(bus);
		
		Tmio1Bus busS = busDao.findByPlaca(bus.getPlaca());
		int id = busS.getId();
		busS = busDao.findById(id);
		
		assertEquals(bus.getCapacidad(), busS.getCapacidad());
		assertEquals(bus.getMarca(), busS.getMarca());
		assertEquals(bus.getModelo(), busS.getModelo());
		assertEquals(bus.getPlaca(),busS.getPlaca());
		assertEquals(bus.getTipo(), busS.getTipo());
		
		bus.setCapacidad(new BigDecimal(1200));
		bus.setMarca("Mercedes-Benz");
		bus.setModelo(new BigDecimal(2020));
		bus.setPlaca("DBT 232");
		bus.setTipo(BusType.P.toString());
		
		busDao.update(bus);
		busS = busDao.findById(id);
		assertEquals(bus.getCapacidad(), busS.getCapacidad());
		assertEquals(bus.getMarca(), busS.getMarca());
		assertEquals(bus.getModelo(), busS.getModelo());
		assertEquals(bus.getPlaca(),busS.getPlaca());
		assertEquals(bus.getTipo(), busS.getTipo());
		
		busDao.delete(bus);
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		assertNotNull(busDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(500));
		bus.setMarca("Folken");
		bus.setModelo(new BigDecimal(2017));
		bus.setPlaca("CSN 439");
		bus.setTipo(BusType.T.toString());
		
		busDao.save(bus);
		
		Tmio1Bus busS = busDao.findByPlaca(bus.getPlaca());
		int id = busS.getId();
		busS = busDao.findById(id);

		assertNotNull(busS);
		
		busDao.delete(busS);
		
		busS = busDao.findById(id);
		assertNull(busS);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		assertNotNull(busDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(500));
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(2013));
		bus.setPlaca("HBL 802");
		bus.setTipo(BusType.A.toString());
		
		busDao.save(bus);
		
		Tmio1Bus busS = busDao.findByPlaca(bus.getPlaca());
		int id = busS.getId();
		busS = busDao.findById(id);
		
		assertEquals(bus.getCapacidad(), busS.getCapacidad());
		assertEquals(bus.getMarca(), busS.getMarca());
		assertEquals(bus.getModelo(), busS.getModelo());
		assertEquals(bus.getPlaca(),busS.getPlaca());
		assertEquals(bus.getTipo(), busS.getTipo());
		
		busDao.delete(bus);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByPlaca() {
		assertNotNull(busDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(500));
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(2013));
		bus.setPlaca("HBL 802");
		bus.setTipo(BusType.A.toString());
		
		busDao.save(bus);
		
		Tmio1Bus busS = busDao.findByPlaca("HBL 802");
		
		assertEquals(bus.getCapacidad(), busS.getCapacidad());
		assertEquals(bus.getMarca(), busS.getMarca());
		assertEquals(bus.getModelo(), busS.getModelo());
		assertEquals(bus.getPlaca(),busS.getPlaca());
		assertEquals(bus.getTipo(), busS.getTipo());
		
		busDao.delete(bus);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByMarca() {
		assertNotNull(busDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(500));
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(2013));
		bus.setPlaca("HBL 802");
		bus.setTipo(BusType.A.toString());
		
		busDao.save(bus);
		
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(500));
		bus1.setMarca("Volkswagen");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("KHA 430");
		bus1.setTipo(BusType.T.toString());
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(500));
		bus2.setMarca("Mercedes-Benz");
		bus2.setModelo(new BigDecimal(2013));
		bus2.setPlaca("DBT 232");
		bus2.setTipo(BusType.P.toString());
		
		busDao.save(bus);
		busDao.save(bus1);
		busDao.save(bus2);
		

		List<Tmio1Bus> busS = busDao.findByMarca("Volkswagen");
		int count = 0;
		for (Tmio1Bus tmio1Bus : busS) {
			if(count == 0) {
				assertEquals(bus.getCapacidad(), tmio1Bus.getCapacidad());
				assertEquals(bus.getMarca(), tmio1Bus.getMarca());
				assertEquals(bus.getModelo(), tmio1Bus.getModelo());
				assertEquals(bus.getPlaca(),tmio1Bus.getPlaca());
				assertEquals(bus.getTipo(), tmio1Bus.getTipo());
				count++;
			}
			else if(count == 1) {
				assertEquals(bus1.getCapacidad(), tmio1Bus.getCapacidad());
				assertEquals(bus1.getMarca(), tmio1Bus.getMarca());
				assertEquals(bus1.getModelo(), tmio1Bus.getModelo());
				assertEquals(bus1.getPlaca(),tmio1Bus.getPlaca());
				assertEquals(bus1.getTipo(), tmio1Bus.getTipo());
			}
			else {
				fail();
			}
		}
		
		busDao.delete(bus);
		busDao.delete(bus1);
		busDao.delete(bus2);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByModelo() {
		assertNotNull(busDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(500));
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("HBL 802");
		bus.setTipo(BusType.A.toString());
		
		busDao.save(bus);
		
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(500));
		bus1.setMarca("Volkswagen");
		bus1.setModelo(new BigDecimal(2013));
		bus1.setPlaca("KHA 430");
		bus1.setTipo(BusType.T.toString());
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(500));
		bus2.setMarca("Mercedes-Benz");
		bus2.setModelo(new BigDecimal(2013));
		bus2.setPlaca("DBT 232");
		bus2.setTipo(BusType.P.toString());
		
		busDao.save(bus);
		busDao.save(bus1);
		busDao.save(bus2);
		
		List<Tmio1Bus> busS = busDao.findByModelo(new BigDecimal(2013));
		int count = 0;
		for (Tmio1Bus tmio1Bus : busS) {
			if(count == 0) {
				assertEquals(bus1.getCapacidad(), tmio1Bus.getCapacidad());
				assertEquals(bus1.getMarca(), tmio1Bus.getMarca());
				assertEquals(bus1.getModelo(), tmio1Bus.getModelo());
				assertEquals(bus1.getPlaca(),tmio1Bus.getPlaca());
				assertEquals(bus1.getTipo(), tmio1Bus.getTipo());
				count++;
			}
			else if(count == 1) {
				assertEquals(bus2.getCapacidad(), tmio1Bus.getCapacidad());
				assertEquals(bus2.getMarca(), tmio1Bus.getMarca());
				assertEquals(bus2.getModelo(), tmio1Bus.getModelo());
				assertEquals(bus2.getPlaca(),tmio1Bus.getPlaca());
				assertEquals(bus2.getTipo(), tmio1Bus.getTipo());
				count++;
			}
			else {
				fail();
			}
		}
		
		busDao.delete(bus);
		busDao.delete(bus1);
		busDao.delete(bus2);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
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
		
		ArrayList<Tmio1Bus> expected = new ArrayList<Tmio1Bus>();
		expected.add(bus1);
		expected.add(bus2);
		expected.add(bus3);
		expected.add(bus4);
		
		List<Tmio1Bus> all = busDao.findAll();
		for (int i = 0; i < all.size(); i++) {
			assertTrue(expected.contains(all.get(i)));
		}
		
		busDao.delete(bus1);
		busDao.delete(bus2);
		busDao.delete(bus3);
		busDao.delete(bus4);
	}
}
