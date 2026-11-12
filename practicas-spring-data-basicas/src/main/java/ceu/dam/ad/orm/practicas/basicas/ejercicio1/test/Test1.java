package ceu.dam.ad.orm.practicas.basicas.ejercicio1.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Episodio;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Serie;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Temporada;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.model.Trailer;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.service.SerieNotFoundException;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.service.SeriesService;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.service.SeriesServiceException;
import ceu.dam.ad.orm.practicas.basicas.ejercicio1.service.SeriesServiceImpl;

public class Test1 {

	public void test() {
		Serie serie = createTestSerie("Los lugares ocultos");
		
		SeriesService service = new SeriesServiceImpl();
		try {
			// Test crear
			serie = service.crearSerie(serie);
			System.out.println(" >>>> Serie creada con id: " + serie.getId());

			// Test consultar positivo
			serie = service.consultarSerie(serie.getId());
			System.out.println(" >>>> Consulta correcta");
			System.out.println(" >>>> Lista de temporadas: " + serie.getTemporadas());
			
			// Test actualizar
			serie.setDescripcion("Los lugares ocultos II");
			serie.getTemporadas().add(createTestTemporada(5));
			service.actualizarSerie(serie);
			System.out.println(" >>>> Actualización correcta");

			// Test buscar positivo
			List<Serie> series;
			try {
				series = service.buscarSeries("lugares");
			}
			catch(SerieNotFoundException e) {
				throw new RuntimeException("La búsqueda de series no es correcta");
			}
			System.out.println(" >>>> Consulta correcta");
			System.out.println(" >>>> Series encontradas: " + series.size());

			// Test buscar negativo
			try {
				series = service.buscarSeries("laura");
				throw new RuntimeException("La búsqueda de series no es correcta. No hay ninguna serie Laura. Debería lanzar Excepcion");
			}
			catch(SerieNotFoundException e) {
				System.out.println(" >>>> Consulta correcta");
				System.out.println(" >>>> Series encontradas: 0");
			}
			
			// Test eliminar
			service.elimnarSerie(serie.getId());
			System.out.println(" >>>> Serie eliminada correctamente.");
			
			// Test consultar negativo
			try {
				serie = service.consultarSerie(serie.getId());
				// La serie debería haber sido borrada y por tanto no existir. El servicio debería lanzar un SerieNotFoundException
				throw new RuntimeException("La consulta de serie no es correcta o el borrado no se ha hecho correctamente. ");
			}
			catch(SerieNotFoundException e) {
				System.out.println(" >>>> Consulta correcta");
			}

			
			
			
		} catch (SeriesServiceException e) {
			e.printStackTrace();
		} catch (SerieNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	private Serie createTestSerie(String titulo) {
		Serie serie = new Serie();
		serie.setDescripcion(titulo);
		serie.setEstreno(new Date());
		serie.setTrailer(new Trailer());
		serie.getTrailer().setDuracion(6);
		serie.setTemporadas(new ArrayList<>());
		
		for (int i = 1; i <= 4; i++) {
			Temporada temporada = createTestTemporada(i);
			serie.getTemporadas().add(temporada);
		}
		return serie;
	}
	
	private Temporada createTestTemporada(Integer numero) {
		Temporada temporada = new Temporada();
		temporada.setNumero(numero);
		temporada.setEpisodios(new ArrayList<>());
		for (int j = 1; j <= 7; j++) {
			Episodio e = new Episodio();
			e.setTitulo("Episodio " + j  + " de la temporada " + numero);
			temporada.getEpisodios().add(e);
		}
		return temporada;
	}

}
