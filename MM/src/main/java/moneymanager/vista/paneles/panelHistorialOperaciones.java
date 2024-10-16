package moneymanager.vista.paneles;

import moneymanager.business.CuentaManager;
import moneymanager.business.Operacion;
import moneymanager.business.TOperacion;
import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelHistorialOperaciones extends JPanel implements Panel{

    private  static panelHistorialOperaciones instancia = null;

    private final JPanel PANEL_OPERACIONES;

    public static panelHistorialOperaciones getInstancia() {
        if (instancia == null) {
            instancia = new panelHistorialOperaciones();
        }
        return instancia;
    }

    private panelHistorialOperaciones(){
        CuentaManager CM = CuentaManager.getInstancia();

        this.setLayout(new BorderLayout());
        this.setVisible(false);

        PANEL_OPERACIONES = new JPanel();
        PANEL_OPERACIONES.setLayout(new FlowLayout(FlowLayout.LEFT, 20,20));

        updateOperaciones();

        PANEL_OPERACIONES.setPreferredSize(new Dimension(300, CM.getCuentaActual().getHistorial().size()*52));
        JScrollPane SCROLL_OPERACIONES = new JScrollPane(PANEL_OPERACIONES);
        SCROLL_OPERACIONES.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        SCROLL_OPERACIONES.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLL_OPERACIONES.setVisible(true);
        SCROLL_OPERACIONES.setFocusable(false);
        SCROLL_OPERACIONES.getVerticalScrollBar().setUnitIncrement(23);
        SCROLL_OPERACIONES.getVerticalScrollBar().setBlockIncrement(50);
        SCROLL_OPERACIONES.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        SCROLL_OPERACIONES.setBackground(VistaVentana.COLOR_PRIMARIO);
        SCROLL_OPERACIONES.setBorder(new EmptyBorder(0,0,0,0));

        this.add(SCROLL_OPERACIONES, BorderLayout.CENTER);
    }

    private void updateOperaciones(){
        PANEL_OPERACIONES.removeAll();
        PANEL_OPERACIONES.revalidate();
        PANEL_OPERACIONES.repaint();
        CuentaManager CM = CuentaManager.getInstancia();
        if (VistaVentana.CUENTA.getHistorial().isEmpty()){

            JLabel noHayOperaciones = new JLabel("<html> EL HISTORIAL ESTÁ VACÍO </html>",SwingConstants.CENTER);
            noHayOperaciones.setFont(new Font("Lexend", Font.BOLD, 30));
            noHayOperaciones.setBorder(new EmptyBorder(110, 80, 10, 5));
            noHayOperaciones.setPreferredSize(new Dimension(350, 300));
            noHayOperaciones.setForeground(VistaVentana.COLOR_ALERTA);
            PANEL_OPERACIONES.add(noHayOperaciones);
        }
        for (Operacion opc : VistaVentana.CUENTA.getHistorial()) {
            String id = opc.getId();
            String fecha = opc.getFecha().getYear()+"/"+ opc.getFecha().getMonthValue()+"/"
                    +opc.getFecha().getDayOfMonth();
            String txt = "";
            if (opc.getTOperacion().equals(TOperacion.TRANSFERENCIA)){
                txt = fecha + "  " + -opc.getCantidad() + "€  " + opc.getCategoria() ;
            }else{
                txt = fecha + "  " +opc.getCantidad() + "€  " + opc.getCategoria() ;
            }

            JButton boton = new JButton(id);

            boton.setFont(new Font("Lexend", Font.BOLD, 15));
            boton.setBorder(new EmptyBorder(1, 5, 1, 5));
            boton.setPreferredSize(new Dimension(70, 30));
            boton.setForeground(VistaVentana.COLOR_SECUNDARIO);
            boton.setBackground(VistaVentana.COLOR_PRIMARIO);

            JLabel cant = new JLabel(txt);
            cant.setFont(new Font("Lexend", Font.BOLD, 15));
            cant.setBorder(new EmptyBorder(1, 5, 1, 5));
            cant.setPreferredSize(new Dimension(280, 30));
            cant.setForeground(VistaVentana.COLOR_SECUNDARIO);
            cant.setBackground(VistaVentana.COLOR_PRIMARIO);
            if (opc.getCantidad() < 0 || opc.getTOperacion().equals(TOperacion.TRANSFERENCIA)){
                boton.setForeground(VistaVentana.COLOR_ALERTA);
                cant.setForeground(VistaVentana.COLOR_ALERTA);
            }

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaVentana.TIPO_OPERACION = opc.getTOperacion();
                    VistaVentana.ID_OPERACION = opc.getId();
                    ocultarPanel();
                    panelOperacion.getInstancia().mostrarPanel();
                }
            });

            PANEL_OPERACIONES.add(boton);
            PANEL_OPERACIONES.add(cant);

        }
        PANEL_OPERACIONES.setPreferredSize(new Dimension(300, CM.getCuentaActual().getHistorial().size()*52));

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
        updateOperaciones();
    }
}
