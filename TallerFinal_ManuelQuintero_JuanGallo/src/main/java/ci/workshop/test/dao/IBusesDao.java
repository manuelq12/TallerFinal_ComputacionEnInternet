package ci.workshop.test.dao;

import java.math.BigDecimal;
import java.util.List;

import ci.workshop.test.model.Tmio1Bus;

public interface IBusesDao {

	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	public Tmio1Bus findById(Integer codigo);
	public List<Tmio1Bus> findAll();
	public Tmio1Bus findByPlaca(String placa);
	public List<Tmio1Bus> findByModelo(BigDecimal modelo);
	public List<Tmio1Bus> findByMarca(String marca);
}
