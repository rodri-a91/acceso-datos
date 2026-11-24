package ceu.dam.ad.orm.practicas.biblioteca.dto.response;

import java.util.UUID;

import lombok.Data;

@Data
public class SocioResponseAltaDto {
	private String dni;
	private String nombre;
	private String apellidos;
	private UUID id;

}
