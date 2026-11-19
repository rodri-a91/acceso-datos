package ceu.dam.ad.orm.practicas.biblioteca.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Socio {

	@Id
	private String dni;
	private String nombre;
	private String apellidos;
	@OneToOne(mappedBy = "socio")
	private Carnet carnet;
	@OneToMany(mappedBy = "socio", fetch = FetchType.EAGER)
	private List<Prestamo> prestamos;

	@Override
	public String toString() {
		return "Socio [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", carnet=" + carnet
				+ ", prestamos=" + prestamos + "]";
	}

}
