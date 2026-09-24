package ceu.dam.ad.users.services;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Service {
	public Connection abrirConexion() throws SQLException {
		String url = "jdbc:mariadb://localhost:3306/blas-bd";
		String user = "blas";
		String pass = "blas";
		return abrirConexion(url, user, pass);
	}
	

	private Connection abrirConexion(String url, String user, String pass) throws SQLException {
		String driver = "org.mariadb.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra la clase del driver. Revisa tu configuración");
			throw new RuntimeException("No se encuentra clase del driver", e);
		}
		return DriverManager.getConnection(url, user, pass);
	}

}
