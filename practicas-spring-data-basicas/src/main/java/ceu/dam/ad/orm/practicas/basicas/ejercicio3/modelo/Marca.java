package ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "marcas")
public class Marca {
	@Id
	private String codigo;

	private String nombreComercial;

	@ManyToOne()
	@JoinColumn(name = "cod_pais", nullable = false)
	private Pais pais;

	@ManyToMany
	@JoinTable(name = "centro_comercial_marcas", joinColumns = {
			@JoinColumn(name = "uuid_centro") }, inverseJoinColumns = { @JoinColumn(name = "cod_marca") })
	private List<CentroComercial> centrosComerciales;

}
