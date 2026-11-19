package ceu.dam.ad.orm.practicas.biblioteca.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import ceu.dam.ad.orm.practicas.biblioteca.model.Carnet;
import ceu.dam.ad.orm.practicas.biblioteca.model.Libro;
import ceu.dam.ad.orm.practicas.biblioteca.model.Prestamo;
import ceu.dam.ad.orm.practicas.biblioteca.model.Socio;
import ceu.dam.ad.orm.practicas.biblioteca.service.BibliotecaException;
import ceu.dam.ad.orm.practicas.biblioteca.service.BibliotecaService;
import ceu.dam.ad.orm.practicas.biblioteca.service.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestBiblioteca {

	private final BibliotecaService service;

	public void test() {
//        -- Para limpiar la bbdd ANTES de cada ejecución del test:
//        DELETE FROM prestamo;
//        DELETE FROM carnet;
//        DELETE FROM socio;
//        DELETE FROM libro WHERE id > 8;

		Scanner sc = new Scanner(System.in);
		try {
			// ---------- Test buscar libros (Query By Example) sobre los 8 libros del script
			Libro ejemplo = new Libro();
			ejemplo.setAutor("tolkien");
			List<Libro> libros = service.buscarLibros(ejemplo);
			comprobar(libros.size() == 2, "Buscar por autor 'tolkien' debería devolver 2 libros y devuelve " + libros.size());

			ejemplo = new Libro();
			ejemplo.setGenero("CIENCIA");
			ejemplo.setAutor("asimov");
			libros = service.buscarLibros(ejemplo);
			comprobar(libros.size() == 2, "Buscar por género 'CIENCIA' y autor 'asimov' debería devolver 2 libros y devuelve " + libros.size());

			ejemplo = new Libro();
			ejemplo.setIsbn("9780000000003");
			libros = service.buscarLibros(ejemplo);
			comprobar(libros.size() == 1, "Buscar por ISBN exacto debería devolver 1 libro y devuelve " + libros.size());

			ejemplo = new Libro();
			ejemplo.setIsbn("0000003");
			libros = service.buscarLibros(ejemplo);
			comprobar(libros.size() == 1, "Buscar por parte del ISBN ('0000003') debería devolver 1 libro y devuelve " + libros.size());

			ejemplo = new Libro();
			ejemplo.setAutor("tolkien");
			ejemplo.setEjemplaresDisponibles(3);
			libros = service.buscarLibros(ejemplo);
			comprobar(libros.size() == 1, "Buscar por autor 'tolkien' y 3 ejemplares disponibles debería devolver 1 libro (El Hobbit) y devuelve " + libros.size());

			libros = service.buscarLibros(new Libro());
			comprobar(libros.size() >= 8, "Con un ejemplo vacío deben devolverse todos los libros");
			System.out.println(">>> Búsquedas de libros con Example correctas");

			// ---------- Test alta de libros
			Libro[] test = new Libro[5];
			for (int i = 0; i < test.length; i++) {
				Libro l = new Libro();
				l.setIsbn("999000000000" + (i + 1));
				l.setTitulo("Libro TEST " + (i + 1));
				l.setAutor("Autor TEST");
				l.setGenero("Test");
				l.setEjemplaresDisponibles(i == 4 ? 0 : 1); // el libro 5 no tiene ejemplares
				test[i] = service.altaLibro(l);
				comprobar(test[i].getId() != null, "El libro creado debe tener id");
			}
			System.out.println(">>> Libros de prueba creados: " + test[0].getId() + " ... " + test[4].getId());
			try {
				Libro repetido = new Libro();
				repetido.setIsbn("9990000000001");
				repetido.setTitulo("Repetido");
				repetido.setAutor("Autor TEST");
				repetido.setGenero("Test");
				repetido.setEjemplaresDisponibles(1);
				service.altaLibro(repetido);
				throw new RuntimeException("Debería lanzar BibliotecaException al dar de alta un libro con ISBN repetido");
			}
			catch (BibliotecaException e) {
				System.out.println(">>> Alta de libro con ISBN repetido rechazada correctamente");
			}

			// ---------- Test alta y consulta de socio
			Socio socio = new Socio();
			socio.setDni("00000000T");
			socio.setNombre("Laura");
			socio.setApellidos("Careto Blau");
			socio = service.altaSocio(socio);
			Carnet carnet = socio.getCarnet();
			comprobar(carnet != null && carnet.getId() != null, "El socio dado de alta debe tener carnet con UUID");
			comprobar(carnet.getFechaCaducidad().equals(LocalDate.now().plusYears(1)), "La caducidad del carnet nuevo debe ser hoy + 1 año");
			System.out.println(">>> Socio creado con carnet: " + carnet);
			try {
				service.altaSocio(socio);
				throw new RuntimeException("Debería lanzar BibliotecaException al dar de alta un socio con DNI repetido");
			}
			catch (BibliotecaException e) {
				System.out.println(">>> Alta de socio repetido rechazada correctamente");
			}
			socio = service.consultarSocio("00000000T");
			comprobar(socio.getCarnet() != null, "consultarSocio debe traer el carnet");
			comprobar(socio.getPrestamos() != null && socio.getPrestamos().isEmpty(), "El socio recién creado no tiene préstamos");
			try {
				service.consultarSocio("NOEXISTE");
				throw new RuntimeException("Debería lanzar NotFoundException si el socio no existe");
			}
			catch (NotFoundException e) {
				System.out.println(">>> Consulta de socio que no existe correcta");
			}

			// ---------- Test préstamos
			int anio = LocalDate.now().getYear();
			Prestamo p1 = service.prestarLibro("00000000T", test[0].getId());
			Prestamo p2 = service.prestarLibro("00000000T", test[1].getId());
			Prestamo p3 = service.prestarLibro("00000000T", test[2].getId());
			comprobar(p1.getNumPrestamo() == 1 && p2.getNumPrestamo() == 2 && p3.getNumPrestamo() == 3,
					"Los números de préstamo deben ser 1, 2 y 3");
			comprobar(stock(test[0].getIsbn()) == 0, "Al prestar el libro debe descontarse un ejemplar");
			System.out.println(">>> 3 préstamos creados: " + p1 + " / " + p2 + " / " + p3);

			esperaError(() -> service.prestarLibro("00000000T", test[3].getId()),
					"No debería poder prestar un 4º libro: el límite es de 3 préstamos activos");
			try {
				service.prestarLibro("00000000T", 999999L);
				throw new RuntimeException("Debería lanzar NotFoundException si el libro no existe");
			}
			catch (NotFoundException e) {
				System.out.println(">>> Préstamo de libro que no existe rechazado correctamente");
			}
			try {
				service.prestarLibro("NOEXISTE", test[3].getId());
				throw new RuntimeException("Debería lanzar NotFoundException si el socio no existe");
			}
			catch (NotFoundException e) {
				System.out.println(">>> Préstamo a socio que no existe rechazado correctamente");
			}

			// ---------- Test devoluciones
			service.devolverLibro(anio, 1);
			comprobar(stock(test[0].getIsbn()) == 1, "Al devolver el libro debe sumarse un ejemplar");
			esperaError(() -> service.devolverLibro(anio, 1), "No debería poder devolver dos veces el mismo préstamo");
			try {
				service.devolverLibro(anio, 99);
				throw new RuntimeException("Debería lanzar NotFoundException si el préstamo no existe");
			}
			catch (NotFoundException e) {
				System.out.println(">>> Devolución de préstamo que no existe rechazada correctamente");
			}

			// ---------- Más reglas de préstamo
			esperaError(() -> service.prestarLibro("00000000T", test[4].getId()),
					"No debería poder prestar un libro sin ejemplares disponibles");
			esperaError(() -> service.prestarLibro("00000000T", test[1].getId()),
					"No debería poder prestar un libro que el socio ya tiene prestado");
			Prestamo p4 = service.prestarLibro("00000000T", test[3].getId());
			comprobar(p4.getNumPrestamo() == 4, "El número de préstamo debe ser 4 (el último del año + 1, aunque el 1 esté devuelto)");

			List<Prestamo> activos = service.consultarPrestamosActivos("00000000T");
			comprobar(activos.size() == 3, "Debería haber 3 préstamos activos y hay " + activos.size());
			System.out.println(">>> Préstamos activos: " + activos);

			socio = service.consultarSocio("00000000T");
			comprobar(socio.getPrestamos().size() == 4, "El socio debe traer sus 4 préstamos (3 activos y 1 devuelto)");
			System.out.println(">>> Consulta de socio con sus préstamos correcta");

			// ---------- Test renovar carnet
			LocalDate caducidadAntes = socio.getCarnet().getFechaCaducidad();
			carnet = service.renovarCarnet("00000000T");
			comprobar(carnet.getFechaCaducidad().equals(caducidadAntes.plusYears(1)), "Un carnet vigente debe renovarse sumando 1 año a su caducidad actual");
			System.out.println(">>> Carnet renovado: " + carnet);

			// ---------- Test de préstamos vencidos (necesita tocar la BBDD a mano)
			System.out.println(">>> Ahora simularemos un préstamo vencido. Ejecuta esto en la BBDD y pulsa ENTER:");
			System.out.println("    UPDATE prestamo SET fecha_prestamo = DATE_SUB(CURDATE(), INTERVAL 40 DAY) WHERE anio = YEAR(CURDATE()) AND num_prestamo = 2;");
			sc.nextLine();
			esperaError(() -> service.renovarCarnet("00000000T"), "No debería poder renovar el carnet con un préstamo vencido");
			service.devolverLibro(anio, 3);
			service.devolverLibro(anio, 4);
			esperaError(() -> service.prestarLibro("00000000T", test[0].getId()),
					"No debería poder pedir un préstamo teniendo otro vencido");
			service.devolverLibro(anio, 2);
			Prestamo p5 = service.prestarLibro("00000000T", test[0].getId());
			comprobar(p5.getNumPrestamo() == 5, "El siguiente número de préstamo debe ser 5");
			System.out.println(">>> Reglas de préstamos vencidos correctas");

			// ---------- La numeración es por año y común a todos los socios
			Socio otro = new Socio();
			otro.setDni("11111111H");
			otro.setNombre("Marcel");
			otro.setApellidos("Careto Rojo");
			service.altaSocio(otro);
			Prestamo p6 = service.prestarLibro("11111111H", test[1].getId());
			comprobar(p6.getAnio() == anio && p6.getNumPrestamo() == 6,
					"La numeración es por año, no por socio: el préstamo de otro socio debe ser el 6 y es el " + p6.getNumPrestamo());
			System.out.println(">>> Numeración de préstamos por año correcta: " + p6);

			System.out.println(">>>>>> TODAS LAS PRUEBAS HAN SIDO CORRECTAS <<<<<<");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int stock(String isbn) {
		Libro ejemplo = new Libro();
		ejemplo.setIsbn(isbn);
		return service.buscarLibros(ejemplo).get(0).getEjemplaresDisponibles();
	}

	private void comprobar(boolean condicion, String mensajeError) {
		if (!condicion) {
			throw new RuntimeException("ALGO NO ESTÁ BIEN: " + mensajeError);
		}
	}

	private interface Operacion {
		void ejecutar() throws Exception;
	}

	/** Comprueba que la operación lanza BibliotecaException. */
	private void esperaError(Operacion op, String mensajeSiNoFalla) {
		try {
			op.ejecutar();
		}
		catch (BibliotecaException e) {
			System.out.println(">>> Rechazo correcto: " + e.getMessage());
			return;
		}
		catch (Exception e) {
			throw new RuntimeException("Se esperaba BibliotecaException pero se lanzó " + e.getClass().getSimpleName(), e);
		}
		throw new RuntimeException("ALGO NO ESTÁ BIEN: " + mensajeSiNoFalla);
	}

}
