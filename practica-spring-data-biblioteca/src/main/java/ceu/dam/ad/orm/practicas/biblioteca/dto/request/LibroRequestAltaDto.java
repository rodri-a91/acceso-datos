package ceu.dam.ad.orm.practicas.biblioteca.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibroRequestAltaDto {

	@Size(min = 3, max = 3)
	private String isbn;
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String autor;
	private String genero;
	@Min(1)
	private Integer ejemplaresDisponibles;

}
