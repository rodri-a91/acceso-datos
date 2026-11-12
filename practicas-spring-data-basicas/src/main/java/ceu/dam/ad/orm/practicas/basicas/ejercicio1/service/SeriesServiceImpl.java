package ceu.dam.ad.orm.practicas.basicas.ejercicio1.service;

import java.util.List;

import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Serie;

public class SeriesServiceImpl implements SeriesService {

	@Override
	public Serie consultarSerie(Long idSerie) throws SerieNotFoundException, SeriesServiceException {
		return null;
		// TODO: IMPLEMENTAR POR EL ALUMNO...
	}
	
	
	@Override
	public List<Serie> buscarSeries(String filtroDescripcion) throws SerieNotFoundException, SeriesServiceException {
		return null;
		// TODO: IMPLEMENTAR POR EL ALUMNO...
		
	}
	
	@Override
	public Serie crearSerie(Serie serie) throws SeriesServiceException {
		return null;
		// TODO: IMPLEMENTAR POR EL ALUMNO...
		
	}
	
	
	@Override
	public void elimnarSerie(Long idSerie) throws SeriesServiceException {
		// TODO: IMPLEMENTAR POR EL ALUMNO...
		
	}
	
	
	@Override
	public void actualizarSerie(Serie serie) throws SeriesServiceException {
		// TODO: IMPLEMENTAR POR EL ALUMNO...
		
	}
	
	
}
