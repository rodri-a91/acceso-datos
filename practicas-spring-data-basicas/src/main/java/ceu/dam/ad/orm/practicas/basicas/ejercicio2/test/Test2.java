package ceu.dam.ad.orm.practicas.basicas.ejercicio2.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Articulo;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Cliente;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.Pedido;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.model.PedidoLinea;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.service.NotFoundException;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.service.PedidosClientesService;
import ceu.dam.ad.orm.practicas.basicas.ejercicio2.service.PedidosClientesServiceImpl;

public class Test2 {

	public void test() {
//		-- Para limpiar la bbdd después de cada prueba:
//		DELETE FROM PEDIDO_LINEAS;
//		DELETE FROM PEDIDO;
//		DELETE FROM CLIENTE;
//		DELETE FROM ARTICULO WHERE ID > 7;		
		
		
		PedidosClientesService service = new PedidosClientesServiceImpl();
		try {
			// Test crear articulo
			Articulo articuloNuevo = new Articulo();
			articuloNuevo.setCodBarras("999990000088");
			articuloNuevo.setDescripcion("Artículo TEST");
			articuloNuevo = service.crearArticulo(articuloNuevo);
			System.out.println(">>> Artículo creado: " + articuloNuevo);
			
			// Test consulta de artículos ya registrados antes
			Articulo[] articulos = new Articulo[8];
			for (int i = 0; i < articulos.length-1; i++) {
				articulos[i] = service.consultarArticulo(Long.valueOf(i+1));
			}
			System.out.println(">>> Consulta de artículos correcta para ids 1...7 ");
			
			// Test consulta de artículo nuevo
			articulos[7] = service.consultarArticulo(articuloNuevo.getId());
			System.out.println(">>> Consulta de artículos correcta: " + articulos[7]);
			
			
			// Test crear cliente
			Cliente cliente = new Cliente();
			cliente.setNombre("Laura");
			cliente.setApellidos("Careto Blau");
			cliente.setDni("00000000T");
			cliente.setPedidos(new HashSet<>());
			
			Pedido pedido1 = createTestPedido(cliente, articulos, 8, 2);
			Pedido pedido2 = createTestPedido(cliente, articulos, 3, 100);
			
			cliente.getPedidos().add(pedido1);
			cliente.getPedidos().add(pedido2);
			service.crearCliente(cliente); // no debe crear los pedidos
			System.out.println(">>> Cliente creado. Sin validar: " + cliente);
			
			// Test actualizar cliente
			cliente.setNombre("Marcel");
			service.actualizarCliente(cliente); // no debe actualizar los pedidos
			System.out.println(">>> Cliente actualizado. Sin validar: " + cliente);
			
			// Test crear pedidos
			pedido1 = service.crearPedido(pedido1);
			System.out.println(">>> Pedido creado con uuid " + pedido1.getUidPedido().toString());
			pedido2 = service.crearPedido(pedido2);
			System.out.println(">>> Pedido creado con uuid " + pedido2.getUidPedido().toString());
			
			// Test consultar cliente
			cliente = service.consultarCliente(cliente.getDni()); // Debe traer todos sus pedidos
			if (cliente.getPedidos()==null || cliente.getPedidos().size()!=2) {
				System.err.println("Algo no está bien: el cliente no tiene la cantidad de pedidos esperados.");
				throw new RuntimeException("Algo no está bien: el cliente no tiene la cantidad de pedidos esperados.");
			}
			if (!cliente.getPedidos().contains(pedido1) || !cliente.getPedidos().contains(pedido2)) {
				System.err.println("Algo no está bien: el cliente no tiene los pedidos esperados.");
				throw new RuntimeException("Algo no está bien: el cliente no tiene los pedidos esperados.");
			}
			System.out.println(">>> Consulta de cliente ok con 2 pedidos");
			System.out.println(">>> Pedidos obtenidos desde el cliente:");
			for (Pedido p : cliente.getPedidos()) {
				System.out.println("\t>>>" + p);
			}
			
			// Test consultar pedido
			pedido1 = service.consultarPedido(pedido1.getUidPedido().toString());
			pedido2 = service.consultarPedido(pedido2.getUidPedido().toString());
			System.out.println(">>> Pedido 1 y 2 obtenidos con consulta nueva (deben ser iguales a los anteriores):");
			System.out.println(pedido1);
			System.out.println(pedido2);
			
			
			// Test consultar negativo
			try {
				service.consultarArticulo(9999999999L);
				throw new RuntimeException("La consulta de artículos no es correcta porque no lanza excepción cuando no existe.");
			}
			catch(NotFoundException e) {
				System.out.println(" >>>> Consulta correcta de artículo que no existe");
			}
			try {
				service.consultarCliente("ESTENOEXISTE");
				throw new RuntimeException("La consulta de CLIENTES no es correcta porque no lanza excepción cuando no existe.");
			}
			catch(NotFoundException e) {
				System.out.println(" >>>> Consulta correcta de cliente que no existe");
			}
			try {
				service.consultarPedido("4384e50f-215b-457d-bc1d-c7dcd129682a");
				throw new RuntimeException("La consulta de PEDIDOS no es correcta porque no lanza excepción cuando no existe.");
			}
			catch(NotFoundException e) {
				System.out.println(" >>>> Consulta correcta de pedido que no existe");
			}
			
			
			// Test buscar pedidos
			try {
				service.consultarPedidosByArticulo("Blaster Azul").forEach(System.out::println);
				System.out.println(">> Búsqueda de pedidos correcta. Comprueba que se ha impreso 2 pedidos");
			} 
			catch (NotFoundException e) {
				System.out.println("Tu consulta de pedidos no funciona porque lanza NotFound cuando realmente sí hay pedidos con el artículo \"Blaster Azul\"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private Pedido createTestPedido(Cliente cliente, Articulo[] articulos, Integer numLineas, Integer offsetCantidad) {
		Pedido pedido = new Pedido();
		pedido.setFecha(new Date());
		pedido.setCliente(cliente);
		pedido.setLineas(new ArrayList<>());
		
		if (numLineas > articulos.length) {
			numLineas = articulos.length;
		}
		
		for (int i = 0; i < numLineas; i++) {
			PedidoLinea linea = new PedidoLinea();
			linea.setArticulo(articulos[i]);
			linea.setCantidad(i+offsetCantidad);
			pedido.getLineas().add(linea);
		}
		return pedido;

	}
	

}
