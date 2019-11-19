package ci.workshop.test.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import ci.workshop.test.dao.IRutasDao;
import ci.workshop.test.model.RouteStateType;
import ci.workshop.test.model.Tmio1Ruta;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TallerFinalManuelQuinteroJuanGalloApplication.class)
@Rollback(false)
public class TestRuta {

	@Autowired
	private IRutasDao rutaDao;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(rutaDao);
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");
		
		
		rutaDao.save(ruta);
		
		List<Tmio1Ruta> rutaL = rutaDao.findByDias(ruta.getDiaInicio(), ruta.getDiaFin());
		Tmio1Ruta rutaS = rutaL.get(0);
		int id = rutaS.getId();	
		rutaS = rutaDao.findById(id);
		assertEquals(ruta.getActiva(), rutaS.getActiva());
		assertEquals(ruta.getDescripcion(), rutaS.getDescripcion());
		assertEquals(ruta.getDiaInicio(), rutaS.getDiaInicio());
		assertEquals(ruta.getDiaFin(),rutaS.getDiaFin());
		assertEquals(ruta.getHoraInicio(), rutaS.getHoraInicio());
		assertEquals(ruta.getHoraFin(), rutaS.getHoraFin());
		assertEquals(ruta.getNumero(), rutaS.getNumero());
		
