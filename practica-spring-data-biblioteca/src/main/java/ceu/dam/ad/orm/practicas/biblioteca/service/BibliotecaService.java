package ceu.dam.ad.orm.practicas.biblioteca.service;

import java.util.List;

import ceu.dam.ad.orm.practicas.biblioteca.model.Carnet;
import ceu.dam.ad.orm.practicas.biblioteca.model.Libro;
import ceu.dam.ad.orm.practicas.biblioteca.model.Prestamo;
import ceu.dam.ad.orm.practicas.biblioteca.model.Socio;

/**
 * Servicio de gestión de una biblioteca.
 *
 * REGLAS DE NEGOCIO GENERALES (se aplican en varios métodos):
 *  - Un socio solo puede tener, como máximo, 3 préstamos activos a la vez.
 *    Un préstamo está "activo" mientras no tenga fecha de devolución.
 *  - El plazo de un préstamo es de 30 días. Un préstamo activo está "vencido" cuando
 *    han pasado más de 30 días desde su fecha de préstamo.
 *  - Un carnet está "vigente" si su fecha de caducidad es igual o posterior a hoy.
 *  - Cada préstamo se identifica por su año y su número dentro de ese año. La numeración
 *    empieza en 1 cada año y es común a todos los socios.
 *
 * BibliotecaException se lanza únicamente cuando se incumple alguna de las reglas de negocio descritas
 * en cada método. Los errores de acceso a datos NO se capturan: se propagan tal cual como excepciones
 * no comprobadas de Spring (DataAccessException).
 */
public interface BibliotecaService {

	/** Da de alta un socio nuevo en BBDD. Si ya existe un socio con ese DNI, lanza BibliotecaException.
	 * Al darlo de alta se le genera SIEMPRE un carnet nuevo (aunque el socio recibido ya trajera uno):
	 * fecha de alta = hoy, fecha de caducidad = hoy + 1 año. Socio y carnet se guardan juntos.
	 * Devuelve el socio registrado, completo (con su carnet ya con UUID). */
	public Socio altaSocio(Socio socio) throws BibliotecaException;

	/** Consulta el socio con el DNI indicado. Si no existe, lanza NotFoundException.
	 * El socio devuelto incluye su carnet y todos sus préstamos (activos y devueltos). */
	public Socio consultarSocio(String dni) throws NotFoundException;

	/** Da de alta un libro nuevo. Si ya existe un libro con el mismo ISBN, lanza BibliotecaException.
	 * Devuelve el libro registrado con su id generado. */
	public Libro altaLibro(Libro libro) throws BibliotecaException;

	/** Busca libros a partir de un libro "de ejemplo" (Query By Example). Reglas:
	 *  - Solo se tienen en cuenta los campos informados (los null se ignoran).
	 *  - campos de texto (titulo, isbn, autor y genero): coincidencia PARCIAL (contiene) e ignorando mayúsculas/minúsculas.
	 *  - ejemplaresDisponibles (numérico): si viene informado, debe coincidir exactamente.
	 * Si no hay resultados, devuelve una lista vacía. Si el ejemplo no tiene ningún campo informado,
	 * devuelve todos los libros. */
	public List<Libro> buscarLibros(Libro ejemplo);

	/** Registra el préstamo de un libro a un socio. Si el socio o el libro no existen, lanza
	 * NotFoundException. Lanzará BibliotecaException si:
	 *  - el socio no tiene un carnet vigente,
	 *  - el socio ya tiene 3 préstamos activos,
	 *  - el socio tiene algún préstamo activo vencido,
	 *  - el socio ya tiene prestado ese mismo libro (préstamo activo),
	 *  - no quedan ejemplares disponibles del libro.
	 * Si todo es correcto: el año del préstamo será el año actual y su número será el siguiente al mayor número
	 * de préstamo registrado en ese año (de cualquier socio; 1 si es el primero del año), la fecha de préstamo será hoy y se descontará un ejemplar
	 * disponible del libro. Todo ello debe ocurrir en una única transacción.
	 * Devuelve el préstamo registrado. */
	public Prestamo prestarLibro(String dniSocio, Long idLibro) throws NotFoundException, BibliotecaException;

	/** Registra la devolución del préstamo identificado por (anio, numPrestamo). Si no existe,
	 * lanza NotFoundException. Si el préstamo ya estaba devuelto, lanza BibliotecaException.
	 * Si es correcto: fecha de devolución = hoy y se suma un ejemplar disponible al libro.
	 * Todo ello debe ocurrir en una única transacción. */
	public void devolverLibro(Integer anio, Integer numPrestamo) throws NotFoundException, BibliotecaException;

	/** Renueva el carnet del socio. Si el socio no existe, lanza NotFoundException.
	 * Lanza BibliotecaException si el socio no tiene carnet o si tiene algún préstamo activo vencido.
	 * La nueva fecha de caducidad será: 1 año a partir de la fecha de caducidad actual si el carnet
	 * sigue vigente, o 1 año a partir de hoy si ya había caducado.
	 * Devuelve el carnet actualizado. */
	public Carnet renovarCarnet(String dniSocio) throws NotFoundException, BibliotecaException;

	/** Devuelve los préstamos activos (sin fecha de devolución) del socio. Si el socio no existe,
	 * lanza NotFoundException. Si no tiene ninguno, devuelve una lista vacía. */
	public List<Prestamo> consultarPrestamosActivos(String dniSocio) throws NotFoundException;

}
