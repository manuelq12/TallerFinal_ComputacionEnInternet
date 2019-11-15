package ci.workshop.test.dao;

import java.math.BigDecimal;
import java.util.List;

import ci.workshop.test.model.Tmio1Ruta;

public interface IRutasDao{
	public void save(Tmio1Ruta entity);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	public Tmio1Ruta findById(Integer codigo);
	public List<Tmio1Ruta> findAll();
	List<Tmio1Ruta> findByDias(BigDecimal inicio, BigDecimal fin);
	List<Tmio1Ruta> findByHoras(BigDecimal inicio, BigDecimal fin);
}
