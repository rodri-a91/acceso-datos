package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "pedido_lineas")
@Entity
public class PedidoLinea {
	@Id
	@Column(name = "uuid_linea_pedido")
	@GeneratedValue
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID uidLinea;
	private Integer numLinea;
	private Integer cantidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_articulo", nullable = false)
	private Articulo articulo;
}