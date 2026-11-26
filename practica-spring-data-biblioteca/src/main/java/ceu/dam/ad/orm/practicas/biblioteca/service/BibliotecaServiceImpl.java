package ceu.dam.ad.orm.practicas.biblioteca.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import ceu.dam.ad.orm.practicas.biblioteca.model.Carnet;
import ceu.dam.ad.orm.practicas.biblioteca.model.Libro;
import ceu.dam.ad.orm.practicas.biblioteca.model.Prestamo;
import ceu.dam.ad.orm.practicas.biblioteca.model.Socio;
import ceu.dam.ad.orm.practicas.biblioteca.repository.LibroRepository;
import ceu.dam.ad.orm.practicas.biblioteca.repository.SocioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BibliotecaServiceImpl implements BibliotecaService {
	private final SocioRepository socioRepository;
	private final LibroRepository libroRepository;

	@Override
	@Transactional
	public Socio altaSocio(Socio socio) throws BibliotecaException {
		Optional<Socio> s = socioRepository.findById(socio.getDni());
		if (s.isPresent())
			throw new BibliotecaException("Ese socio ya existe");
		Carnet carnet = new Carnet();
		carnet.setSocio(socio);
		carnet.setFechaAlta(LocalDate.now());
		carnet.setFechaCaducidad(LocalDate.now().plusYears(1));
		socio.setCarnet(carnet);

		return socioRepository.save(socio);
	}

	@Override
	public Socio consultarSocio(String dni) throws NotFoundException {
		return socioRepository.findById(dni).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public Libro altaLibro(Libro libro) throws BibliotecaException {
		Optional<Libro> libroConsul = libroRepository.findByIsbn(libro.getIsbn())
		if (libroConsul.isPresent())
			throw new BibliotecaException("Ese libro ya existe");

		return libroRepository.save(libro);
	}

	@Override
	public List<Libro> buscarLibros(Libro ejemplo) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Libro> example = Example.of(ejemplo, matcher);
		return libroRepository.findAll(example);

	}
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

	@Override
	public Prestamo prestarLibro(String dniSocio, Long idLibro) throws NotFoundException, BibliotecaException {
		socioRepository.findById(dniSocio);
		
		
		return null;
	}

	@Override
	public void devolverLibro(Integer anio, Integer numPrestamo) throws NotFoundException, BibliotecaException {
		// TODO: IMPLEMENTAR POR EL ALUMNO...
	}

	@Override
	public Carnet renovarCarnet(String dniSocio) throws NotFoundException, BibliotecaException {
		return null;
		// TODO: IMPLEMENTAR POR EL ALUMNO...
	}

	@Override
	public List<Prestamo> consultarPrestamosActivos(String dniSocio) throws NotFoundException {
		return null;
		// TODO: IMPLEMENTAR POR EL ALUMNO...
	}

}
