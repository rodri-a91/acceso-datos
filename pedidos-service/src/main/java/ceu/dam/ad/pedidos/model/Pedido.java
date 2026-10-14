package ceu.dam.ad.pedidos.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Pedido {

	@Id
	@Column(name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idPedido;
	@Column(name = "fecha")
	private LocalDate fechaPedido;
	@Column(name = "cliente")
	private String dniCliente;
	private BigDecimal total;
	private BigDecimal subtotal;
	private BigDecimal impuestos;
	@Transient
	private List<ArticuloPedido> articulos;
	

}
