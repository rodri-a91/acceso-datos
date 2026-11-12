package ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo;

public class Tienda {

	private Long id;
	
	private String ubicacion;
	
	private Marca marca;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	@Override
	public String toString() {
		return "Tienda [id=" + id + ", ubicacion=" + ubicacion + "]";
	}
	
	

}
