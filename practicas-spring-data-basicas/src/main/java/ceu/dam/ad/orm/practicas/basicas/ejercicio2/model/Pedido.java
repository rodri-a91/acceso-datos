package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

<<<<<<< HEAD
public class Pedido {
	
	private UUID uidPedido;
	private Cliente cliente;
	private Date fecha;
	private List<PedidoLinea> lineas;
	

	public UUID getUidPedido() {
		return uidPedido;
	}
	public void setUidPedido(UUID uidPedido) {
		this.uidPedido = uidPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<PedidoLinea> getLineas() {
		return lineas;
	}
	public void setLineas(List<PedidoLinea> lineas) {
		this.lineas = lineas;
	}
	@Override
	public String toString() {
		return "Pedido [uidPedido=" + uidPedido + ", fecha=" + fecha + ", lineas=" + lineas + "]";
	}
=======
import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Pedido {

	@Id
	@GeneratedValue
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID uidPedido;
	private Cliente cliente;
	private Date fecha;
	@Transient
	private List<PedidoLinea> lineas;

>>>>>>> 6f88b4af03db3f55d701bd39d441e0200dcf7747
	@Override
	public int hashCode() {
		return Objects.hash(uidPedido);
	}
<<<<<<< HEAD
=======

>>>>>>> 6f88b4af03db3f55d701bd39d441e0200dcf7747
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(uidPedido, other.uidPedido);
	}
<<<<<<< HEAD
	
	
	
=======

>>>>>>> 6f88b4af03db3f55d701bd39d441e0200dcf7747
}
