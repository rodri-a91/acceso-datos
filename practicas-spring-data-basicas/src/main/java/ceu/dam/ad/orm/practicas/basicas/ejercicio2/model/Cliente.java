package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

import java.util.Set;

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

}
