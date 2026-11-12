package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	private Articulo articulo;

}
