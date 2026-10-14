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
import ceu.dam.ad.pedidos.service.PedidoService;

@SpringBootApplication
public class PedidosServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PedidosServiceApplication.class, args);
		PedidoService service = context.getBean(PedidoService.class);
		
		try {
			System.out.println("--> 1. Creando un nuevo pedido...");

			// Crear articulos
			ArticuloPedido art1 = new ArticuloPedido();
			art1.setCodBarras("8410000000011");
			art1.setDescripcionArticulo("Teclado Mecanico RGB");
			art1.setPrecioSinIva(new BigDecimal("100.00"));
			art1.setExentoIva(false); // LLEVA 21% de IVA (21.00)

			ArticuloPedido art2 = new ArticuloPedido();
			art2.setCodBarras("8410000000028");
			art2.setDescripcionArticulo("Libro Formacion (Exento)");
			art2.setPrecioSinIva(new BigDecimal("50.00"));
			art2.setExentoIva(true); // EXENTO de IVA (0.00)

			List<ArticuloPedido> listaArticulos = new ArrayList<>();
			listaArticulos.add(art1);
			listaArticulos.add(art2);

			// Crear cabecera del pedido
			Pedido nuevoPedido = new Pedido();
			nuevoPedido.setDniCliente("12345678X");
			nuevoPedido.setFechaPedido(LocalDate.now());
			nuevoPedido.setArticulos(listaArticulos);

			// Guardar mediante el servicio
			Pedido pedidoGuardado = service.crearPedido(nuevoPedido);
			idPedidoGenerado = pedidoGuardado.getIdPedido();

			System.out.println("✅ Pedido creado con exito!");
			System.out.println("   ID Pedido: " + pedidoGuardado.getIdPedido());
			System.out.println("   DNI Cliente: " + pedidoGuardado.getDniCliente());
			System.out.println("   Subtotal: " + pedidoGuardado.getSubtotal() + " €");
			System.out.println("   Impuestos (21%): " + pedidoGuardado.getImpuestos() + " €");
			System.out.println("   Total: " + pedidoGuardado.getTotal() + " €");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
