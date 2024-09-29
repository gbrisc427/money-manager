package moneymanager.vista.paneles;

import moneymanager.business.CuentaManager;
import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelNoHayCuentas extends JPanel implements Panel{

    private static panelNoHayCuentas instancia = null;

    public static panelNoHayCuentas getInstancia() {
        if (instancia == null) {
            instancia = new panelNoHayCuentas();
        }
        return instancia;
    }

    private panelNoHayCuentas(){

        this.setLayout(new BorderLayout());
        this.setVisible(false);

        JLabel ETIQUETA_TITULO = new JLabel("¡VAYA!", SwingConstants.CENTER);
        ETIQUETA_TITULO.setFont(new Font("Lexend", Font.BOLD, 30));
        ETIQUETA_TITULO.setBorder(new EmptyBorder(35, 15, 20, 15));
        ETIQUETA_TITULO.setForeground(VistaVentana.COLOR_ALERTA);
        ETIQUETA_TITULO.setBackground(VistaVentana.COLOR_PRIMARIO);
        this.add(ETIQUETA_TITULO,BorderLayout.NORTH);

        JPanel PANEL_INFO = new JPanel();
        PANEL_INFO.setLayout(new FlowLayout(FlowLayout.CENTER));
        PANEL_INFO.setVisible(true);

        JLabel ETIQUETA_INFO = new JLabel("<html>PARECE QUE NO TIENES NINGUNA <br> CUENTA CREADA ACTUELMENTE, <br> PULSA  EL BOTON  PARA CREAR UNA <br> CUENTA POR DEFECTO.</html>", SwingConstants.CENTER);
        ETIQUETA_INFO.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_INFO.setBorder(new EmptyBorder(50, 15, 15, 15));
        ETIQUETA_INFO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        PANEL_INFO.add(ETIQUETA_INFO);

        JButton BOTON_CREAR_CUENTA = new JButton("CREAR CUENTA");
        BOTON_CREAR_CUENTA.setBorder(new EmptyBorder(9, 10, 9, 10));
        BOTON_CREAR_CUENTA.setForeground(VistaVentana.COLOR_PRIMARIO);
        PANEL_INFO.add(BOTON_CREAR_CUENTA);
        PANEL_INFO.setBorder(new EmptyBorder(10, 30, 20, 20));

        this.add(PANEL_INFO, BorderLayout.CENTER);

        BOTON_CREAR_CUENTA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CuentaManager.getInstancia().inicializar();
                ocultarPanel();
                panelPrincipal.getInstancia().mostrarPanel();
            }
        });
    }


    @Override
    public void ocultarPanel() {
        ventana.remove(this);
        this.setVisible(false);
    }

    @Override
    public void mostrarPanel() {
        ventana.add(this, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
