package moneymanager.vista.paneles;

import moneymanager.business.Cuenta;
import moneymanager.business.CuentaManager;
import moneymanager.business.LimitDocument;
import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelConsultarCuentas extends JPanel implements Panel{

    private static panelConsultarCuentas instancia = null;

    private final JPanel PANEL_CUENTAS;

    public static panelConsultarCuentas getInstancia() {
        if (instancia == null) {
            instancia = new panelConsultarCuentas();
        }
        return instancia;
    }

    private panelConsultarCuentas(){
        CuentaManager CM = CuentaManager.getInstancia();
        this.setLayout(new BorderLayout());
        this.setVisible(false);

        PANEL_CUENTAS = new JPanel();
        PANEL_CUENTAS.setLayout(new FlowLayout(FlowLayout.LEFT, 20,20));

        updateCuentas();

        PANEL_CUENTAS.setPreferredSize(new Dimension(300, CM.getCuentas().size()*52));

        JScrollPane SCROLL_CUENTAS = new JScrollPane(PANEL_CUENTAS);
        SCROLL_CUENTAS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        SCROLL_CUENTAS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLL_CUENTAS.setVisible(true);
        SCROLL_CUENTAS.setFocusable(false);
        SCROLL_CUENTAS.getVerticalScrollBar().setUnitIncrement(23);
        SCROLL_CUENTAS.getVerticalScrollBar().setBlockIncrement(50);
        SCROLL_CUENTAS.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createInvisibleButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createInvisibleButton();
            }

            private JButton createInvisibleButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
        SCROLL_CUENTAS.setBackground(VistaVentana.COLOR_PRIMARIO);
        SCROLL_CUENTAS.setBorder(new EmptyBorder(0,0,0,0));

        JButton BOTON_CREAR_CUENTA = new JButton("CREAR CUENTA");
        BOTON_CREAR_CUENTA.setFont(new Font("Lexend", Font.BOLD, 15));
        BOTON_CREAR_CUENTA.setBorder(new EmptyBorder(15, 15, 30, 15));
        BOTON_CREAR_CUENTA.setForeground(VistaVentana.COLOR_SECUNDARIO);
        BOTON_CREAR_CUENTA.setBackground(VistaVentana.COLOR_PRIMARIO);

        this.add(SCROLL_CUENTAS, BorderLayout.CENTER);
        this.add(BOTON_CREAR_CUENTA, BorderLayout.SOUTH);

        BOTON_CREAR_CUENTA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpNewCuenta(ventana);
            }
        });
    }

    private void updateCuentas(){
        PANEL_CUENTAS.removeAll();
        PANEL_CUENTAS.revalidate();
        PANEL_CUENTAS.repaint();
        CuentaManager CM = CuentaManager.getInstancia();
        for (Cuenta aux : CM.getCuentas()) {
            String txt = aux.getNombre();
            JButton boton = new JButton(txt);

            boton.setFont(new Font("Lexend", Font.BOLD, 20));
            boton.setBorder(new EmptyBorder(1, 5, 1, 5));
            boton.setPreferredSize(new Dimension(200, 30));
            boton.setForeground(VistaVentana.COLOR_SECUNDARIO);
            boton.setBackground(VistaVentana.COLOR_PRIMARIO);

            JLabel cant = new JLabel(aux.getSaldo()+"€");
            cant.setFont(new Font("Lexend", Font.BOLD, 20));
            cant.setBorder(new EmptyBorder(1, 20, 1, 5));
            cant.setPreferredSize(new Dimension(100, 30));
            cant.setForeground(VistaVentana.COLOR_SECUNDARIO);
            cant.setBackground(VistaVentana.COLOR_PRIMARIO);

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CM.cambiarCuenta(aux);
                    ocultarPanel();
                    panelPrincipal.getInstancia().mostrarPanel();
                }
            });

            PANEL_CUENTAS.add(boton);
            PANEL_CUENTAS.add(cant);
        }
        PANEL_CUENTAS.setPreferredSize(new Dimension(300, CM.getCuentas().size()*52));
    }

    private void popUpNewCuenta(JFrame parentFrame) {
        CuentaManager CM = CuentaManager.getInstancia();

        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(325, 200);
        dialogo.setLayout(new FlowLayout());

        JLabel mensaje = new JLabel("<html>INTRODUCE EL NOMBRE <br>DE LA NUEVA CUENTA.</html>");
        mensaje.setFont(new Font("Lexend", Font.BOLD, 20));
        mensaje.setBorder(new EmptyBorder(25, 15, 15, 15));
        mensaje.setForeground(VistaVentana.COLOR_SECUNDARIO);
        dialogo.add(mensaje);

        JTextField nombre = new JTextField(15);
        nombre.setFont(new Font("Lexend", Font.BOLD, 12));
        nombre.setBorder(new EmptyBorder(10, 25, 10, 25));
        nombre.setForeground(VistaVentana.COLOR_PRIMARIO);
        nombre.setBackground(VistaVentana.COLOR_SECUNDARIO);
        nombre.setDocument(new LimitDocument(20));
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        nombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = nombre.getText();
                if (!text.isEmpty()){
                    CM.crearCuenta(text.toUpperCase());
                    updateCuentas();
                    ocultarPanel();
                    panelPrincipal.getInstancia().mostrarPanel();
                    dialogo.dispose();
                }
            }
        });
        dialogo.add(nombre);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
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
        updateCuentas();
    }
}
