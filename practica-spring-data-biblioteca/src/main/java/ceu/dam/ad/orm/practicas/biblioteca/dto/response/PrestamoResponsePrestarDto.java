package ceu.dam.ad.orm.practicas.biblioteca.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PrestamoResponsePrestarDto {

	private Integer anio;
	private Integer numPrestamo;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;

}
