package ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Tienda {
	@Id
	private Long id;

	private String ubicacion;

	@ManyToOne(fetch = FetchType.LAZY)
	private Marca marca;

}
