package ceu.dam.ad.orm.practicas.biblioteca.dto.response;

import java.time.LocalDate;

import ceu.dam.ad.orm.practicas.biblioteca.model.Libro;
import lombok.Data;

@Data
public class PrestamoResponseActivosDto {
	
	private Integer anio;
	private Integer numPrestamo;
	private Libro libro;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;

}
