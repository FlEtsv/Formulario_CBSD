import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainGUI extends JFrame {

    private JTextField nombreField, apellidoField, resultadoField, contraseñaField, recuperarContraseñaField;
    private JButton boton, botonRecuperar;

    public MainGUI() {
        // Configuración básica de la ventana
        setTitle("Formulario_CBSD");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un JPanel con layout nulo
        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(new Color(200, 255, 200)); // Color de fondo verde claro

        // Inicialización de componentes
        nombreField = new JTextField();
        apellidoField = new JTextField();
        resultadoField = new JTextField();
        boton = new JButton("Guardar");
        contraseñaField = new JTextField();
        recuperarContraseñaField = new JTextField();
        botonRecuperar = new JButton("Recuperar");

        // Configuración de colores y bordes si es necesario
        nombreField.setBackground(Color.WHITE);
        apellidoField.setBackground(Color.WHITE);

        // Establecer tamaño y posición manualmente
        nombreField.setBounds(90, 50, 150, 30);
        apellidoField.setBounds(90, 100, 150, 30);
        resultadoField.setBounds(90, 200, 300, 30);
        boton.setBounds(50, 150, 100, 30);
        contraseñaField.setBounds(100, 250, 250, 30);
        recuperarContraseñaField.setBounds(100, 350, 200, 30);
        botonRecuperar.setBounds(50, 400, 100, 30);

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

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setBounds(10, 250, 150, 30);
        panelPrincipal.add(contraseñaLabel);
        panelPrincipal.add(contraseñaField);

        JLabel recuperarDatosLabel = new JLabel("Obtener");
        recuperarDatosLabel.setBounds(10, 350, 200, 30);
        panelPrincipal.add(recuperarDatosLabel);
        panelPrincipal.add(recuperarContraseñaField);
        panelPrincipal.add(botonRecuperar);

        // Añadir el panelPrincipal a la ventana
        add(panelPrincipal);

        boton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            GeneradorRandomConSemillaNumerica generador = new GeneradorRandomConSemillaNumerica();
            long numeroAleatorioLargo = generador.generarNumeroAleatorioLargo();

            // Insertar datos en la base de datos
            try (ConexionBD conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/Formulario_CBSD", "root",
                    "root1234")) {
                conexionBD.insertarDatos("usuarios", nombre, apellido, numeroAleatorioLargo);
                resultadoField.setText("Datos guardados correctamente.");
                contraseñaField.setText("=" + numeroAleatorioLargo);
            } catch (SQLException ex) {
                ex.printStackTrace();
                resultadoField.setText("Error al guardar los datos.");
            }
        });

        botonRecuperar.addActionListener(i -> {
            String apellidosFuera = null;
            String nombreFuera = null;
            String recuperarSemillaString = recuperarContraseñaField.getText();

            try {
                Long recuperarSemilla = Long.parseLong(recuperarSemillaString);

                try (ConexionBD conexionBD2 = new ConexionBD("jdbc:mysql://localhost:3306/Formulario_CBSD", "root",
                        "root1234")) {
                    conexionBD2.recuperarDatos(recuperarSemilla);
                    nombreFuera = conexionBD2.obtenerRNombre();
                    apellidosFuera = conexionBD2.obtenerRApellidos();
                    resultadoField.setText("Nombre: " + nombreFuera + ", Apellido: " + apellidosFuera);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    resultadoField.setText("Error al recuperar los datos.");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                resultadoField.setText("Error: Ingrese una semilla válida.");
            }
        });

        // Mover setVisible(true) fuera del bloque try-catch
        setVisible(true);
    }

    public static void main(String[] args) {
        // Puedes probar la GUI creando una instancia de MainGUI
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainGUI extends JFrame {

    private JTextField nombreField, apellidoField, resultadoField, contraseñaField, recuperarContraseñaField;
    private JButton boton, botonRecuperar;

    public MainGUI() {
        // Configuración básica de la ventana
        setTitle("Formulario_CBSD");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un JPanel con layout nulo
        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(new Color(200, 255, 200)); // Color de fondo verde claro

        // Inicialización de componentes
        nombreField = new JTextField();
        apellidoField = new JTextField();
        resultadoField = new JTextField();
        boton = new JButton("Guardar");
        contraseñaField = new JTextField();
        recuperarContraseñaField = new JTextField();
        botonRecuperar = new JButton("Recuperar");

        // Configuración de colores y bordes si es necesario
        nombreField.setBackground(Color.WHITE);
        apellidoField.setBackground(Color.WHITE);

        // Establecer tamaño y posición manualmente
        nombreField.setBounds(90, 50, 150, 30);
        apellidoField.setBounds(90, 100, 150, 30);
        resultadoField.setBounds(90, 200, 300, 30);
        boton.setBounds(50, 150, 100, 30);
        contraseñaField.setBounds(100, 250, 250, 30);
        recuperarContraseñaField.setBounds(100, 350, 200, 30);
        botonRecuperar.setBounds(50, 400, 100, 30);

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

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setBounds(10, 250, 150, 30);
        panelPrincipal.add(contraseñaLabel);
        panelPrincipal.add(contraseñaField);

        JLabel recuperarDatosLabel = new JLabel("Obtener");
        recuperarDatosLabel.setBounds(10, 350, 200, 30);
        panelPrincipal.add(recuperarDatosLabel);
        panelPrincipal.add(recuperarContraseñaField);
        panelPrincipal.add(botonRecuperar);

        // Añadir el panelPrincipal a la ventana
        add(panelPrincipal);

        boton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            GeneradorRandomConSemillaNumerica generador = new GeneradorRandomConSemillaNumerica();
            long numeroAleatorioLargo = generador.generarNumeroAleatorioLargo();

            // Insertar datos en la base de datos
            try (ConexionBD conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/Formulario_CBSD", "root",
                    "root1234")) {
                conexionBD.insertarDatos("usuarios", nombre, apellido, numeroAleatorioLargo);
                resultadoField.setText("Datos guardados correctamente.");
                contraseñaField.setText("=" + numeroAleatorioLargo);
            } catch (SQLException ex) {
                ex.printStackTrace();
                resultadoField.setText("Error al guardar los datos.");
            }
        });

        botonRecuperar.addActionListener(i -> {
            String apellidosFuera = null;
            String nombreFuera = null;
            String recuperarSemillaString = recuperarContraseñaField.getText();

            try {
                Long recuperarSemilla = Long.parseLong(recuperarSemillaString);

                try (ConexionBD conexionBD2 = new ConexionBD("jdbc:mysql://localhost:3306/Formulario_CBSD", "root",
                        "root1234")) {
                    conexionBD2.recuperarDatos(recuperarSemilla);
                    nombreFuera = conexionBD2.obtenerRNombre();
                    apellidosFuera = conexionBD2.obtenerRApellidos();
                    resultadoField.setText("Nombre: " + nombreFuera + ", Apellido: " + apellidosFuera);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    resultadoField.setText("Error al recuperar los datos.");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                resultadoField.setText("Error: Ingrese una semilla válida.");
            }
        });

        // Mover setVisible(true) fuera del bloque try-catch
        setVisible(true);
    }

    public static void main(String[] args) {
        // Puedes probar la GUI creando una instancia de MainGUI
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
