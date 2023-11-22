import java.awt.*;
import javax.swing.*;

public class mainGUI extends JFrame {

    private JTextField nombreField, apellidoField;
    private JButton boton;

    public mainGUI(){
        // Configuración básica de la ventana
        setTitle("Formulario_CBSD");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un JPanel con fondo verde
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(200, 255, 200)); // Color de fondo verde claro
        panelPrincipal.setLayout(new GridLayout(3, 2)); // GridLayout para organizar los componentes

        // Inicialización de componentes
        nombreField = new JTextField();
        apellidoField = new JTextField();
        boton = new JButton("Aceptar");

        // Configuración de colores y bordes si es necesario
        nombreField.setBackground(Color.WHITE);
        apellidoField.setBackground(Color.WHITE);

        //Establecer tamaño preferido para nombreField
        nombreField.setPreferredSize(new Dimension(50, 30));

        // Añadir componentes al panel
        panelPrincipal.add(new JLabel("Nombre:"));
        panelPrincipal.add(nombreField);
        panelPrincipal.add(new JLabel("Apellido:"));
        panelPrincipal.add(apellidoField);
        panelPrincipal.add(boton); // El botón se agrega en la tercera fila

        // Añadir el panelPrincipal a la ventana
        add(panelPrincipal);

        // Resto de la configuración de la interfaz gráfica aquí
        // ...

        setVisible(true);
    }

    public static void main(String[] args) {
        // Puedes probar la GUI creando una instancia de MainGUI
        SwingUtilities.invokeLater(() -> new mainGUI());
    }
}