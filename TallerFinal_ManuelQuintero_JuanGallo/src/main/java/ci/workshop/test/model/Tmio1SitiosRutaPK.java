package ci.workshop.test.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tmio1_sitios_rutas database table.
 * 
 */
@Embeddable
public class Tmio1SitiosRutaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_sitio", insertable=false, updatable=false)
	private Integer idSitio;

	@Column(name="id_ruta", insertable=false, updatable=false)
	private Integer idRuta;

	private int hashId;
	
	public Tmio1SitiosRutaPK() {
	}
	public Integer getIdSitio() {
		return this.idSitio;
	}
	public void setIdSitio(Integer idSitio) {
		this.idSitio = idSitio;
	}
	public Integer getIdRuta() {
		return this.idRuta;
	}
	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tmio1SitiosRutaPK)) {
			return false;
		}
		Tmio1SitiosRutaPK castOther = (Tmio1SitiosRutaPK)other;
		return 
			this.idSitio.equals(castOther.idSitio)
			&& this.idRuta.equals(castOther.idRuta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSitio.hashCode();
		hash = hash * prime + this.idRuta.hashCode();
		hashId= hash;
		return hash;
	}
	public int getHashId() {
		return hashId;
	}
	public void setHashId(int hashId) {
		this.hashId = hashId;
	}
}