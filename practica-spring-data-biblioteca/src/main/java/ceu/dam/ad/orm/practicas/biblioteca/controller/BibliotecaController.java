package ceu.dam.ad.orm.practicas.biblioteca.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.orm.practicas.biblioteca.dto.request.LibroRequestAltaDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.request.SocioRequestDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.response.CarnetResponseRenovarDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.response.LibroResponseAltaDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.response.LibroResponseBuscarDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.response.PrestamoResponseActivosDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.response.PrestamoResponsePrestarDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.response.SocioResponseAltaDto;
import ceu.dam.ad.orm.practicas.biblioteca.dto.response.SocioResponseConsultaDto;
import ceu.dam.ad.orm.practicas.biblioteca.model.Carnet;
import ceu.dam.ad.orm.practicas.biblioteca.model.Libro;
import ceu.dam.ad.orm.practicas.biblioteca.model.Prestamo;
import ceu.dam.ad.orm.practicas.biblioteca.model.Socio;
import ceu.dam.ad.orm.practicas.biblioteca.service.BibliotecaException;
import ceu.dam.ad.orm.practicas.biblioteca.service.BibliotecaService;
import ceu.dam.ad.orm.practicas.biblioteca.service.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/biblioteca")
@RequiredArgsConstructor
public class BibliotecaController {
	final BibliotecaService service;
	final ModelMapper mapper;

	@PostMapping("/socio")
	public SocioResponseAltaDto altaSocio(@Valid @RequestBody SocioRequestDto socio) throws BibliotecaException {
		Socio socAlta = service.altaSocio(mapper.map(socio, Socio.class));
		SocioResponseAltaDto socDevuel = mapper.map(socAlta, SocioResponseAltaDto.class);
		socDevuel.setId(socAlta.getCarnet().getId());
		return socDevuel;

	}

	@GetMapping("/{dni}")
	public SocioResponseConsultaDto consultarSocio(@PathVariable String dni) throws NotFoundException {

		return mapper.map(service.consultarSocio(dni), SocioResponseConsultaDto.class);
	}

	@PostMapping("/libro")
	public LibroResponseAltaDto altaLibro(@Valid @RequestBody LibroRequestAltaDto libro) throws BibliotecaException {
		Libro libroAlta = service.altaLibro(mapper.map(libro, Libro.class));

		return mapper.map(libroAlta, LibroResponseAltaDto.class);
	}

	@GetMapping("/libro")
	public List<LibroResponseBuscarDto> buscarLibros(@RequestParam String titulo, @RequestParam String isbn,
			@RequestParam String autor, @RequestParam String genero, @RequestParam Integer ejemplaresDisponibles) {
		Libro ejemplo = new Libro();
		ejemplo.setAutor(autor);
		ejemplo.setTitulo(titulo);
		ejemplo.setIsbn(isbn);
		ejemplo.setGenero(genero);
		ejemplo.setEjemplaresDisponibles(ejemplaresDisponibles);

		return service.buscarLibros(ejemplo).stream().map(i -> mapper.map(i, LibroResponseBuscarDto.class)).toList();
	}

	@PutMapping("/libro/{idLibro}")
	public PrestamoResponsePrestarDto prestarLibro(@RequestParam String dniSocio, @PathVariable Long idLibro)
			throws NotFoundException, BibliotecaException {
		Prestamo libroPrestado = service.prestarLibro(dniSocio, idLibro);

		return mapper.map(libroPrestado, PrestamoResponsePrestarDto.class);
	}

	@PutMapping("/prestamo/{anio}/{numPrestamo}")
	public void devolverLibro(Integer anio, Integer numPrestamo) throws NotFoundException, BibliotecaException {
		service.devolverLibro(anio, numPrestamo);
	}

	@PutMapping("/socio/{dniSocio}/carnet}")
	public CarnetResponseRenovarDto renovarCarnet(String dniSocio) throws NotFoundException, BibliotecaException {
		Carnet carnet = service.renovarCarnet(dniSocio);

		return mapper.map(carnet, CarnetResponseRenovarDto.class);
	}

	@GetMapping("/socio/{dniSocio}/prestamos")
	public List<PrestamoResponseActivosDto> consultarPrestamosActivos(String dniSocio) throws NotFoundException {
		List<Prestamo> prestamos = service.consultarPrestamosActivos(dniSocio);
		return prestamos.stream().map(p -> mapper.map(prestamos, PrestamoResponseActivosDto.class)).toList();
	}

}
