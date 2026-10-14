package ceu.dam.ad.pedidos.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido_articulos")
public class ArticuloPedido {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_pedido")
	private Long idPedido;
	@Column(name = "codigo_barras")
	private String codBarras;
	@Column(name = "descripcion")
	private String descripcionArticulo;
	@Column(name = "precio")
	private BigDecimal precioSinIva;
	@Column(name = "exento")
	private Boolean exentoIva; 

}
