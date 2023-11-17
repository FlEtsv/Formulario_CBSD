import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {

    private Connection conexion;

    // Constructor: Establecer la conexión a la base de datos
    public ConexionBD(String url, String usuario, String contraseña) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para hacerla inserción de datos
    public void insertarDatos(String tabla, String columna1, int columna2, String columna3) {
        PreparedStatement preparedStatement = null;

        try {
            // Consulta SQL parametrizada para la inserción de datos
            String consulta = "INSERT INTO " + tabla + " (columna1, columna2, columna3) VALUES (?, ?, ?)";

            // Crear un objeto PreparedStatement
            preparedStatement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, columna1);
            preparedStatement.setInt(2, columna2);
            preparedStatement.setString(3, columna3);

            // Ejecutar la consulta de inserción
            int filasAfectadas = preparedStatement.executeUpdate();

            // Comprobar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("La inserción no tuvo éxito");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el recurso PreparedStatement en el bloque finally
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}