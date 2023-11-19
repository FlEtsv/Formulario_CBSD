import java.awt.*;
import javax.swing.*;

public class mainGUI extends JFrame {

    private JTextField pantalla; // Corregido el nombre de la clase JTextField

    private String nombre, apellido;

    public mainGUI() {
        // Configuración básica de la ventana
        setTitle("Formulario_CBSD");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicialización de componentes
        pantalla = new JTextField();
        pantalla.setEditable(false);
        add(pantalla, BorderLayout.NORTH);

        // Resto de la configuración de la interfaz gráfica aquí

        setVisible(true);
    }

    public static void main(String[] args) {
        // Puedes probar la GUI creando una instancia de MainGUI
        SwingUtilities.invokeLater(() -> new mainGUI());
    }
}

