package moneymanager.vista.paneles;

import moneymanager.vista.VistaVentana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelMenu extends JPanel implements Panel {

    private static panelMenu instancia = null;

    public static panelMenu getInstancia() {
        if (instancia == null) {
            instancia = new panelMenu();
        }
        return instancia;
    }

    private panelMenu(){

        this.setBackground(new Color(164, 227, 111));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(173, 0));

        JButton BOTON_REALIZAR_OPERACION = new JButton("REALIZAR OPERACIÓN");
        this.add(BOTON_REALIZAR_OPERACION);

        JButton BOTON_VER_HISTORIAL = new JButton("VER HISTORIAL");
        this.add(BOTON_VER_HISTORIAL);

        JButton BOTON_CONSULTAR_CUENTAS = new JButton("CUENTAS");
        this.add(BOTON_CONSULTAR_CUENTAS);

        BOTON_REALIZAR_OPERACION.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelNoHayCuentas.getInstancia().isVisible() && !panelConsultarCuentas.getInstancia().isVisible()){
                    panelModificarCuenta.getInstancia().ocultarPanel();
                    panelPrincipal.getInstancia().ocultarPanel();
                    panelHistorialOperaciones.getInstancia().ocultarPanel();
                    panelOperacion.getInstancia().ocultarPanel();

                    panelRealizarOperacion.getInstancia().mostrarPanel();
                }
            }
        });

        BOTON_CONSULTAR_CUENTAS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelNoHayCuentas.getInstancia().isVisible()){
                    panelModificarCuenta.getInstancia().ocultarPanel();
                    panelPrincipal.getInstancia().ocultarPanel();
                    panelRealizarOperacion.getInstancia().ocultarPanel();
                    panelHistorialOperaciones.getInstancia().ocultarPanel();
                    panelOperacion.getInstancia().ocultarPanel();

                    panelConsultarCuentas.getInstancia().mostrarPanel();
                }
            }
        });

        BOTON_VER_HISTORIAL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelNoHayCuentas.getInstancia().isVisible() && !panelConsultarCuentas.getInstancia().isVisible()){
                    panelModificarCuenta.getInstancia().ocultarPanel();
                    panelPrincipal.getInstancia().ocultarPanel();
                    panelRealizarOperacion.getInstancia().ocultarPanel();
                    panelOperacion.getInstancia().ocultarPanel();

                    panelHistorialOperaciones.getInstancia().mostrarPanel();
                }
            }
        });

    }

    @Override
    public void ocultarPanel() {
        // EL PANEL MENU NUNCA SE VA HA OCULTAR
    }

    @Override
    public void mostrarPanel() {
        ventana.add(this, BorderLayout.WEST);
        this.setVisible(true);
    }
}
