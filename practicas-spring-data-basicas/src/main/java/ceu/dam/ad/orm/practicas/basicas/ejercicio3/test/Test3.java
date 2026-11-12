package ceu.dam.ad.orm.practicas.basicas.ejercicio3.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.CentroComercial;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Marca;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Pais;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.modelo.Tienda;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.service.ComercialException;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.service.ComercialService;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.service.ComercialServiceImpl;
import ceu.dam.ad.orm.practicas.basicas.ejercicio3.service.NotFoundException;


public class Test3 {
	// CADA VEZ QUE EJECUTES EL TEST, PASA ESTE SCRIPT EN BBDD:
//	DELETE FROM centro_comercial_marcas;
//	DELETE FROM tiendas;
//	DELETE FROM centro_comercial;
//	DELETE FROM marcas;

	public void test() {
		ComercialService service = new ComercialServiceImpl();
		Scanner sc = new Scanner(System.in);
		Pais paisUS = null;
		Pais paisES = null;
		Tienda tiendaTest = null;
		
		try {
		
			System.out.println(">> Probamos a consultar países....");
			try {
				List<Pais> paises = service.buscarPaises("ES");
				if (paises.size()!=2) {
					System.out.println(">> No funciona la consulta de países. Devuelve más de 2 resultados. Revisa e intenta de nuevo.");
					return;
				}
				System.out.println(">> Países encontrados: " + paises);
				for (Pais p : paises) {
					if (p.getCodigo().equals("US")) {
						paisUS = p;
					}
					else if(p.getCodigo().equals("ES")) {
						paisES = p;
					} 
				}
				if (paisUS == null) {
					System.out.println(">> No funciona la consulta de países. No devuelve el país Estados Unidos. Revisa e intenta de nuevo.");
					return;
				}
				if (paisES == null) {
					System.out.println(">> No funciona la consulta de países. No devuelve el país España. Revisa e intenta de nuevo.");
					return;
				}
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona la consulta de países. Mira el error e intenta de nuevo.");
				return;
			}
			System.out.println(">> La consulta de países parece que funciona. Revisa los datos impresos encima de esta línea. Si está Ok y quieres continuar, pulsa enter");
			sc.nextLine();
			
			
			System.out.println(">> Probamos a insertar marcas....");
			Marca apple = new Marca("APPL", "Apple", paisUS);
			Marca nike = new Marca("NIKE", "Nike", paisUS);
			Marca starbucks = new Marca("STBU", "Starbucks", paisUS);
			Marca[] marcas = {apple, nike, starbucks};
			
			try {
				service.insertarMarca(apple);
				service.insertarMarca(nike);
				service.insertarMarca(starbucks);
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona insertar marcas. Mira el error e intenta de nuevo.");
				return;
			}
	
			System.out.println(">> El insertar marcas no ha dado error. Mira que estén en BBDD 3 marcas (Apple, Starbucks y Nike). Si está OK y quieres continuar, pulsa ENTER");
			sc.nextLine();
	
			
			System.out.println(">> Probamos a insertar centro comercial....");
			CentroComercial cc = new CentroComercial();
			cc.setPais(paisES);
			cc.setDireccion("Avda de los caracoles amarillos, S/N");
			cc.setNombre("Centro Comercial Torre Caracol");
			cc.setTiendas(new ArrayList<>());
			cc.setMarcas(Arrays.asList(marcas));
			for (Marca marca : marcas) {
				marca.setCentrosComerciales(new ArrayList<>());
				marca.getCentrosComerciales().add(cc);
			}
			for (int i = 0; i < 6; i++) {
				Tienda t = new Tienda();
				t.setMarca(marcas[i%3]);
				t.setUbicacion("Sección " + i);
				cc.getTiendas().add(t);
			}
			try {
				service.insertarCentroComercial(cc);
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona insertar centro comercial. Mira el error e intenta de nuevo.");
				return;
			}
			System.out.println(">> UUID generado para el centro comercial insertado: " + cc.getId());
			System.out.println(">> El insertar centro comercial no ha dado error. Mira que estén en BBDD un centro comercial de España con 6 tiendas asociadas. Si está OK y quieres continuar, pulsa ENTER");
			sc.nextLine();
	
			System.out.println(">> Probamos a consultar centro comercial....");
			try {
				cc = service.consultarCentroComercial(cc.getId().toString());
				System.out.println(">> Centro comercial obtenido: " + cc);
				
				try {
					tiendaTest = cc.getTiendas().get(5);
				}
				catch(Exception e) {
					System.out.println(">> No se han cargado todas las tiendas del centro comercial. Revisa e intenta de nuevo");
					return;
				}
	
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona consultar centro comercial. Mira el error e intenta de nuevo.");
				return;
			} catch (NotFoundException e) {
				e.printStackTrace();
				System.out.println(">> No se encuenta el centro comercial que acabamos de insertar con uuid " + cc.getId() + " //  Mira el error e intenta de nuevo.");
				return;
			}
			
			System.out.println(">> El consultar centro comercial no ha dado error. Mira que estén todos sus datos impresos.  Si está OK y quieres continuar, pulsa ENTER");
			sc.nextLine();
	
			System.out.println(">> Probamos a consultar tienda....");
			try {
				tiendaTest = service.consularTienda(tiendaTest.getId());
				System.out.println(">> Tienda obtenida: " + tiendaTest);
				
				try {
					Marca m = tiendaTest.getMarca();
					System.out.println(">> Marca de la tienda: " + m);
					System.out.println(">> Has obtenido la marca de la tienda. El servicio dice que no te traigas sus entidades asociadas. Revisa e intenta de nuevo.");
					return;
				}
				catch(Exception e) {
				}
	
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona consultar centro comercial. Mira el error e intenta de nuevo.");
				return;
			} catch (NotFoundException e) {
				e.printStackTrace();
				System.out.println(">> No se encuenta el centro comercial que acabamos de insertar con uuid " + cc.getId() + " //  Mira el error e intenta de nuevo.");
				return;
			}
	
			
			System.out.println(">> El consultar tienda no ha dado error. Mira que estén todos sus datos impresos salvo la marca.  Si está OK y quieres continuar, pulsa ENTER");
			sc.nextLine();
	
			System.out.println(">> Probamos a borrar tienda...");
			try {
				service.borrarTienda(tiendaTest.getId());
				try {
					tiendaTest = service.consularTienda(tiendaTest.getId());
					System.out.println(">> No funciona borrar tienda porque después de borrar estoy consultando y no me lanza NotFound. Mira el error e intenta de nuevo.");
					return;
				}
				catch(NotFoundException nfe) {
				}
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona borrar tienda. Mira el error e intenta de nuevo.");
				return;
			}
			
			System.out.println(">> El borrar tienda no ha dado error. Mira que en la BBDD sólo queden 5 tiendas en lugar de 6. Si está OK y quieres continuar, pulsa ENTER");
			sc.nextLine();
	
			String uuid = cc.getId().toString();
			System.out.println(">> Probamos a borrar centro comercial con uuid " + uuid + "...");
			try {
				service.borrarCentroComercial(uuid);
				try {
					cc = service.consultarCentroComercial(uuid);
					System.out.println(">> No funciona borrar centro comercial porque después de borrar estoy consultando y no me lanza NotFound. Mira el error e intenta de nuevo.");
					return;
				}
				catch(NotFoundException nfe) {
				}
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona borrar centro comercial. Mira el error e intenta de nuevo.");
				return;
			}
			System.out.println(">> El borrado de centro comercial parece que funciona. Revisa que en BBDD se hayan borrado todos los datos salvo países y marcas. Si está Ok y quieres continuar, pulsa enter");
			sc.nextLine();
	
			
			
			System.out.println(">> Probamos a consultar países....");
			try {
				List<Pais> paises = service.buscarPaises("");
				if (paises.size()!=7) {
					System.out.println(">> No funciona la consulta de países. Debería devolver 7 resultados. ¿Has borrado los países? Revisa e intenta de nuevo.");
					return;
				}
				System.out.println(">> Todos los países: " + paises);
			} catch (ComercialException e) {
				e.printStackTrace();
				System.out.println(">> No funciona la consulta de países. Mira el error e intenta de nuevo.");
				return;
			}
			System.out.println(">> Parece que está todo OK. Terminado!!");
		
		}
		finally {
			sc.close();
		}
		
	}

}
