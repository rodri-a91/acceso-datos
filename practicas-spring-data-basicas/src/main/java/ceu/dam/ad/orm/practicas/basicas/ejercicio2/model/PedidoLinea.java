package ceu.dam.ad.orm.practicas.basicas.ejercicio2.model;

import java.util.UUID;

public class PedidoLinea {
	private UUID uidLinea;
	private Integer numLinea;
	private Integer cantidad;
	private Articulo articulo;

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

	

	
}
