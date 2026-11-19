package ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Tienda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String ubicacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_marca", nullable = false)
	private Marca marca;

}
