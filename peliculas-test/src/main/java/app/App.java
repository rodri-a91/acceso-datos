package app;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import model.Pelicula;
import service.PeliculaService;

public class App {

	public static void main(String[] args) {
		PeliculaService service = new PeliculaService();
		try {
			List<Pelicula> peliculas = service.findAll();

			peliculas.forEach(System.out::println);

//			for (Pelicula pelicula : peliculas) {
//				System.out.println(pelicula);
//			}
			
//			Generación de hash
			String entrada = "blas";
			String hash = DigestUtils.sha512_256Hex(entrada);
			System.out.println(hash);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
