package ci.workshop.test.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the tmio1_servicios database table.
 * 
 */
@Entity
@Table(name="tmio1_servicios")
@NamedQuery(name="Tmio1Servicio.findAll", query="SELECT t FROM Tmio1Servicio t")
public class Tmio1Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tmio1ServicioPK id;
	
	private String hash;

	//bi-directional many-to-one association to Tmio1Bus
	@ManyToOne
	@JoinColumn(name="id_bus", insertable=false, updatable=false)
	@JsonIgnoreProperties("services")
	private Tmio1Bus tmio1Bus;

	//bi-directional many-to-one association to Tmio1Conductore
	@ManyToOne
	@JoinColumn(name="cedula_conductor", insertable=false, updatable=false)
	@JsonIgnoreProperties("services")
	private Tmio1Conductore tmio1Conductore;

	//bi-directional many-to-one association to Tmio1Ruta
	@ManyToOne
	@JoinColumn(name="id_ruta", insertable=false, updatable=false)
	@JsonIgnoreProperties("services")
	private Tmio1Ruta tmio1Ruta;


	public Tmio1Servicio() {
	}

	public Tmio1ServicioPK getId() {
		return this.id;
	}

	public void setId(Tmio1ServicioPK id) {
		this.id = id;
	}
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Tmio1Bus getTmio1Bus() {
		return this.tmio1Bus;
	}

	public void setTmio1Bus(Tmio1Bus tmio1Bus) {
		this.tmio1Bus = tmio1Bus;
	}

	public Tmio1Conductore getTmio1Conductore() {
		return this.tmio1Conductore;
	}

	public void setTmio1Conductore(Tmio1Conductore tmio1Conductore) {
		this.tmio1Conductore = tmio1Conductore;
	}

	public Tmio1Ruta getTmio1Ruta() {
		return this.tmio1Ruta;
	}

	public void setTmio1Ruta(Tmio1Ruta tmio1Ruta) {
		this.tmio1Ruta = tmio1Ruta;
	}
	
	
}