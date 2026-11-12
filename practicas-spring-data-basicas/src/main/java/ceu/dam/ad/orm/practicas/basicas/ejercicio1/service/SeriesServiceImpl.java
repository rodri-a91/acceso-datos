package ceu.dam.ad.orm.practicas.basicas.ejercicio1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.orm.practicas.basicas.ejercicio1.dao.SerieRepository;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Serie;
import jakarta.transaction.Transactional;

@Service
public class SeriesServiceImpl implements SeriesService {
	private final SerieRepository repo;

	public SeriesServiceImpl(SerieRepository repo) {
		this.repo = repo;
	}

	@Override
	public Serie consultarSerie(Long idSerie) throws SerieNotFoundException, SeriesServiceException {
		try {
			Optional<Serie> serieConsul = repo.findById(idSerie);

			if (serieConsul.isEmpty()) {
				throw new SerieNotFoundException("Esta serie no existe");
			}

			return serieConsul.get();

		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error con la base de datos", e);
		}

	}

	@Override
	public List<Serie> buscarSeries(String filtroDescripcion) throws SerieNotFoundException, SeriesServiceException {
		try {

			List<Serie> series = repo.findByDescripcionContaining(filtroDescripcion);

			if (series.isEmpty()) {
				throw new SerieNotFoundException("No existe ninguna serie con esa descripción");

			}

			return series;
		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error con la base de datos", e);

		}

	}

	@Override
	@Transactional
	public Serie crearSerie(Serie serie) throws SeriesServiceException {
		try {
			return repo.save(serie);

		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error con la base de datos", e);

		}

	}

	@Override
	@Transactional
	public void eliminarSerie(Long idSerie) throws SeriesServiceException {
		try {

			repo.deleteById(idSerie);

		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error con la base de datos", e);

		}

	}

	@Override
	@Transactional
	public void actualizarSerie(Serie serie) throws SeriesServiceException {
		try {
			repo.save(serie);

		} catch (DataAccessException e) {
			throw new SeriesServiceException("Error con la base de datos", e);

		}

	}

}
