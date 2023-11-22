import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class mainGUI extends JFrame {

    private JTextField nombreField, apellidoField, resultadoField;
    private JButton boton;

    public mainGUI() {
        // Configuración básica de la ventana
        setTitle("Formulario_CBSD");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un JPanel con fondo verde y layout nulo
        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(new Color(200, 255, 200)); // Color de fondo verde claro

        // Inicialización de componentes
        nombreField = new JTextField();
        apellidoField = new JTextField();
        resultadoField = new JTextField();  // Declaración de resultadoField
        boton = new JButton("Aceptar");

        // Configuración de colores y bordes si es necesario
        nombreField.setBackground(Color.WHITE);
        apellidoField.setBackground(Color.WHITE);

        // Establecer tamaño y posición manualmente
        nombreField.setBounds(90, 50, 150, 30);
        apellidoField.setBounds(90, 100, 150, 30);
        resultadoField.setBounds(90, 200, 300, 30);
        boton.setBounds(50, 150, 100, 30);

        // Añadir componentes al panel
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 50, 70, 30);
        panelPrincipal.add(nombreLabel);
        panelPrincipal.add(nombreField);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(10, 100, 70, 30);
        panelPrincipal.add(apellidoLabel);
        panelPrincipal.add(apellidoField);

        panelPrincipal.add(new JLabel("Resultado:"));
        panelPrincipal.add(resultadoField);
        panelPrincipal.add(boton);

        // Añadir el panelPrincipal a la ventana
        add(panelPrincipal);

        boton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();

            // Insertar datos en la base de datos
            try (ConexionBD conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/Formulario_CBSD", "root", "root1234")) {
                conexionBD.insertarDatos("usuarios", nombre, apellido, 1);
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Manejar la excepción apropiadamente (mostrar un mensaje de error, etc.)
            }

            // Concatenar los valores recibidos y mostrarlos en el JTextField de resultado
            resultadoField.setText("Nombre: " + nombre + ", Apellido: " + apellido);
        });

        // Mover setVisible(true) fuera del bloque try-catch
        setVisible(true);
    }

    public static void main(String[] args) {
        // Puedes probar la GUI creando una instancia de MainGUI
        SwingUtilities.invokeLater(mainGUI::new);
    }
}




