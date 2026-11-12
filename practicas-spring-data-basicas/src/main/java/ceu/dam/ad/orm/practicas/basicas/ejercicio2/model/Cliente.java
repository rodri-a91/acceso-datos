package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

import java.util.Set;

<<<<<<< HEAD
public class Cliente {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private Set<Pedido> pedidos;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
	
	
	
=======
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Cliente {
	@Id
	private String dni;
	private String nombre;
	private String apellidos;
	@Transient
	private Set<Pedido> pedidos;

>>>>>>> 6f88b4af03db3f55d701bd39d441e0200dcf7747
}
