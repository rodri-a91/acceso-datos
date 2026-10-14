package ceu.dam.ad.pedidos.service;

import java.util.List;

import ceu.dam.ad.pedidos.model.Pedido;

public interface PedidoService {

	/** Recibe el id de un pedido y lo consulta en BBDD. Debe devolver el pedido con todos sus datos
	 * incluyendo sus artículos y sus totales. Lanza PedidoNotFoundException si el pedido no existe
	 * o PedidoException si hay un error.
	 * @param idPedido
	 * @return
	 * @throws PedidoException
	 * @throws PedidoNotFoundException
	 */
	public Pedido consultarPedido(Long idPedido) throws PedidoException, PedidoNotFoundException;

	
	
	/** Recibe un pedido con su lista de artículos (sin los totales calculados) y debe guardarlo en BBDD. Tendrá que devolver
	 * el pedido con todos sus datos. Si hay algún error lanza PedidoException
	 * Debe calcular sus totales
	 * @param pedido
	 * @return
	 * @throws PedidoException
	 */
	public Pedido crearPedido(Pedido pedido) throws PedidoException;
	
	
	
	/** Recibe un código de barras. Tendrá que devolver la lista de IDs de pedidos que incluyan
	 * algún artículo con ese código de barras. Si no hay ninguno, lanzará PedidoNotFoundException.
	 * Si hay algún error, lanzará PedidoException.
	 * @param codBarras
	 * @return
	 * @throws PedidoException
	 * @throws PedidoNotFoundException
	 */
	public List<Long> buscarPedidosArticulo(String codBarras) throws PedidoException, PedidoNotFoundException;
	
	
}
