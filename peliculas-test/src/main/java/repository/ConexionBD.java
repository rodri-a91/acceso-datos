package repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Configuración de la conexión
    private static final String URL = "jdbc:mariadb://localhost:3306/blas-db";
    private static final String USER = "blas";
    private static final String PASSWORD = "blas";

    /**
     * Método que establece y devuelve la conexión con MariaDB
     * @return Connection objeto de conexión o null si falla
     */
    public static Connection getConexion() {
        Connection conexion = null;
        try {
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a MariaDB realizada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    // Ejemplo rápido de prueba
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getConexion()) {
            if (conn != null) {
                System.out.println("¡Conexión lista para usar!");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}