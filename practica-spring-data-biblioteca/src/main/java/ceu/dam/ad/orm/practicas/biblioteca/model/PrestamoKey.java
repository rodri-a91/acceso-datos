package ceu.dam.ad.orm.practicas.biblioteca.model;

import java.io.Serializable;

import lombok.Data;

/** Clase de clave primaria compuesta de Prestamo (para usar con @IdClass). */
@Data
public class PrestamoKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer anio;
	private Integer numPrestamo;

	@Override
	public String toString() {
		return "PrestamoId [anio=" + anio + ", numPrestamo=" + numPrestamo + "]";
	}

}
