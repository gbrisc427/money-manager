package moneymanager.vista.paneles;


import moneymanager.business.CuentaManager;
import moneymanager.business.Operacion;
import moneymanager.business.OperacionesManager;
import moneymanager.business.TOperacion;
import moneymanager.vista.VistaVentana;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class panelHistorialOperaciones extends JPanel implements Panel{


    private  static panelHistorialOperaciones instancia = null;


    private final JPanel PANEL_OPERACIONES;
    private final JComboBox<String> COMBOBOX_MESES;
    private final String[] MESES = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
    private int MES;
    private int ANIO;


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


        LocalDateTime now = LocalDateTime.now();


        int primerMes = OperacionesManager.getInstancia().getOperaciones().getFirst().getFecha().getMonthValue();
        int primerAno =  OperacionesManager.getInstancia().getOperaciones().getFirst().getFecha().getYear();
        int mesActual = now.getMonthValue();
        int anoActual =  now.getYear();

        String[] opciones;

        if (primerAno == anoActual){
            opciones = new String[mesActual - primerMes + 1];
            for (int i = 0; i < opciones.length ; i++) {
                opciones[i] = MESES[mesActual - 1 - i];
            }
        }else{
            opciones = new String[ (13 - primerMes) + mesActual + 12 * (anoActual - primerAno - 1) ];
            int mes = mesActual;
            int ano = anoActual;
            for (int i = 0; i < opciones.length; i++) {
                mes -= 1;


                if (mes < 0){
                    mes +=  12;
                    ano -= 1;
                }
                opciones[i] = MESES[mes] + " // " + ano;
            }
        }


        COMBOBOX_MESES = new JComboBox<>(opciones);
        COMBOBOX_MESES.setFont(new Font("Lexend", Font.BOLD, 15));
        COMBOBOX_MESES.setForeground(VistaVentana.COLOR_SECUNDARIO);
        COMBOBOX_MESES.setBackground(VistaVentana.COLOR_PRIMARIO);
        COMBOBOX_MESES.setBorder(new EmptyBorder(0,0,0,0));
        COMBOBOX_MESES.setSelectedIndex(0);


        COMBOBOX_MESES.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
                return label;
            }
        });


        COMBOBOX_MESES.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                JComponent popup = (JComponent) COMBOBOX_MESES.getUI().getAccessibleChild(COMBOBOX_MESES, 0);
                if (popup instanceof JPopupMenu) {
                    JScrollPane scrollPane = (JScrollPane) ((JPopupMenu) popup).getComponent(0);
                    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();


                    scrollPane.setFocusable(false);
                    scrollPane.getVerticalScrollBar().setUnitIncrement(23);
                    scrollPane.getVerticalScrollBar().setBlockIncrement(50);
                    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
                    scrollPane.setBackground(VistaVentana.COLOR_PRIMARIO);


                    verticalScrollBar.setPreferredSize(new Dimension(10, 0));
                }
            }


            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}


            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
        });


        COMBOBOX_MESES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOperaciones();
            }
        });


        this.add(COMBOBOX_MESES, BorderLayout.NORTH);




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


        if (String.valueOf(COMBOBOX_MESES.getSelectedItem()).contains("//")){
            MES = valorMes(String.valueOf(COMBOBOX_MESES.getSelectedItem()).split(" // ")[0]);
            ANIO = Integer.parseInt(String.valueOf(COMBOBOX_MESES.getSelectedItem()).split(" // ")[1]);
        }else{
            MES = valorMes(String.valueOf(COMBOBOX_MESES.getSelectedItem()));
            ANIO = LocalDateTime.now().getYear();
        }


        if (CM.getOperacionesCAFechaDeterminada(MES, ANIO).isEmpty()){
            JLabel noHayOperaciones = new JLabel("<html> EL HISTORIAL ESTÁ VACÍO </html>",SwingConstants.CENTER);
            noHayOperaciones.setFont(new Font("Lexend", Font.BOLD, 30));
            noHayOperaciones.setBorder(new EmptyBorder(110, 80, 10, 5));
            noHayOperaciones.setPreferredSize(new Dimension(350, 300));
            noHayOperaciones.setForeground(VistaVentana.COLOR_ALERTA);
            PANEL_OPERACIONES.add(noHayOperaciones);
        }
        for (Operacion opc : CM.getOperacionesCAFechaDeterminada(MES, ANIO)) {
            String id = opc.getId();
            String fecha = opc.getFecha().getYear()+"/"+opc.getFecha().getDayOfMonth()+"/"
                    +opc.getFecha().getMonthValue();
            String txt = "";
            txt = fecha + "  " +opc.getCantidad() + "€  " + opc.getCategoria() ;


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
            if (opc.getCantidad() < 0){
                boton.setForeground(VistaVentana.COLOR_ALERTA);
                cant.setForeground(VistaVentana.COLOR_ALERTA);
            }
            if (opc.getTOperacion().equals(TOperacion.TRANSFERENCIA)){
                boton.setForeground(VistaVentana.COLOR_TRANSFERENCIA);
                cant.setForeground(VistaVentana.COLOR_TRANSFERENCIA);
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
        PANEL_OPERACIONES.setPreferredSize(new Dimension(300, CM.getOperacionesCAFechaDeterminada(MES, ANIO).size()*52));


    }


    private int valorMes(String mes){
        int sol = -1;
        for (int i = 0; i < MESES.length; i++) {
            if (MESES[i].equals(mes)){
                sol = i + 1;
            }
        }
        return sol;
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

