import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
class ConexionBD implements AutoCloseable {

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

    // Método para hacer la inserción de datos
    public void insertarDatos(String tabla, String nombre, String apellido, int semilla) {
        try (PreparedStatement preparedStatement = conexion.prepareStatement(
                "INSERT INTO " + tabla + " (nombre, apellido, semilla) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setInt(3, semilla);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("La inserción no tuvo éxito");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        cerrarConexion();
    }
}