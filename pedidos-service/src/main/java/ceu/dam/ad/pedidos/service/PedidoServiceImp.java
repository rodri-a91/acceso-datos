package ceu.dam.ad.pedidos.service;

import java.math.BigDecimal;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

//import ceu.dam.ad.pedidos.dao.ArticuloPedidoRepository;
import ceu.dam.ad.pedidos.dao.PedidoRepository;
import ceu.dam.ad.pedidos.model.ArticuloPedido;
import ceu.dam.ad.pedidos.model.Pedido;
import jakarta.transaction.Transactional;

@Service
public class PedidoServiceImp implements PedidoService {
	private final PedidoRepository repo;
//	private final ArticuloPedidoRepository repoArticulo;
//	private static final Logger logger = LoggerFactory.getLogger(PedidoServiceImp.class);

	PedidoServiceImp(PedidoRepository repo) {
		this.repo = repo;
//		this.repoArticulo = repoArticulo;
	}

	@Override
	public Pedido consultarPedido(Long idPedido) throws PedidoException, PedidoNotFoundException {
		try {

			Pedido pedidoConsul = repo.findById(idPedido).orElseThrow(() -> new PedidoNotFoundException());

			return pedidoConsul;

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new PedidoException("Error al acceder a la base de datos", e);
		}
	}

	@Override
	@Transactional
	public Pedido crearPedido(Pedido pedido) throws PedidoException {
		try {

			List<ArticuloPedido> articulos = pedido.getArticulos();
			BigDecimal subtotal = BigDecimal.ZERO;
			BigDecimal impuestos = BigDecimal.ZERO;

			for (ArticuloPedido articuloPedido : articulos) {
				subtotal = subtotal.add(articuloPedido.getPrecioSinIva());
			}

			pedido.setSubtotal(subtotal);

			for (ArticuloPedido articuloPedido : articulos) {
				if (!articuloPedido.getExentoIva()) {
					impuestos = impuestos
							.add(articuloPedido.getPrecioSinIva().multiply(BigDecimal.valueOf(0.21).setScale(2)));

				}
			}
			pedido.setImpuestos(impuestos);

			pedido.setTotal(subtotal.add(impuestos));

			return repo.save(pedido);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new PedidoException("Error al acceder a la base de datos", e);
		}
	}

	@Override
	public List<Long> buscarPedidosArticulo(String codBarras) throws PedidoException, PedidoNotFoundException {
		try {
			List<Pedido> pedidos = repo.findDistinctByArticulosCodBarras(codBarras);

			if (pedidos.isEmpty()) {
				throw new PedidoNotFoundException("El pedido no existe");
			}

			return pedidos.stream().map(pedido -> pedido.getIdPedido()).toList();

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new PedidoException("Error al acceder a la base de datos", e);
		}
	}

}
