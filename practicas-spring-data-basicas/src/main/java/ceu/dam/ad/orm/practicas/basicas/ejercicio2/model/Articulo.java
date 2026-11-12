package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

public class Articulo {
	
	private Long id;
	private String descripcion;
	private String codBarras;
	
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
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", descripcion=" + descripcion + ", codBarras=" + codBarras + "]";
	}
	
	
	
}
