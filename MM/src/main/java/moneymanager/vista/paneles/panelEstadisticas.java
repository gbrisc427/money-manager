package moneymanager.vista.paneles;

import moneymanager.business.CuentaManager;
import moneymanager.business.Gasto;
import moneymanager.business.OperacionesManager;
import moneymanager.business.TOperacion;
import moneymanager.vista.VistaVentana;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Objects;

public class panelEstadisticas extends JPanel implements Panel{



    private static panelEstadisticas instancia = null;

    private final JLabel ETIQUETA_NOMBRE_CUENTA;
    private final JLabel ETIQUETA_ID;
    private final JComboBox<String> COMBOBOX_TIEMPO;
    private final JComboBox<String> COMBOBOX_MESES;
    private final JComboBox<String> COMBOBOX_CATEGORIAS;

    private final String[] MESES = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

    private final String[] OPCIONES = {"MES", "ULT. TRIMESTRE", "ULT. AÑO", "SIEMPRE"};

    private  final JLabel ETIQUETA_INGRESOS_TOTALES;
    private  final JLabel ETIQUETA_GASTOS_TOTALES;
    private  final JLabel ETIQUETA_BENEFICIO;
    private  final JLabel ETIQUETA_INFO_GRAFICO;
    private final DefaultCategoryDataset DATASET_GRAFICO;
    private final ChartPanel PANEL_GRAFICO;

    private int MES;
    private int ANIO;

    public static panelEstadisticas getInstancia() {
        if (instancia == null) {
            instancia = new panelEstadisticas();
        }
        return instancia;
    }

