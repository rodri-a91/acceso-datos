package ceu.dam.ad.orm.practicas.basicas.ejercicio1.model;

public class Trailer {

	private Long id;
	private Integer duracion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Trailer [id=" + id + ", duracion=" + duracion + "]";
	}

	
	
}
