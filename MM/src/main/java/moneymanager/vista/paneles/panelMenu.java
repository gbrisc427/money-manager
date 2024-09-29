package moneymanager.vista.paneles;

import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class panelMenu extends JPanel implements Panel {

    private static panelMenu instancia = null;

    public static panelMenu getInstancia() {
        if (instancia == null) {
            instancia = new panelMenu();
        }
        return instancia;
    }

    private panelMenu(){

        this.setBackground(VistaVentana.COLOR_SECUNDARIO);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(173, 0));

        JButton BOTON_REALIZAR_OPERACION = new JButton("REALIZAR OPERACIÓN");
        BOTON_REALIZAR_OPERACION.setAlignmentX(Component.CENTER_ALIGNMENT);
        BOTON_REALIZAR_OPERACION.setMaximumSize(new Dimension(Integer.MAX_VALUE, BOTON_REALIZAR_OPERACION.getMinimumSize().height));
        this.add(BOTON_REALIZAR_OPERACION);

        JButton BOTON_VER_HISTORIAL = new JButton("VER HISTORIAL");
        BOTON_VER_HISTORIAL.setAlignmentX(Component.CENTER_ALIGNMENT);
        BOTON_VER_HISTORIAL.setMaximumSize(new Dimension(Integer.MAX_VALUE, BOTON_VER_HISTORIAL.getMinimumSize().height));
        this.add(BOTON_VER_HISTORIAL);

        JButton BOTON_CONSULTAR_CUENTAS = new JButton("CUENTAS");
        BOTON_CONSULTAR_CUENTAS.setAlignmentX(Component.CENTER_ALIGNMENT);
        BOTON_CONSULTAR_CUENTAS.setMaximumSize(new Dimension(Integer.MAX_VALUE, BOTON_CONSULTAR_CUENTAS.getMinimumSize().height));
        this.add(BOTON_CONSULTAR_CUENTAS);

        this.add(Box.createVerticalGlue());
        JButton BOTON_AYUDA = new JButton("AYUDA");
        BOTON_AYUDA.setAlignmentX(Component.CENTER_ALIGNMENT);
        BOTON_AYUDA.setMaximumSize(new Dimension(Integer.MAX_VALUE, BOTON_AYUDA.getMinimumSize().height));
        BOTON_AYUDA.setBorder(new EmptyBorder(10,15,10,10));
        this.add(BOTON_AYUDA);

        JButton BOTON_AJUSTES = new JButton("AJUSTES");
        BOTON_AJUSTES.setAlignmentX(Component.CENTER_ALIGNMENT);
        BOTON_AJUSTES.setMaximumSize(new Dimension(Integer.MAX_VALUE, BOTON_AJUSTES.getMinimumSize().height));
        BOTON_AJUSTES.setBorder(new EmptyBorder(10,15,30,10));
        this.add(BOTON_AJUSTES);


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

        BOTON_AYUDA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpAyuda(ventana);
            }
        });

        BOTON_AJUSTES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelNoHayCuentas.getInstancia().isVisible()){
                    panelModificarCuenta.getInstancia().ocultarPanel();
                    panelPrincipal.getInstancia().ocultarPanel();
                    panelRealizarOperacion.getInstancia().ocultarPanel();
                    panelHistorialOperaciones.getInstancia().ocultarPanel();
                    panelOperacion.getInstancia().ocultarPanel();
                    panelConsultarCuentas.getInstancia().ocultarPanel();

                    panelAjustes.getInstancia().mostrarPanel();
                }
            }
        });


    }

    private void popUpAyuda(JFrame parentFrame){
        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(420, 500);
        dialogo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("MANUAL",SwingConstants.CENTER);
        titulo.setFont(new Font("Lexend", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(25, 15, 25, 15));
        titulo.setForeground(VistaVentana.COLOR_ALERTA);
        titulo.setBackground(VistaVentana.COLOR_PRIMARIO);
        titulo.setHorizontalTextPosition(0);
        dialogo.add(titulo);

        JLabel txt = new JLabel();
        txt.setFont(new Font("Lexend", Font.BOLD, 12));
        txt.setBorder(new EmptyBorder(10, 15, 25, 15));
        txt.setForeground(VistaVentana.COLOR_SECUNDARIO);
        txt.setBackground(VistaVentana.COLOR_PRIMARIO);
        txt.setHorizontalAlignment(0);
        txt.setText("<html><p style=\"color: rgb(227, 111, 111);\">REALIZAR OPERACIÓN:</p>AQUÍ PUEDES  REGISTRAR UNA OPERACIÓN,<br> COMO UN  INGRESO, GASTO O TRANSFERENCIA. <br> RELLENANDO LOS DIFERENTES CAMPOS. <br>" +
                "<br><p style=\"color: rgb(227, 111, 111);\"> VER HISTORIAL:</p>  AQUÍ PUEDES CONSULTAR EL HISTORIAL DE TODAS <br>LAS OPERACIONES, SI PULSAS EL ID DE ALGUNA DE <br> ESTAS, PUEDES MODIFICARLA O ELIMINARLA <br>" +
                "<br><p style=\"color: rgb(227, 111, 111);\">CUENTAS:</p> AQUÍ PUEDES CONSULTAR TODAS  TUS CUENTAS,<br> ASÍ COMO CREAR  NUEVAS, SI PULSAS EL ID DE LA  <br> CUENTA, ACCEDES A ESTA. <br> " +
                "UNA VEZ EN EL PANEL PRINCIPAL, SI  PULSAS EL ID <br> DE LA CUENTA, PUEDES MODIFICAR SU NOMBRE  <br> Y ELIMINARLA. <br>" +
                "<br><p style=\"color: rgb(227, 111, 111);\">AJUSTES:</p> PUEDES CAMBIAR LOS COLORES DE LA INTERFAZ.<br></html>");
        dialogo.add(txt);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
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
