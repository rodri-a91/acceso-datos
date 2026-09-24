package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Pelicula;
import repository.PeliculaRepository;

public class PeliculaService {

	private PeliculaRepository repo;

	public PeliculaService() {
		repo = new PeliculaRepository();
	}

	public List<Pelicula> findAll() throws SQLException {
		List<Pelicula> peliculas = repo.findAll();
//		for (Pelicula pelicula : peliculas) {
//			Integer edad = LocalDate.now().getYear() - pelicula.getAñoEstreno();
//			pelicula.setEdad(edad);
//		}

		peliculas.forEach(p -> p.setEdad(LocalDate.now().getYear() - p.getAñoEstreno()));

		return peliculas;

	}

	public List<String> findAllTittleByYear(Integer year) throws SQLException {
		List<Pelicula> peliculas = repo.findAll();
		
		return peliculas.stream()
					.filter(p -> p.getAñoEstreno().equals(year))
					.map(p -> p.getTitulo())
					.toList();


	}

}
