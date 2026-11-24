package ceu.dam.ad.orm.practicas.biblioteca.dto.response;

import lombok.Data;

@Data
public class LibroResponseAltaDto {

	private Long id;
	private String isbn;
	private String titulo;
	private String autor;
	private String genero;
	private Integer ejemplaresDisponibles;
}
