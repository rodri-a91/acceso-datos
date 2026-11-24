package ceu.dam.ad.orm.practicas.biblioteca.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SocioRequestDto {
	@NotEmpty
	private String dni;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellidos;

}
