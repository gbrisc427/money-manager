package moneymanager.vista;


import moneymanager.business.Cuenta;
import moneymanager.business.CuentaManager;
import moneymanager.business.LimitDocument;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class VistaVentana extends JFrame {

    private static VistaVentana instancia = null;
    public static volatile boolean ventanaAbierta = true;

    public static VistaVentana getInstancia() {
        if (instancia == null) {
            cambiarEstilo();
            instancia = new VistaVentana();
        }
        return instancia;
    }

    private VistaVentana() {

        CuentaManager CM = CuentaManager.getInstancia();
        Cuenta cuenta = CM.getCuentaActual();

        setTitle("");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // BOTÓN NOMBRE DE LA CUENTA
        String nombreCuenta = CM.getNombreCuenta();
        System.out.println(nombreCuenta);
        JButton botonNombreCuenta = new JButton(nombreCuenta);
        botonNombreCuenta.setFont(new Font("Lexend", Font.BOLD, 30));
        botonNombreCuenta.setBorder(new EmptyBorder(25, 15, 0, 15));
        botonNombreCuenta.setForeground(new Color(164, 227, 111));
        botonNombreCuenta.setBackground(new Color(253, 242, 240));
        panelPrincipal.add(botonNombreCuenta, BorderLayout.NORTH);


        // ETIQUETA CON EL SALDO DE LA CUENTA
        String saldo = CM.getSaldo();
        JLabel etiquetaSaldo = new JLabel(saldo, SwingConstants.CENTER);
        etiquetaSaldo.setFont(new Font("Lexend", Font.BOLD, 70));
        etiquetaSaldo.setBorder(new EmptyBorder(0, 15, 50, 15));
        panelPrincipal.add(etiquetaSaldo, BorderLayout.CENTER);


        // MENU
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(164, 227, 111));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(173, 0));


        // BOTONES MENÚ
        JButton realizarOperacion = new JButton("REALIZAR OPERACIÓN");
        menuPanel.add(realizarOperacion);

        JButton verHistorial = new JButton("VER HISTORIAL");
        menuPanel.add(verHistorial);

        JButton consultarCuentas = new JButton("CUENTAS");
        menuPanel.add(consultarCuentas);

        this.add(menuPanel, BorderLayout.WEST); // Menú en el lado izquierdo
        this.add(panelPrincipal, BorderLayout.CENTER); // Contenido principal en el centro

        setVisible(true);

        // PANEL MODIFICAR CUENTA
        JPanel panelModificarCuenta = new JPanel();
        panelModificarCuenta.setLayout(new BorderLayout());
        panelModificarCuenta.setVisible(false);

        // BOTÓN2 NOMBRE DE LA CUENTA
        JButton botonNombreCuenta2 = new JButton(nombreCuenta);
        botonNombreCuenta2.setFont(new Font("Lexend", Font.BOLD, 30));
        botonNombreCuenta2.setBorder(new EmptyBorder(25, 15, 0, 15));
        botonNombreCuenta2.setForeground(new Color(164, 227, 111));
        botonNombreCuenta2.setBackground(new Color(253, 242, 240));
        panelModificarCuenta.add(botonNombreCuenta2, BorderLayout.NORTH);

        //BOTON ELIMINAR CUENTA
        JButton botonEliminarCuenta = new JButton("ELIMINAR CUENTA");
        botonEliminarCuenta.setFont(new Font("Lexend", Font.BOLD, 15));
        botonEliminarCuenta.setBorder(new EmptyBorder(25, 15, 25, 15));
        botonEliminarCuenta.setForeground(new Color(227, 111, 111));
        botonEliminarCuenta.setBackground(new Color(253, 242, 240));
        panelModificarCuenta.add(botonEliminarCuenta, BorderLayout.SOUTH);


        // CONTENIDO PANEL CAMBIAR NOMBRE
        JPanel cambioNombre = new JPanel();
        cambioNombre.setLayout(new FlowLayout(FlowLayout.LEFT));
        cambioNombre.setVisible(true);

        JLabel etiquetaCambiarNombre = new JLabel("CAMBIAR NOMBRE:", SwingConstants.LEFT);
        etiquetaCambiarNombre.setFont(new Font("Lexend", Font.BOLD, 20));
        etiquetaCambiarNombre.setBorder(new EmptyBorder(25, 0, 15, 15));
        etiquetaCambiarNombre.setForeground(new Color(164, 227, 111));
        cambioNombre.add(etiquetaCambiarNombre);

        JTextField nuevoNombre = new JTextField(20);
        nuevoNombre.setFont(new Font("Lexend", Font.BOLD, 12));
        nuevoNombre.setBorder(new EmptyBorder(10, 10, 10, 10));
        nuevoNombre.setForeground(new Color(253, 242, 240));
        nuevoNombre.setBackground(new Color(164, 227, 111));
        nuevoNombre.setDocument(new LimitDocument(30));
        cambioNombre.add(nuevoNombre);

        JButton botonGuardarNombre = new JButton("GUARDAR");
        botonGuardarNombre.setBorder(new EmptyBorder(10, 10, 10, 10));
        botonGuardarNombre.setForeground(new Color(253, 242, 240));
        cambioNombre.add(botonGuardarNombre);

        JLabel infoCambioNombre = new JLabel("<html>PORFAVOR INTRODUCE UN NOMBRE <br> PARA GUARDAR LOS CAMBIOS</html>");
        infoCambioNombre.setFont(new Font("Lexend", Font.BOLD, 12));
        infoCambioNombre.setBorder(new EmptyBorder(15, 0, 15, 15));
        infoCambioNombre.setForeground(new Color(164, 227, 111));
        infoCambioNombre.setVisible(false);
        cambioNombre.add(infoCambioNombre);

        cambioNombre.setBorder(new EmptyBorder(10, 30, 20, 20));

        panelModificarCuenta.add(cambioNombre, BorderLayout.CENTER);


        // VENTANA
        this.add(menuPanel, BorderLayout.WEST); // Menú en el lado izquierdo
        this.add(panelPrincipal, BorderLayout.CENTER); // Contenido principal en el centro
        this.add(panelModificarCuenta, BorderLayout.CENTER);

        // CONTROL DEL CIERRE DE LA VENTANA
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                VistaVentana.this.dispose();
                ventanaAbierta = false;
            }
        });

        // FUNCIONES BOTONES
        botonNombreCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPrincipal.setVisible(false);
                panelModificarCuenta.setVisible(true);
            }
        });

        botonNombreCuenta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPrincipal.setVisible(true);
                panelModificarCuenta.setVisible(false);
            }
        });

        botonGuardarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textField = nuevoNombre.getText().toUpperCase();
                if (!textField.isEmpty()) {
                    panelPrincipal.setVisible(true);
                    panelModificarCuenta.setVisible(false);
                    infoCambioNombre.setVisible(false);
                    if (cuenta != null) {
                        CM.modificarCuenta(textField);
                        nuevoNombre.setText("");
                    }
                    botonNombreCuenta.setText(textField);
                    botonNombreCuenta2.setText(textField);
                    nuevoNombre.setText("");
                } else {
                    infoCambioNombre.setVisible(true);
                }
            }
        });

        botonEliminarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPopUpEliminarCuenta(VistaVentana.this);
                panelPrincipal.setVisible(true);
                panelModificarCuenta.setVisible(false);
            }
        });

    }

    private static void cambiarEstilo() {
        UIManager.put("Button.background", new Color(164, 227, 111));
        UIManager.put("Button.foreground", new Color(253, 242, 240));
        UIManager.put("Button.font", new Font("Lexend", Font.BOLD, 12));
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 15, 10, 15));
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.select", new Color(253, 242, 240));
        UIManager.put("Button.foreground", new Color(35, 33, 33));
        UIManager.put("Panel.background", new Color(253, 242, 240));
    }

    private void mostrarPopUpEliminarCuenta(JFrame parentFrame) {
        CuentaManager CM = CuentaManager.getInstancia();

        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(275, 200);
        dialogo.setLayout(new FlowLayout());

        JLabel mensaje = new JLabel("<html>PARA ELIMINAR LA CUENTA <br> ESCRIBE \"CONFIRMAR\".</html>");
        mensaje.setFont(new Font("Lexend", Font.BOLD, 15));
        mensaje.setBorder(new EmptyBorder(25, 15, 15, 15));
        mensaje.setForeground(new Color(227, 111, 111));
        dialogo.add(mensaje);

        JTextField confirmar = new JTextField(8);
        confirmar.setFont(new Font("Lexend", Font.BOLD, 12));
        confirmar.setBorder(new EmptyBorder(10, 25, 10, 25));
        confirmar.setForeground(new Color(253, 242, 240));
        confirmar.setBackground(new Color(227, 111, 111));
        confirmar.setDocument(new LimitDocument(9));
        confirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = confirmar.getText();
                if (text.equals("CONFIRMAR")) {
                    dialogo.dispose();
                    CM.eliminarCuenta();
                }
            }
        });
        dialogo.add(confirmar);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }


}