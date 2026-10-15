package ceu.dam.ad.pedidos.service;

import java.math.BigDecimal;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.pedidos.dao.ArticuloPedidoRepository;
import ceu.dam.ad.pedidos.dao.PedidoRepository;
import ceu.dam.ad.pedidos.model.ArticuloPedido;
import ceu.dam.ad.pedidos.model.Pedido;
import jakarta.transaction.Transactional;

@Service
public class PedidoServiceImp implements PedidoService {
	private final PedidoRepository repo;
	private final ArticuloPedidoRepository repoArticulo;
//	private static final Logger logger = LoggerFactory.getLogger(PedidoServiceImp.class);

	PedidoServiceImp(PedidoRepository repo, ArticuloPedidoRepository repoArticulo) {
		this.repo = repo;
		this.repoArticulo = repoArticulo;
	}

	@Override
	public Pedido consultarPedido(Long idPedido) throws PedidoException, PedidoNotFoundException {
		try {

			Pedido pedidoConsul = repo.findById(idPedido).orElseThrow(() -> new PedidoNotFoundException());

			List<ArticuloPedido> articulos = repoArticulo.findByIdPedido(idPedido);

			pedidoConsul.setArticulos(articulos);

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
					impuestos = impuestos.add(articuloPedido.getPrecioSinIva().multiply(BigDecimal.valueOf(0.21).setScale(2)));

				}
			}
			pedido.setImpuestos(impuestos);

			pedido.setTotal(subtotal.add(impuestos));

			repo.save(pedido);

			for (ArticuloPedido articuloPedido : articulos) {
				articuloPedido.setIdPedido(pedido.getIdPedido());
				repoArticulo.save(articuloPedido);
			}

			return pedido;

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new PedidoException("Error al acceder a la base de datos", e);
		}
	}

	@Override
	public List<Long> buscarPedidosArticulo(String codBarras) throws PedidoException, PedidoNotFoundException {
		try {
			List<ArticuloPedido> articulos = repoArticulo.findByCodBarras(codBarras);

			if (articulos.isEmpty()) {
				throw new PedidoNotFoundException("El pedido no existe");
			}

			List<Long> numeros = articulos.stream().map(articulo -> articulo.getIdPedido()).distinct().toList();

			return numeros;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new PedidoException("Error al acceder a la base de datos", e);
		}
	}

}
