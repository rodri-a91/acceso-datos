package ceu.dam.ad.orm.practicas.biblioteca.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CarnetResponseRenovarDto {

	private UUID id;
	private LocalDate fechaAlta;
	private LocalDate fechaCaducidad;

}
