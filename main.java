import java.util.Scanner;
public class main {
        public static void main(String[] args) {
        // Ejemplo de uso
        ConexionBD conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/Formulario_CBSD", "root", "root1234");
        Scanner nameScanner = new Scanner(System.in);

        // Insertar datos en la tabla "nombre_de_la_tabla"
        conexionBD.insertarDatos("usuarios", "joel",1, "Francisco");

        // Cerrar la conexión BD
        conexionBD.cerrarConexion();
    }
}

