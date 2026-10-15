package ceu.dam.ad.pedidos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.pedidos.model.ArticuloPedido;
import ceu.dam.ad.pedidos.model.Pedido;
import ceu.dam.ad.pedidos.service.PedidoException;
import ceu.dam.ad.pedidos.service.PedidoNotFoundException;
import ceu.dam.ad.pedidos.service.PedidoService;

@SpringBootApplication
public class PedidosServiceApplication {

	private static final String COD_TECLADO = "8410000000011";
	private static final String COD_LIBRO = "8410000000028";
	private static final String COD_INEXISTENTE = "0000000000000";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PedidosServiceApplication.class, args);
		PedidoService service = context.getBean(PedidoService.class);

		// Cada prueba va en su propio try/catch para que un fallo no corte las demás
		Long idCreado = probarCrearPedido(service);

		if (idCreado != null) {
			probarConsultarPedido(service, idCreado);
		}
		probarConsultarPedidoInexistente(service);

		probarBuscarPedidosArticulo(service, COD_TECLADO);
		probarBuscarPedidosArticulo(service, COD_LIBRO);
		probarBuscarPedidosArticuloInexistente(service);

		context.close();
	}

	// ---------------------------------------------------------------
	// 1. crearPedido
	// ---------------------------------------------------------------
	private static Long probarCrearPedido(PedidoService service) {
		titulo("1. crearPedido");
		try {
			ArticuloPedido art1 = new ArticuloPedido();
			art1.setCodBarras(COD_TECLADO);
			art1.setDescripcionArticulo("Teclado Mecanico RGB");
			art1.setPrecioSinIva(new BigDecimal("100.00"));
			art1.setExentoIva(false); // 21% -> 21.00

			ArticuloPedido art2 = new ArticuloPedido();
			art2.setCodBarras(COD_LIBRO);
			art2.setDescripcionArticulo("Libro Formacion (Exento)");
			art2.setPrecioSinIva(new BigDecimal("50.00"));
			art2.setExentoIva(true); // exento -> 0.00

			List<ArticuloPedido> articulos = new ArrayList<>();
			articulos.add(art1);
			articulos.add(art2);

			Pedido nuevo = new Pedido();
			nuevo.setDniCliente("12345678X");
			nuevo.setFechaPedido(LocalDate.now());
			nuevo.setArticulos(articulos);

			Pedido guardado = service.crearPedido(nuevo);

			imprimirPedido(guardado);
			System.out.println("   Esperado -> Subtotal 150.00 | Impuestos 21.00 | Total 171.00");
			return guardado.getIdPedido();

		} catch (PedidoException e) {
			System.out.println("❌ PedidoException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("❌ Error inesperado: " + e);
		}
		return null;
	}

	// ---------------------------------------------------------------
	// 2. consultarPedido
	// ---------------------------------------------------------------
	private static void probarConsultarPedido(PedidoService service, Long id) {
		titulo("2a. consultarPedido (existente, id " + id + ")");
		try {
			Pedido pedido = service.consultarPedido(id);
			imprimirPedido(pedido);
			System.out.println("   Esperado -> 2 artículos");
		} catch (PedidoNotFoundException e) {
			System.out.println("❌ No debería lanzar PedidoNotFoundException: " + e.getMessage());
		} catch (PedidoException e) {
			System.out.println("❌ PedidoException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("❌ Error inesperado: " + e);
		}
	}

	private static void probarConsultarPedidoInexistente(PedidoService service) {
		titulo("2b. consultarPedido (inexistente)");
		try {
			service.consultarPedido(-1L);
			System.out.println("❌ Debería haber lanzado PedidoNotFoundException");
		} catch (PedidoNotFoundException e) {
			System.out.println("✅ PedidoNotFoundException correcta: " + e.getMessage());
		} catch (PedidoException e) {
			System.out.println("❌ PedidoException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("❌ Error inesperado: " + e);
		}
	}

	// ---------------------------------------------------------------
	// 3. buscarPedidosArticulo
	// ---------------------------------------------------------------
	private static void probarBuscarPedidosArticulo(PedidoService service, String codBarras) {
		titulo("3a. buscarPedidosArticulo (" + codBarras + ")");
		try {
			List<Long> ids = service.buscarPedidosArticulo(codBarras);
			System.out.println("✅ Pedidos encontrados: " + ids);
		} catch (PedidoNotFoundException e) {
			System.out.println("❌ No debería lanzar PedidoNotFoundException: " + e.getMessage());
		} catch (PedidoException e) {
			System.out.println("❌ PedidoException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("❌ Error inesperado: " + e);
		}
	}

	private static void probarBuscarPedidosArticuloInexistente(PedidoService service) {
		titulo("3b. buscarPedidosArticulo (código inexistente)");
		try {
			service.buscarPedidosArticulo(COD_INEXISTENTE);
			System.out.println("❌ Debería haber lanzado PedidoNotFoundException");
		} catch (PedidoNotFoundException e) {
			System.out.println("✅ PedidoNotFoundException correcta: " + e.getMessage());
		} catch (PedidoException e) {
			System.out.println("❌ PedidoException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("❌ Error inesperado: " + e);
		}
	}

	// ---------------------------------------------------------------
	// Utilidades
	// ---------------------------------------------------------------
	private static void titulo(String texto) {
		System.out.println();
		System.out.println("========== " + texto + " ==========");
	}

	private static void imprimirPedido(Pedido p) {
		System.out.println("✅ Pedido " + p.getIdPedido() + " | Cliente " + p.getDniCliente() + " | Fecha " + p.getFechaPedido());
		System.out.println("   Subtotal: " + p.getSubtotal() + " € | Impuestos: " + p.getImpuestos() + " € | Total: " + p.getTotal() + " €");
		List<ArticuloPedido> arts = p.getArticulos();
		System.out.println("   Artículos (" + (arts == null ? 0 : arts.size()) + "):");
		if (arts != null) {
			for (ArticuloPedido a : arts) {
				System.out.println("     - [" + a.getId() + "] " + a.getCodBarras() + " " + a.getDescripcionArticulo()
						+ " " + a.getPrecioSinIva() + " € " + (Boolean.TRUE.equals(a.getExentoIva()) ? "(exento)" : "(21%)"));
			}
		}
	}
}