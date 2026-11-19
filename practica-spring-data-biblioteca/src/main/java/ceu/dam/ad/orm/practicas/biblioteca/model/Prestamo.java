package ceu.dam.ad.orm.practicas.biblioteca.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@IdClass(PrestamoKey.class)
public class Prestamo {
	@Id
	private Integer anio;
	@Id
	private Integer numPrestamo;
	@ManyToOne()
	@JoinColumn(name = "dni_socio", nullable = false)
	private Socio socio;
	@ManyToOne
	@JoinColumn(name = "id_libro", nullable = false)
	private Libro libro;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;

	@Override
	public String toString() {
		return "Prestamo [anio=" + anio + ", numPrestamo=" + numPrestamo + ", libro=" + libro + ", fechaPrestamo="
				+ fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
	}

}
