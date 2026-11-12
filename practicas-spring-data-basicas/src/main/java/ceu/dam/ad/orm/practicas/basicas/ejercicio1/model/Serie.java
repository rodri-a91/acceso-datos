package ceu.dam.ad.orm.practicas.basicas.ejercicio1.model;

import java.util.Date;
import java.util.List;

public class Serie {

	private Long id;
	private String descripcion;
	private Date estreno;
	private Trailer trailer;
	private List<Temporada> temporadas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getEstreno() {
		return estreno;
	}

	public void setEstreno(Date estreno) {
		this.estreno = estreno;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<Temporada> temporadas) {
		this.temporadas = temporadas;
	}
	
	@Override
	public String toString() {
		return "Serie [id=" + id + ", descripcion=" + descripcion + ", estreno=" + estreno + ", trailer=" + trailer
				+ ", temporadas=" + temporadas + "]";
	}

}
