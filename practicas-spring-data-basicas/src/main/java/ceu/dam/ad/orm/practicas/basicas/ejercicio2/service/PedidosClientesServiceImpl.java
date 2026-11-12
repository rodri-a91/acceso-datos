package ceu.dam.ad.orm.practicas.basicas.ejercicio2.service;

import java.util.List;

import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Articulo;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Cliente;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Pedido;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.repository.ClienteRepository;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.repository.PedidoRepository;

public class PedidosClientesServiceImpl implements PedidosClientesService {

	private final PedidoRepository pedidoRepo;
	private final ClienteRepository clienteRepo;

	public PedidosClientesServiceImpl(PedidoRepository repo, ClienteRepository clienteRepo) {
		this.pedidoRepo = repo;
		this.clienteRepo = clienteRepo;
	}

	@Override
	public void crearCliente(Cliente cliente) throws PedidosClientesServiceException {

		clienteRepo.save(cliente);

		// TODO: IMPLEMENTAR...
	}

	@Override
	public Pedido crearPedido(Pedido pedido) throws PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}

	@Override
	public Articulo crearArticulo(Articulo articulo) throws PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
	}

	@Override
	public Cliente consultarCliente(String dni) throws NotFoundException, PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}

	@Override
	public Articulo consultarArticulo(Long idArticulo) throws NotFoundException, PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}

	@Override
	public Pedido consultarPedido(String uuid) throws NotFoundException, PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}

	@Override
	public List<Pedido> consultarPedidosByArticulo(String descripcionArticulo)
			throws PedidosClientesServiceException, NotFoundException {
		// TODO: IMPLEMENTAR...
		return null;
	}

}
