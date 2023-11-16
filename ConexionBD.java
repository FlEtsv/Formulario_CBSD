import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static void main(String[] args) {
        // Establecer la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/ventas";
        String usuario = "root";
        String contraseña = "root1234";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            System.out.println("Conexión exitosa a la base de datos");

            // Realizar operaciones con la base de datos aquí

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // La conexión se cierra automáticamente al salir del bloque try-with-resources
    }
}
