package ceu.dam.ad.orm.practicas.biblioteca.dto.response;

import ceu.dam.ad.orm.practicas.biblioteca.model.Carnet;
import lombok.Data;

@Data
public class SocioResponseConsultaDto {
	private String dni;
	private String nombre;
	private String apellidos;
	private Carnet carnet;

}
