import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {
/**
 *         public static void main(String[] args) {
        // Ejemplo de uso
        ConexionBD conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/Formulario_CBSD", "root", "root1234");
        

        // Insertar datos en la tabla "nombre_de_la_tabla"
        conexionBD.insertarDatos("usuarios", "OLAYA","BENAMEUR",4);

        // Cerrar la conexión B
        conexionBD.cerrarConexion();
    }
 */
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
    public void insertarDatos(String tabla, String nombre, String apellido, int semilla) {
        PreparedStatement preparedStatement = null;

        try {
            // Consulta SQL parametrizada para la inserción de datos
            String consulta = "INSERT INTO " + tabla + " (nombre, apellido, semilla) VALUES (?, ?, ?)";

            // Crear un objeto PreparedStatement
            preparedStatement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setInt(3, semilla);

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