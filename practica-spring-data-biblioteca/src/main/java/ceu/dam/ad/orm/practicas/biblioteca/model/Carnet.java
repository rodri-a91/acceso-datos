package ceu.dam.ad.orm.practicas.biblioteca.model;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Carnet {
	@Id
	@Column(name = "uuid_carnet")
	@GeneratedValue
	@JdbcTypeCode(Types.VARCHAR)
	private UUID id;
	@OneToOne
	@JoinColumn(name = "dni_socio", nullable = false)
	private Socio socio;
	private LocalDate fechaAlta;
	private LocalDate fechaCaducidad;

	@Override
	public String toString() {
		return "Carnet [id=" + id + ", fechaAlta=" + fechaAlta + ", fechaCaducidad=" + fechaCaducidad + "]";
	}

}
