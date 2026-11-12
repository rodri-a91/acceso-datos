package ceu.dam.ad.orm.practicas.basicas.ejercicio1.service;

import java.util.List;

import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Serie;

public interface SeriesService {

	/** Debe crear la serie y todas sus entidades asociadas en bbdd. Si hay algún error, lanzará 
	 * SeriesServiceException. 
	 * Devolverá la serie creada con todos sus datos completos.
	 */
	public Serie crearSerie(Serie serie) throws SeriesServiceException;

	/** Consulta la serie con el id indicado por parámetro y la devuelve. Tiene que incluir todas sus entidades 
	 * asociadas. Si la serie no existe, se lanzará SerieNotFoundException. Si hay cualquier otro error, se
	 * lanzará SeriesServiceException
	 */
	public Serie consultarSerie(Long idSerie) throws SerieNotFoundException, SeriesServiceException;


	/** Consultará todas las series que contengan en su descripción la palabra indicada en el filtro por
	 * parámetro. Si no se encuentra ninguna, lanzará SerieNotFoundException. Si hay cualquier otro error, se
	 * lanzará SeriesServiceException
	 */
	public List<Serie> buscarSeries(String filtroDescripcion) throws SerieNotFoundException, SeriesServiceException;


	/** Debe eliminar la serie con el id indicado, y todas sus entidades asociadas de bbdd. 
	 * Si hay algún error, lanzará SeriesServiceException.
	 */
	public void elimnarSerie(Long idSerie) throws SeriesServiceException;

	/** Actualizará la serie que se pasa por parámetro y todas las entidades asociadas que estén modificadas. 
	 * Si hay algún error, lanzará SeriesServiceException
	 */
	public void actualizarSerie(Serie serie) throws SeriesServiceException;

}