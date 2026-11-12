package ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo;

import java.util.List;
import java.util.UUID;

public class CentroComercial {

	private UUID id;
	
	private String nombre;
	
	private String direccion;
	
	private Pais pais;
	
	private List<Tienda> tiendas;
	
	private List<Marca> marcas;

	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public List<Tienda> getTiendas() {
		return tiendas;
	}
	public void setTiendas(List<Tienda> tiendas) {
		this.tiendas = tiendas;
	}
	public List<Marca> getMarcas() {
		return marcas;
	}
	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
	@Override
	public String toString() {
		String x = "[id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", pais=" + pais + "]";
		x+= "\n\tMarcas:";
		for (Marca marca : marcas) {
			x+= "\n\t\t" + marca;
		}
		x+= "\n\tTiendas:";
		for (Tienda tienda : tiendas) {
			x+= "\n\t\t" + tienda;
		}
		return x;
	}
	
	
	
	 
	

}