    private panelEstadisticas() {
        CuentaManager CM = CuentaManager.getInstancia();

        this.setLayout(new BorderLayout());
        this.setVisible(false);

        String nombreCuenta = CM.getNombreCuenta();
        ETIQUETA_NOMBRE_CUENTA = new JLabel(nombreCuenta, SwingConstants.CENTER);

        ETIQUETA_NOMBRE_CUENTA.setFont(new Font("Lexend", Font.BOLD, 30));
        ETIQUETA_NOMBRE_CUENTA.setBorder(new EmptyBorder(35, 15, 0, 15));
        ETIQUETA_NOMBRE_CUENTA.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_NOMBRE_CUENTA.setBackground(VistaVentana.COLOR_PRIMARIO);
        this.add(ETIQUETA_NOMBRE_CUENTA, BorderLayout.NORTH);

        JPanel PANEL_OPERACIONES = new JPanel();
        PANEL_OPERACIONES.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        COMBOBOX_TIEMPO = new JComboBox<>(OPCIONES);
        COMBOBOX_TIEMPO.setFont(new Font("Lexend", Font.BOLD, 12));
        COMBOBOX_TIEMPO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        COMBOBOX_TIEMPO.setBackground(VistaVentana.COLOR_PRIMARIO);
        COMBOBOX_TIEMPO.setBorder(new EmptyBorder(0,0,0,0));
        COMBOBOX_TIEMPO.setSelectedIndex(0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        COMBOBOX_TIEMPO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "MES") || Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "SIEMPRE")) {
                    COMBOBOX_MESES.setEnabled(true);
                    ETIQUETA_INFO_GRAFICO.setVisible(true);
                    COMBOBOX_MESES.setSelectedIndex(0);
                    PANEL_GRAFICO.setVisible(false);
                }else{
                    COMBOBOX_MESES.setEnabled(false);
                    ETIQUETA_INFO_GRAFICO.setVisible(false);
                    PANEL_GRAFICO.setVisible(true);
                    COMBOBOX_MESES.setSelectedItem(null);
                }
                updateInfo();
            }
        });

        PANEL_OPERACIONES.add(COMBOBOX_TIEMPO, gbc);

        COMBOBOX_MESES = new JComboBox<>(getOpcionesComboBoxMeses());
        COMBOBOX_MESES.setFont(new Font("Lexend", Font.BOLD, 12));
        COMBOBOX_MESES.setForeground(VistaVentana.COLOR_SECUNDARIO);
        COMBOBOX_MESES.setBackground(VistaVentana.COLOR_PRIMARIO);
        COMBOBOX_MESES.setBorder(new EmptyBorder(0,0,0,0));
        COMBOBOX_MESES.setSelectedIndex(0);

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
                updateInfo();
            }
        });

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        PANEL_OPERACIONES.add(COMBOBOX_MESES, gbc);

        ETIQUETA_INGRESOS_TOTALES = new JLabel();
        ETIQUETA_INGRESOS_TOTALES.setFont(new Font("Lexend", Font.BOLD, 17));
        ETIQUETA_INGRESOS_TOTALES.setBorder(new EmptyBorder(30, 10, 5, 10));
        ETIQUETA_INGRESOS_TOTALES.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_OPERACIONES.add(ETIQUETA_INGRESOS_TOTALES, gbc);


        ETIQUETA_GASTOS_TOTALES = new JLabel();
        ETIQUETA_GASTOS_TOTALES.setFont(new Font("Lexend", Font.BOLD, 17));
        ETIQUETA_GASTOS_TOTALES.setBorder(new EmptyBorder(5, 10, 5, 10));
        ETIQUETA_GASTOS_TOTALES.setForeground(VistaVentana.COLOR_ALERTA);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_OPERACIONES.add(ETIQUETA_GASTOS_TOTALES, gbc);

        ETIQUETA_BENEFICIO = new JLabel();
        ETIQUETA_BENEFICIO.setFont(new Font("Lexend", Font.BOLD, 20));
        ETIQUETA_BENEFICIO.setBorder(new EmptyBorder(5, 10, 50, 10));
        ETIQUETA_BENEFICIO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_OPERACIONES.add(ETIQUETA_BENEFICIO, gbc);

        ETIQUETA_INFO_GRAFICO = new JLabel("<html>* EL GRÁFICO DE INGRESOS/GASTOS SOLO ESTÁ <br>DISPONIBLE PARA EL ÚLTIMO AÑO Y TIMESTRE<html>");
        ETIQUETA_INFO_GRAFICO.setFont(new Font("Lexend", Font.BOLD, 10));
        ETIQUETA_INFO_GRAFICO.setBorder(new EmptyBorder(5, 10, 10, 10));
        ETIQUETA_INFO_GRAFICO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_OPERACIONES.add(ETIQUETA_INFO_GRAFICO, gbc);



        DATASET_GRAFICO = new DefaultCategoryDataset();

        JFreeChart GRAFICO_ING_GASTOS = ChartFactory.createLineChart(
                "",
                "",
                "€",
                DATASET_GRAFICO,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );


        CategoryPlot plot = GRAFICO_ING_GASTOS.getCategoryPlot();

        plot.setBackgroundPaint(VistaVentana.COLOR_PRIMARIO);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, rangeAxis.getUpperBound());

        rangeAxis.setRange(0.0, 200.0);

        rangeAxis.setTickUnit(new NumberTickUnit(25));

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, VistaVentana.COLOR_SECUNDARIO);
        plot.setRenderer(renderer);
        CategoryAxis domainAxis = plot.getDomainAxis();

        domainAxis.setTickLabelPaint(VistaVentana.COLOR_SECUNDARIO);
        domainAxis.setLabelPaint(VistaVentana.COLOR_SECUNDARIO);

        rangeAxis.setTickLabelPaint(VistaVentana.COLOR_SECUNDARIO);
        rangeAxis.setLabelPaint(VistaVentana.COLOR_SECUNDARIO);

        GRAFICO_ING_GASTOS.setBackgroundPaint(VistaVentana.COLOR_PRIMARIO);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        PANEL_GRAFICO = new ChartPanel(GRAFICO_ING_GASTOS);
        PANEL_GRAFICO.setPreferredSize(new java.awt.Dimension(300, 200));
        PANEL_OPERACIONES.add(PANEL_GRAFICO, gbc);

        JLabel ETIQUETA_CATEGORIAS = new JLabel("CATEGORÍAS: ");
        ETIQUETA_CATEGORIAS.setFont(new Font("Lexend", Font.BOLD, 20));
        ETIQUETA_CATEGORIAS.setBorder(new EmptyBorder(10,  10, 10, 10));
        ETIQUETA_CATEGORIAS.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_OPERACIONES.add(ETIQUETA_CATEGORIAS, gbc);


        COMBOBOX_CATEGORIAS = new JComboBox<>(new String[]{"INGRESOS", "GASTOS"});

        //COMBO BOX QUE CONTENGA LA OPCIÓN INGRESOS / GASTOS

        //GRÁFICO QUE MUESTRE LOS ING / GASTOS POR CADA CATEGORÍA SEGÚN EL TIEMPO SELECCIONADO


        JScrollPane SCROLL_ESTADISTICAS = new JScrollPane(PANEL_OPERACIONES);
        SCROLL_ESTADISTICAS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        SCROLL_ESTADISTICAS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SCROLL_ESTADISTICAS.setVisible(true);
        SCROLL_ESTADISTICAS.setFocusable(false);
        SCROLL_ESTADISTICAS.getVerticalScrollBar().setUnitIncrement(23);
        SCROLL_ESTADISTICAS.getVerticalScrollBar().setBlockIncrement(50);
        SCROLL_ESTADISTICAS.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        SCROLL_ESTADISTICAS.setBackground(VistaVentana.COLOR_PRIMARIO);
        SCROLL_ESTADISTICAS.setBorder(new EmptyBorder(0,0,0,0));

        this.add(SCROLL_ESTADISTICAS, BorderLayout.CENTER);

        String id = "#"+CM.getCuentaActual().getId();
        ETIQUETA_ID = new JLabel(id, SwingConstants.CENTER);
        ETIQUETA_ID.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_ID.setBorder(new EmptyBorder(0, 15, 20, 15));
        this.add(ETIQUETA_ID, BorderLayout.SOUTH);

    }

    private String[] getOpcionesComboBoxMeses(){
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

        return opciones;
    }

    private void setFecha(){
        if (String.valueOf(COMBOBOX_MESES.getSelectedItem()).contains("//")){
            MES = valorMes(String.valueOf(COMBOBOX_MESES.getSelectedItem()).split(" // ")[0]);
            ANIO = Integer.parseInt(String.valueOf(COMBOBOX_MESES.getSelectedItem()).split(" // ")[1]);
        }else if (COMBOBOX_MESES.getSelectedItem() != null){
            MES = valorMes(String.valueOf(COMBOBOX_MESES.getSelectedItem()));
            ANIO = LocalDateTime.now().getYear();
        }else{
            MES = LocalDateTime.now().getMonthValue();
            ANIO = LocalDateTime.now().getYear();
        }
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

    private float calcularIngresos(){
        CuentaManager CM = CuentaManager.getInstancia();
        float ingresos = 0;
        if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "MES")) {
            ingresos = CM.cantTotal(TOperacion.INGRESO, MES, ANIO);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. TRIMESTRE")) {
            ingresos = CM.cantTotal(TOperacion.INGRESO, 0);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. AÑO")) {
            ingresos = CM.cantTotal(TOperacion.INGRESO, 1);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "SIEMPRE")) {
            ingresos = CM.cantTotal(TOperacion.INGRESO);
        }
        return ingresos;
    }


    private float calcularGastos(){
        CuentaManager CM = CuentaManager.getInstancia();
        float gastos = 0;
        if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "MES")) {
            gastos = CM.cantTotal(TOperacion.GASTO, MES, ANIO);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. TRIMESTRE")) {
            gastos = CM.cantTotal(TOperacion.GASTO, 0);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. AÑO")) {
            gastos = CM.cantTotal(TOperacion.GASTO, 1);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "SIEMPRE")) {
            gastos = CM.cantTotal(TOperacion.GASTO);
        }
        return gastos;
    }

    private float calcularTransferencias(){
        CuentaManager CM = CuentaManager.getInstancia();
        float transf = 0;
        if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "MES")) {
            transf = CM.cantTotal(TOperacion.TRANSFERENCIA, MES, ANIO);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. TRIMESTRE")) {
            transf = CM.cantTotal(TOperacion.TRANSFERENCIA, 0);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. AÑO")) {
            transf = CM.cantTotal(TOperacion.TRANSFERENCIA, 1);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "SIEMPRE")) {
            transf = CM.cantTotal(TOperacion.TRANSFERENCIA);
        }
        return transf;
    }

    private void setData() {
        DATASET_GRAFICO.clear();
        if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. TRIMESTRE")) {
            setData(3);
        }else if (Objects.equals(COMBOBOX_TIEMPO.getSelectedItem(), "ULT. AÑO")) {
            setData(MES);
        }
    }

    private void setData(int meses){
        CuentaManager CM = CuentaManager.getInstancia();
        double ingreso, gastos;
        for (int i = 0; i < meses; i++) {
            ingreso = CM.cantTotal(TOperacion.INGRESO, MES-i, ANIO);
            gastos = -1 * CM.cantTotal(TOperacion.GASTO, MES-i, ANIO) + -1 * CM.cantTotal(TOperacion.TRANSFERENCIA, MES-i, ANIO);
            DATASET_GRAFICO.addValue(ingreso, "ING", MESES[MES-1-i]);
            DATASET_GRAFICO.addValue(gastos, "GASTOS", MESES[MES-1-i]);
        }
    }

    private float round(float num){
        return Math.round(num * 100) / 100f;
    }

    private void updateInfo(){
        CuentaManager CM = CuentaManager.getInstancia();
        setFecha();
        VistaVentana.CUENTA = CM.getCuentaActual();
        String nombre = CM.getNombreCuenta();
        String id = CM.getId();
        float ingresos = calcularIngresos();
        float gastos = (-1 * calcularGastos() + -1 * calcularTransferencias()) ;
        String ingresosT = "INGRESOS TOTALES: " + ingresos + "€";
        String gastosT = "GASTOS TOTALES: " + gastos + "€";
        String beneficio = "BENEFICIO: " + round(ingresos - gastos)  +"€";

        setData();

        ETIQUETA_NOMBRE_CUENTA.setText(nombre);
        ETIQUETA_INGRESOS_TOTALES.setText(ingresosT);
        ETIQUETA_GASTOS_TOTALES.setText(gastosT);
        ETIQUETA_BENEFICIO.setText(beneficio);
        ETIQUETA_ID.setText(id);
    }



    @Override
    public void ocultarPanel() {
        ventana.remove(this);
        this.setVisible(false);
    }

    @Override
    public void mostrarPanel() {
        updateInfo();
        COMBOBOX_TIEMPO.setSelectedIndex(0);
        ventana.add(this, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
