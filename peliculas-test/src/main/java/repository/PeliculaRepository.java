package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pelicula;

public class PeliculaRepository {
	
	public List<Pelicula> findAll() throws SQLException{
		List<Pelicula> lista = new ArrayList<>();
		try(Connection conn = ConexionBD.getConexion()) {
			String sql = "SELECT * FROM PELICULAS";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pelicula p = new Pelicula();
				p.setId(rs.getLong("id"));
				p.setTitulo(rs.getString("titulo"));
				p.setDirector(rs.getString("director"));
				p.setDuracion(rs.getInt("duracion"));
				p.setAñoEstreno(rs.getInt("año_estreno"));
				
				lista.add(p);
			}
			
			return lista;
			
		}
		
	}

}