		rutaDao.delete(ruta);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		assertNotNull(rutaDao);
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Inactiva.toString());
		ruta.setDescripcion("Descripcion 2 ");
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setDiaFin(new BigDecimal(4));
		ruta.setHoraInicio(new BigDecimal(18000));
		ruta.setHoraFin(new BigDecimal(55000));
		ruta.setNumero("B14");
		
		
		rutaDao.save(ruta);
		
		List<Tmio1Ruta> rutaL = rutaDao.findByDias(ruta.getDiaInicio(), ruta.getDiaFin());
		Tmio1Ruta rutaS = rutaL.get(0);
		int id = rutaS.getId();	
		rutaS = rutaDao.findById(id);
		assertEquals(ruta.getActiva(), rutaS.getActiva());
		assertEquals(ruta.getDescripcion(), rutaS.getDescripcion());
		assertEquals(ruta.getDiaInicio(), rutaS.getDiaInicio());
		assertEquals(ruta.getDiaFin(),rutaS.getDiaFin());
		assertEquals(ruta.getHoraInicio(), rutaS.getHoraInicio());
		assertEquals(ruta.getHoraFin(), rutaS.getHoraFin());
		assertEquals(ruta.getNumero(), rutaS.getNumero());
		
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 2.1 ");
		ruta.setDiaInicio(new BigDecimal(3));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(40000));
		ruta.setHoraFin(new BigDecimal(70000));
		ruta.setNumero("A14");
		
		rutaDao.update(ruta);
		rutaS = rutaDao.findById(id);
		assertEquals(ruta.getActiva(), rutaS.getActiva());
		assertEquals(ruta.getDescripcion(), rutaS.getDescripcion());
		assertEquals(ruta.getDiaInicio(), rutaS.getDiaInicio());
		assertEquals(ruta.getDiaFin(),rutaS.getDiaFin());
		assertEquals(ruta.getHoraInicio(), rutaS.getHoraInicio());
		assertEquals(ruta.getHoraFin(), rutaS.getHoraFin());
		assertEquals(ruta.getNumero(), rutaS.getNumero());
		
		rutaDao.delete(ruta);
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		assertNotNull(rutaDao);
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Inactiva.toString());
		ruta.setDescripcion("Descripcion 2 ");
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setDiaFin(new BigDecimal(4));
		ruta.setHoraInicio(new BigDecimal(18000));
		ruta.setHoraFin(new BigDecimal(55000));
		ruta.setNumero("B14");
		
		
		rutaDao.save(ruta);
		
		List<Tmio1Ruta> rutaL = rutaDao.findByDias(ruta.getDiaInicio(), ruta.getDiaFin());
		Tmio1Ruta rutaS = rutaL.get(0);
		int id = rutaS.getId();	
		rutaS = rutaDao.findById(id);

		assertNotNull(rutaS);
		
		rutaDao.delete(rutaS);
		
		rutaS = rutaDao.findById(id);
		assertNull(rutaS);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		assertNotNull(rutaDao);
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(28000));
		ruta.setHoraFin(new BigDecimal(45000));
		ruta.setNumero("A11");
		
		
		rutaDao.save(ruta);
		
		List<Tmio1Ruta> rutaL = rutaDao.findByDias(ruta.getDiaInicio(), ruta.getDiaFin());
		Tmio1Ruta rutaS = rutaL.get(0);
		int id = rutaS.getId();	
		rutaS = rutaDao.findById(id);
		assertEquals(ruta.getActiva(), rutaS.getActiva());
		assertEquals(ruta.getDescripcion(), rutaS.getDescripcion());
		assertEquals(ruta.getDiaInicio(), rutaS.getDiaInicio());
		assertEquals(ruta.getDiaFin(),rutaS.getDiaFin());
		assertEquals(ruta.getHoraInicio(), rutaS.getHoraInicio());
		assertEquals(ruta.getHoraFin(), rutaS.getHoraFin());
		assertEquals(ruta.getNumero(), rutaS.getNumero());
		
		rutaDao.delete(ruta);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDias() {
		assertNotNull(rutaDao);
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(5));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(30000));
		ruta.setHoraFin(new BigDecimal(50000));
		ruta.setNumero("A11");
		
		
		rutaDao.save(ruta);
		
		
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setActiva(RouteStateType.Activa.toString());
		ruta1.setDescripcion("Descripcion 2 ");
		ruta1.setDiaInicio(new BigDecimal(5));
		ruta1.setDiaFin(new BigDecimal(7));
		ruta1.setHoraInicio(new BigDecimal(18000));
		ruta1.setHoraFin(new BigDecimal(45000));
		ruta1.setNumero("A12");
		
		
		rutaDao.save(ruta1);
		
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva(RouteStateType.Inactiva.toString());
		ruta2.setDescripcion("Descripcion 3 ");
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setDiaFin(new BigDecimal(7));
		ruta2.setHoraInicio(new BigDecimal(60000));
		ruta2.setHoraFin(new BigDecimal(70000));
		ruta2.setNumero("A13");
		
		
		rutaDao.save(ruta2);
		
		List<Tmio1Ruta> rutaL = rutaDao.findByDias(new BigDecimal(5), new BigDecimal(7));
		System.out.println(rutaL.size());
		assertTrue(rutaL.size() == 2);
		for (Tmio1Ruta rt : rutaL) {
			if(rt.equals(ruta)) assertTrue(true);
			else if(rt.equals(ruta1)) assertTrue(true);	
		}
		
		
		rutaDao.delete(ruta);
		rutaDao.delete(ruta1);
		rutaDao.delete(ruta2);
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByHoras() {
		assertNotNull(rutaDao);
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva(RouteStateType.Activa.toString());
		ruta.setDescripcion("Descripcion 1 ");
		ruta.setDiaInicio(new BigDecimal(5));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(30000));
		ruta.setHoraFin(new BigDecimal(50000));
		ruta.setNumero("A11");
		
		
		rutaDao.save(ruta);
		
		
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setActiva(RouteStateType.Activa.toString());
		ruta1.setDescripcion("Descripcion 2 ");
		ruta1.setDiaInicio(new BigDecimal(5));
		ruta1.setDiaFin(new BigDecimal(7));
		ruta1.setHoraInicio(new BigDecimal(18000));
		ruta1.setHoraFin(new BigDecimal(45000));
		ruta1.setNumero("A12");
		
		
		rutaDao.save(ruta1);
		
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setActiva(RouteStateType.Inactiva.toString());
		ruta2.setDescripcion("Descripcion 3 ");
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setDiaFin(new BigDecimal(7));
		ruta2.setHoraInicio(new BigDecimal(60000));
		ruta2.setHoraFin(new BigDecimal(70000));
		ruta2.setNumero("A13");
		
		
		rutaDao.save(ruta2);
		
		List<Tmio1Ruta> rutaL = rutaDao.findByHoras(new BigDecimal(30000), new BigDecimal(70000));
		assertTrue(rutaL.size() == 2);
		for (Tmio1Ruta rt : rutaL) {
			if(rt.equals(ruta)) assertTrue(true);
			else if(rt.equals(ruta2)) assertTrue(true);	
		}
		
		
		rutaDao.delete(ruta);
		rutaDao.delete(ruta1);
		rutaDao.delete(ruta2);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {

		ArrayList<Tmio1Ruta> expected = new ArrayList<Tmio1Ruta>();
		
Tmio1Ruta route1 = new Tmio1Ruta();
		
		route1.setActiva("Activa");
		route1.setDescripcion("D1");
		route1.setDiaInicio(new BigDecimal(1));
		route1.setDiaFin(new BigDecimal(5));
		route1.setHoraInicio(new BigDecimal(24000));
		route1.setHoraFin(new BigDecimal(72000));
		route1.setNumero("A11");
		
		rutaDao.save(route1);
		
		Tmio1Ruta route2 = new Tmio1Ruta();
		
		route2.setActiva("Activa");
		route2.setDescripcion("D2");
		route2.setDiaInicio(new BigDecimal(1));
		route2.setDiaFin(new BigDecimal(5));
		route2.setHoraInicio(new BigDecimal(34000));
		route2.setHoraFin(new BigDecimal(62000));
		route2.setNumero("A12");
		
		
		rutaDao.save(route2);
		
		Tmio1Ruta route3 = new Tmio1Ruta();
		
		route3.setActiva("Activa");
		route3.setDescripcion("D3");
		route3.setDiaInicio(new BigDecimal(1));
		route3.setDiaFin(new BigDecimal(5));
		route3.setHoraInicio(new BigDecimal(13000));
		route3.setHoraFin(new BigDecimal(41000));
		route3.setNumero("A13");
		
		rutaDao.save(route3);
		
		expected.add(route1);
		expected.add(route2);
		expected.add(route3);
		
		List<Tmio1Ruta> all = rutaDao.findAll();
		for (int i = 0; i < all.size(); i++) {
			
			assertTrue(expected.contains(all.get(i)));
		}
	
		rutaDao.delete(route1);
		rutaDao.delete(route2);
		rutaDao.delete(route3);
		
	}
	

}
