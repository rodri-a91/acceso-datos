package ceu.dam.ad.orm.practicas.basicas.ejercicio1.model;

import java.util.List;

public class Temporada {

	private Long id;
	private Integer numero;
	private List<Episodio> episodios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		this.episodios = episodios;
	}

	@Override
	public String toString() {
		return "Temporada [id=" + id + ", numero=" + numero + ", episodios=" + episodios + "]";
	}
}
