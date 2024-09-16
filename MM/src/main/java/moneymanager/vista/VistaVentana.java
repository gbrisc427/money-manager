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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // ETIQUETA NOMBRE DE LA CUENTA
        /*
        CAMBIARLO POR UN BOTÓN QUE AL HACERLO CLICK ENTRE EN EL MENU DE PODER MODIFICAR LA CUENTA.
         */
        String nombreCuenta = CM.getNombreCuenta();
        JLabel etiquetaNombreCuenta = new JLabel("ACCOUNT", SwingConstants.CENTER);
        etiquetaNombreCuenta.setFont(new Font("Lexend", Font.BOLD, 25));
        etiquetaNombreCuenta.setBorder(new EmptyBorder(25,15,0,15));
        panel.add(etiquetaNombreCuenta, BorderLayout.NORTH);

        // ETIQUETA CON EL SALDO DE LA CUENTA
        String saldo = CM.getSaldo();
        JLabel etiquetaSaldo = new JLabel(saldo, SwingConstants.CENTER);
        etiquetaSaldo.setFont(new Font("Lexend", Font.BOLD, 70));
        panel.add(etiquetaSaldo, BorderLayout.CENTER);



        // MENU
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color( 164, 227, 111 ));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(173, 0));


        // BOTON REALIZAR OPERACION
        JButton boton = new JButton("REALIZAR OPERACIÓN");
        menuPanel.add(boton);

        JButton boton2 = new JButton("VER HISTORIAL");
        menuPanel.add(boton2);

        JButton boton3 = new JButton("CUENTAS");
        menuPanel.add(boton3);


        // Evento de acción para el botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes llamar a la lógica de negocio o cambiar la ventana
                JOptionPane.showMessageDialog(null, "Botón presionado");
            }
        });


        this.add(menuPanel, BorderLayout.WEST); // Menú en el lado izquierdo
        this.add(panel, BorderLayout.CENTER); // Contenido principal en el centro

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void cambiarEstilo(){
        UIManager.put("Button.background", new Color( 164, 227, 111 ));
        UIManager.put("Button.foreground", new Color(253,242,240));
        UIManager.put("Button.font", new Font("Lexend", Font.BOLD, 12));
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(15, 15, 15,15));
        UIManager.put("Button.focus", new Color(0,0,0,0));
        UIManager.put("Button.select", new Color( 102, 209, 180 ));
        UIManager.put("Button.foreground", new Color(253,242,240));

        UIManager.put("Panel.background", new Color(253,242,240));
    }


    public static void main(String[] args) {
        cambiarEstilo();
        VistaVentana ventana = new VistaVentana();
    }
}
