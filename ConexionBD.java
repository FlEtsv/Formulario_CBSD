import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private String rNombre;
    private String rApellidos;

    public String obtenerRNombre() {
        return rNombre;
    }

    public String obtenerRApellidos() {
        return rApellidos;
    }

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
                System.err.println(e);
            }
        }
    }

    // Método para hacer la inserción de datos
    public void insertarDatos(String tabla, String nombre, String apellido, long semilla) {
        try (PreparedStatement preparedStatement = conexion.prepareStatement(
                "INSERT INTO " + tabla + " (nombre, apellido, semilla) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setLong(3, semilla);

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




    //metodo para buscar en la base de datos la semilla y recuperar los nombres y apellidos
    public void recuperarDatos(long semilla){
    String consultaSQL = "SELECT nombre, apellido FROM usuarios WHERE semilla = ? ";

    try (PreparedStatement conexion1Statement = conexion.prepareStatement(consultaSQL)) {
        conexion1Statement.setLong(1, semilla);
        try (ResultSet resultSet = conexion1Statement.executeQuery()) {
            while (resultSet.next()) {
                 rNombre = resultSet.getString(1);
                 rApellidos = resultSet.getString(2);

                System.out.println("Nombre: " + rNombre + " " + rApellidos);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime la traza de la excepción para depuración
        System.err.println("Error SQL: " + e.getErrorCode() + ", Mensaje: " + e.getMessage());
    }
}

}
