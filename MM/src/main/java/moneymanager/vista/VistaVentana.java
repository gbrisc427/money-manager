package moneymanager.vista;


import moneymanager.business.Cuenta;
import moneymanager.business.CuentaManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VistaVentana extends JFrame {


    public VistaVentana() {

        CuentaManager CM = CuentaManager.getInstancia();
        Cuenta cuenta = CM.getCuentaActual();

        setTitle("");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // BOTÓN NOMBRE DE LA CUENTA
        String nombreCuenta = CM.getNombreCuenta();
        JButton botonNombreCuenta = new JButton("ACCOUNT");
        botonNombreCuenta.setFont(new Font("Lexend", Font.BOLD, 30));
        botonNombreCuenta.setBorder(new EmptyBorder(25,15,0,15));
        botonNombreCuenta.setForeground(new Color(164, 227, 111 ));
        botonNombreCuenta.setBackground(new Color(253,242,240));
        panelPrincipal.add(botonNombreCuenta, BorderLayout.NORTH);


        // ETIQUETA CON EL SALDO DE LA CUENTA
        String saldo = CM.getSaldo();
        JLabel etiquetaSaldo = new JLabel(saldo, SwingConstants.CENTER);
        etiquetaSaldo.setFont(new Font("Lexend", Font.BOLD, 70));
        etiquetaSaldo.setBorder(new EmptyBorder(0,15,50,15));
        panelPrincipal.add(etiquetaSaldo, BorderLayout.CENTER);



        // MENU
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color( 164, 227, 111 ));
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

        // ETIQUETA CUENTA
        JLabel etiquetaNombreCuenta = new JLabel("ACCOUNT", SwingConstants.CENTER);
        etiquetaNombreCuenta.setFont(new Font("Lexend", Font.BOLD, 30));
        etiquetaNombreCuenta.setBorder(new EmptyBorder(25,15,15,15));
        etiquetaNombreCuenta.setForeground(new Color(164, 227, 111 ));
        panelModificarCuenta.add(etiquetaNombreCuenta, BorderLayout.NORTH);

        // CONTENIDO PANEL

        // CAMBIO NOMBRE

        JPanel cambioNombre = new JPanel();
        cambioNombre.setLayout(new BorderLayout());
        cambioNombre.setVisible(true);



        JLabel etiquetaCambiarNombre = new JLabel("CAMBIAR NOMBRE:", SwingConstants.LEFT);
        etiquetaCambiarNombre.setFont(new Font("Lexend", Font.BOLD, 15));
        etiquetaCambiarNombre.setBorder(new EmptyBorder(25,15,15,15));
        etiquetaCambiarNombre.setForeground(new Color(164, 227, 111 ));
        cambioNombre.add(etiquetaCambiarNombre, BorderLayout.NORTH);

        JTextField nuevoNombre = new JTextField(20);
        nuevoNombre.setToolTipText("NUEVO NOMBRE AQUÍ");
        nuevoNombre.setFont(new Font("Lexend", Font.BOLD, 15));
        nuevoNombre.setBorder(null);
        nuevoNombre.setForeground(new Color(253,242,240));
        nuevoNombre.setBackground(new Color(164, 227, 111 ));
        cambioNombre.add(nuevoNombre, BorderLayout.CENTER);

        cambioNombre.setBorder(new EmptyBorder(20,20,20,20));

        panelModificarCuenta.add(cambioNombre, BorderLayout.CENTER);


        // VENTANA

        this.add(menuPanel, BorderLayout.WEST); // Menú en el lado izquierdo
        this.add(panelPrincipal, BorderLayout.CENTER); // Contenido principal en el centro
        this.add(panelModificarCuenta, BorderLayout.CENTER);


        // FUNCIONES BOTONES

        botonNombreCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPrincipal.setVisible(false);
                panelModificarCuenta.setVisible(true);
            }
        });

    }

    private static void cambiarEstilo(){
        UIManager.put("Button.background", new Color( 164, 227, 111 ));
        UIManager.put("Button.foreground", new Color(253,242,240));
        UIManager.put("Button.font", new Font("Lexend", Font.BOLD, 12));
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 15, 10,15));
        UIManager.put("Button.focus", new Color(0,0,0,0));
        UIManager.put("Button.select", new Color( 253,242,240));
        UIManager.put("Button.foreground", new Color(35, 33, 33));
        UIManager.put("Panel.background", new Color(253,242,240));
    }


    public static void main(String[] args) {
        cambiarEstilo();
        VistaVentana ventana = new VistaVentana();
    }
}
