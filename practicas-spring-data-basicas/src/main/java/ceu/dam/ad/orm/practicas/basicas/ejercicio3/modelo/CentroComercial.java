package ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class CentroComercial {
	@Id
	@GeneratedValue
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@Column(name = "uuid_centro")
	private UUID id;

	@Column(name = "nombre_comercial")
	private String nombre;

	private String direccion;

	@JoinColumn(name = "cod_pais")
	@ManyToOne
	private Pais pais;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "uuid_centro", nullable = false)
	private List<Tienda> tiendas;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "centro_comercial_marcas", joinColumns = {
			@JoinColumn(name = "cod_marca") }, inverseJoinColumns = { @JoinColumn(name = "uuid_centro") })
	private List<Marca> marcas;

	@Override
	public String toString() {
		String x = "[id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", pais=" + pais + "]";
		x += "\n\tMarcas:";
		for (Marca marca : marcas) {
			x += "\n\t\t" + marca;
		}
		x += "\n\tTiendas:";
		for (Tienda tienda : tiendas) {
			x += "\n\t\t" + tienda;
		}
		return x;
	}

}
