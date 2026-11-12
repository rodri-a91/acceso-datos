package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

import java.util.UUID;

<<<<<<< HEAD
public class PedidoLinea {
=======
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
>>>>>>> 6f88b4af03db3f55d701bd39d441e0200dcf7747
	private UUID uidLinea;
	private Integer numLinea;
	private Integer cantidad;
	private Articulo articulo;

<<<<<<< HEAD
	public UUID getUidLinea() {
		return uidLinea;
	}
	public void setUidLinea(UUID uidLinea) {
		this.uidLinea = uidLinea;
	}
	public Integer getNumLinea() {
		return numLinea;
	}
	public void setNumLinea(Integer numLinea) {
		this.numLinea = numLinea;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	@Override
	public String toString() {
		return "PedidoLinea [uidLinea=" + uidLinea + ", numLinea=" + numLinea + ", cantidad=" + cantidad + ", articulo="
				+ articulo + "]";
	}

	

	
=======
>>>>>>> 6f88b4af03db3f55d701bd39d441e0200dcf7747
}
