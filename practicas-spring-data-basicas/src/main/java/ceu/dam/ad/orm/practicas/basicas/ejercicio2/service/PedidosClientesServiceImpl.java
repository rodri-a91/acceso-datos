package ceu.dam.ad.orm.practicas.basicas.ejercicio2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Articulo;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Cliente;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Pedido;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.repository.ArticuloRepository;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.repository.ClienteRepository;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidosClientesServiceImpl implements PedidosClientesService {

	private final PedidoRepository pedidoRepo;
	private final ClienteRepository clienteRepo;
	private final ArticuloRepository articuloRepo;

	public PedidosClientesServiceImpl(PedidoRepository repo, ClienteRepository clienteRepo,
			ArticuloRepository articuloRepo) {
		this.pedidoRepo = repo;
		this.clienteRepo = clienteRepo;
		this.articuloRepo = articuloRepo;
	}

	@Override
	public void crearCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {

			clienteRepo.save(cliente);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error creando cliente", e);
		}

	}

	@Override
	@Transactional
	public Pedido crearPedido(Pedido pedido) throws PedidosClientesServiceException {
		try {
			consultarCliente(pedido.getCliente().getDni());
			// TODO TERMINAR

		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error creando artículo", e);
		} catch (NotFoundException e) {
			throw new PedidosClientesServiceException("El cliente indicado no existe", e);
		}
		return null;
	}

	@Override
	public Articulo crearArticulo(Articulo articulo) throws PedidosClientesServiceException {
		try {
			return articuloRepo.save(articulo);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error creando artículo", e);
		}
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {

			consultarCliente(cliente.getDni());
			clienteRepo.save(cliente);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error creando cliente", e);
		} catch (NotFoundException e) {
			throw new PedidosClientesServiceException("El cliente indicado no existe", e);
		}
	}

	@Override
	public Cliente consultarCliente(String dni) throws NotFoundException, PedidosClientesServiceException {
		try {

			return clienteRepo.findById(dni).orElseThrow(() -> new NotFoundException("No existe el cliente indicado"));
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error consultando cliente", e);
		}
	}

	@Override
	public Articulo consultarArticulo(Long idArticulo) throws NotFoundException, PedidosClientesServiceException {
		return articuloRepo.findById(idArticulo)
				.orElseThrow(() -> new NotFoundException("No existe el artículo indicado"));
	}

	@Override
	public Pedido consultarPedido(String uuid) throws NotFoundException, PedidosClientesServiceException {
		return pedidoRepo.findById(UUID.fromString(uuid))
				.orElseThrow(() -> new NotFoundException("No existe el pedido indicado"));
	}

	@Override
	public List<Pedido> consultarPedidosByArticulo(String descripcionArticulo)
			throws PedidosClientesServiceException, NotFoundException {
		try {
			return pedidoRepo.findByLineasArticulosDescripcion(descripcionArticulo);

		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("Error consultando pedidos", e);
		}
	}

}