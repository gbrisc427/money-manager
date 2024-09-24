package moneymanager.vista;


import moneymanager.business.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class VistaVentana extends JFrame {

    private static VistaVentana instancia = null;
    public static volatile boolean ventanaAbierta = true;

    //COMPONENTES
    private final JButton botonNombreCuenta;
    private final JButton botonNombreCuenta2;
    private final JLabel etiquetaSaldo;
    private final JPanel panelPrincipal;
    private final JPanel panelModificarCuenta;
    private final JPanel panelNoHayCuentas;
    private final JPanel panelRealizarOperacion;
    private final JPanel panelConsultarCuentas;
    private final JPanel panelCuentas;
    private Cuenta cuenta;
    private boolean cuentaEilinada;



    public static VistaVentana getInstancia() {
        if (instancia == null) {
            cambiarEstilo();
            instancia = new VistaVentana();
        }
        return instancia;
    }

    private VistaVentana() {

        OperacionesManager OM = OperacionesManager.getInstancia();
        CuentaManager CM = CuentaManager.getInstancia();
        cuenta = CM.getCuentaActual();
        if (CM.inicializar()){
            popUpBienvenida(VistaVentana.this);
        }

        setTitle("");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // PANEL PRINCIPAL
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // BOTÓN NOMBRE DE LA CUENTA
        String nombreCuenta = CM.getNombreCuenta();
        botonNombreCuenta = new JButton(nombreCuenta);
        botonNombreCuenta.setFont(new Font("Lexend", Font.BOLD, 30));
        botonNombreCuenta.setBorder(new EmptyBorder(35, 15, 0, 15));
        botonNombreCuenta.setForeground(new Color(164, 227, 111));
        botonNombreCuenta.setBackground(new Color(253, 242, 240));
        panelPrincipal.add(botonNombreCuenta, BorderLayout.NORTH);


        // ETIQUETA CON EL SALDO DE LA CUENTA
        String saldo = CM.getSaldo();
        etiquetaSaldo = new JLabel(saldo, SwingConstants.CENTER);
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
        panelModificarCuenta = new JPanel();
        panelModificarCuenta.setLayout(new BorderLayout());
        panelModificarCuenta.setVisible(false);

        // BOTÓN2 NOMBRE DE LA CUENTA
        botonNombreCuenta2 = new JButton(nombreCuenta);
        botonNombreCuenta2.setFont(new Font("Lexend", Font.BOLD, 30));
        botonNombreCuenta2.setBorder(new EmptyBorder(25, 15, 0, 15));
        botonNombreCuenta2.setForeground(new Color(164, 227, 111));
        botonNombreCuenta2.setBackground(new Color(253, 242, 240));
        panelModificarCuenta.add(botonNombreCuenta2, BorderLayout.NORTH);

        //BOTON ELIMINAR CUENTA
        JButton botonEliminarCuenta = new JButton("ELIMINAR CUENTA");
        botonEliminarCuenta.setFont(new Font("Lexend", Font.BOLD, 15));
        botonEliminarCuenta.setBorder(new EmptyBorder(35, 15, 25, 15));
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

        // PANEL NO HAY CUENTAS CREADAS
        panelNoHayCuentas = new JPanel();
        panelNoHayCuentas.setLayout(new BorderLayout());
        panelNoHayCuentas.setVisible(false);

        JLabel vaia = new JLabel("¡VAYA!", SwingConstants.CENTER);
        vaia.setFont(new Font("Lexend", Font.BOLD, 30));
        vaia.setBorder(new EmptyBorder(35, 15, 20, 15));
        vaia.setForeground(new Color(227, 111, 111));
        vaia.setBackground(new Color(253, 242, 240));
        panelNoHayCuentas.add(vaia,BorderLayout.NORTH);

        JPanel infoNoHayCuentas = new JPanel();
        infoNoHayCuentas.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoNoHayCuentas.setVisible(true);

        JLabel txtNoHayCuentas = new JLabel("<html>PARECE QUE NO TIENES NINGUNA <br> CUENTA CREADA ACTUELMENTE, <br> PULSA  EL BOTON  PARA CREAR UNA <br> CUENTA POR DEFECTO.</html>", SwingConstants.CENTER);
        txtNoHayCuentas.setFont(new Font("Lexend", Font.BOLD, 15));
        txtNoHayCuentas.setBorder(new EmptyBorder(50, 15, 15, 15));
        txtNoHayCuentas.setForeground(new Color(164, 227, 111));
        infoNoHayCuentas.add(txtNoHayCuentas);

        JButton botonNoHayCuentas = new JButton("CREAR CUENTA");
        botonNoHayCuentas.setBorder(new EmptyBorder(9, 10, 9, 10));
        botonNoHayCuentas.setForeground(new Color(253, 242, 240));
        infoNoHayCuentas.add(botonNoHayCuentas);
        infoNoHayCuentas.setBorder(new EmptyBorder(10, 30, 20, 20));

        panelNoHayCuentas.add(infoNoHayCuentas, BorderLayout.CENTER);


        // PANEL REALIZAR OPERACION
        panelRealizarOperacion = new JPanel();
        panelRealizarOperacion.setLayout(new BorderLayout());
        panelRealizarOperacion.setVisible(false);

        JLabel reOpTitulo = new JLabel("REALIZAR OPERACIÓN", SwingConstants.CENTER);
        reOpTitulo.setFont(new Font("Lexend", Font.BOLD, 25));
        reOpTitulo.setBorder(new EmptyBorder(35, 15, 20, 15));
        reOpTitulo.setForeground(new Color(164, 227, 111));
        reOpTitulo.setBackground(new Color(253, 242, 240));
        reOpTitulo.setVisible(true);
        panelRealizarOperacion.add(reOpTitulo,BorderLayout.NORTH);

        JPanel panelRealizarOp = new JPanel();
        panelRealizarOp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelRealizarOp.setVisible(true);

        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel txtSubTitulo = new JLabel("<html> INTRODUCE LOS VALORES: </html>", SwingConstants.LEFT);
        txtSubTitulo.setFont(new Font("Lexend", Font.BOLD, 17));
        txtSubTitulo.setForeground(new Color(164, 227, 111));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panelRealizarOp.add(txtSubTitulo, gbc);

        gbc.insets = new Insets(5, 0, 5, 5);

        JLabel txtCant = new JLabel("<html>CANTIDAD: </html>", SwingConstants.LEFT);
        txtCant.setFont(new Font("Lexend", Font.BOLD, 15));
        txtCant.setForeground(new Color(164, 227, 111));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        panelRealizarOp.add(txtCant, gbc);

        JTextField txtFCant = new JTextField(4);
        txtFCant.setFont(new Font("Lexend", Font.BOLD, 13));
        txtFCant.setBorder(new EmptyBorder(8, 8, 8, 8));
        txtFCant.setForeground(new Color(253, 242, 240));
        txtFCant.setBackground(new Color(164, 227, 111));
        txtFCant.setText("0.0");
        txtFCant.setDocument(new NumericDocument(6));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        panelRealizarOp.add(txtFCant, gbc);

        JLabel txtCategoria = new JLabel("<html>CATEGORÍA: </html>", SwingConstants.LEFT);
        txtCategoria.setFont(new Font("Lexend", Font.BOLD, 15));
        txtCategoria.setForeground(new Color(164, 227, 111));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        panelRealizarOp.add(txtCategoria, gbc);

        JTextField txtFCategoria = new JTextField(15);
        txtFCategoria.setFont(new Font("Lexend", Font.BOLD, 13));
        txtFCategoria.setBorder(new EmptyBorder(8, 8, 8, 8));
        txtFCategoria.setForeground(new Color(253, 242, 240));
        txtFCategoria.setBackground(new Color(164, 227, 111));
        txtFCategoria.setDocument(new LimitDocument(20));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        panelRealizarOp.add(txtFCategoria, gbc);

        JLabel txtAsunto = new JLabel("<html>ASUNTO: </html>", SwingConstants.LEFT);
        txtAsunto.setFont(new Font("Lexend", Font.BOLD, 15));
        txtAsunto.setForeground(new Color(164, 227, 111));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        panelRealizarOp.add(txtAsunto, gbc);

        JTextField txtFAsunto = new JTextField(15);
        txtFAsunto.setFont(new Font("Lexend", Font.BOLD, 13));
        txtFAsunto.setBorder(new EmptyBorder(8, 8, 8, 8));
        txtFAsunto.setForeground(new Color(253, 242, 240));
        txtFAsunto.setBackground(new Color(164, 227, 111));
        txtFAsunto.setDocument(new LimitDocument(50));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        panelRealizarOp.add(txtFAsunto, gbc);

        gbc.insets = new Insets(10, 0, 10, 5);

        JCheckBox checkBoxTransferencia = new JCheckBox("TRANSEFERENCIA");
        checkBoxTransferencia.setFont(new Font("Lexend", Font.BOLD, 15));
        checkBoxTransferencia.setForeground(new Color(164, 227, 111));
        checkBoxTransferencia.setBackground(new Color(253, 242, 240));
        checkBoxTransferencia.setBorder(new EmptyBorder(1,1,1,1));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelRealizarOp.add(checkBoxTransferencia, gbc);

        String[] opciones = CM.getCuentasOpTransf();
        JComboBox<String> cuentasTransf = new JComboBox<>(opciones);
        cuentasTransf.setFont(new Font("Lexend", Font.BOLD, 15));
        cuentasTransf.setForeground(new Color(164, 227, 111));
        cuentasTransf.setBackground(new Color(253, 242, 240));
        cuentasTransf.setBorder(new EmptyBorder(0,0,0,0));
        if (opciones.length == 0){
            cuentasTransf.setEnabled(false);
            cuentasTransf.setVisible(false);
            checkBoxTransferencia.setVisible(false);
        }else{
            cuentasTransf.setSelectedIndex(0);
        }
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelRealizarOp.add(cuentasTransf, gbc);

        panelRealizarOp.setBorder(new EmptyBorder(15,30,15,15));
        panelRealizarOperacion.add(panelRealizarOp, BorderLayout.CENTER);

        JPanel botonesRealizarOp = new JPanel();
        botonesRealizarOp.setLayout(new FlowLayout(FlowLayout.CENTER));
        botonesRealizarOp.setVisible(true);

        JButton botonGuardarOp = new JButton("ACEPTAR");
        botonGuardarOp.setBorder(new EmptyBorder(9, 9,9, 9));
        botonGuardarOp.setForeground(new Color(253, 242, 240));
        botonesRealizarOp.add(botonGuardarOp);

        JButton botonCancelarOp = new JButton("CANCELAR");
        botonCancelarOp.setBorder(new EmptyBorder(9, 9, 9, 9));
        botonCancelarOp.setForeground(new Color(253, 242, 240));
        botonCancelarOp.setBackground(new Color(227, 111, 111));
        botonesRealizarOp.add(botonCancelarOp);

        botonesRealizarOp.setBorder(new EmptyBorder(0,0,30,0));
        panelRealizarOperacion.add(botonesRealizarOp, BorderLayout.SOUTH);


        // PANEL CUENTAS
        panelConsultarCuentas = new JPanel();
        panelConsultarCuentas.setLayout(new BorderLayout());
        panelConsultarCuentas.setVisible(false);

        panelCuentas = new JPanel();
        panelCuentas.setLayout(new FlowLayout(FlowLayout.LEFT, 20,20));

        updateCuentas();

        panelCuentas.setPreferredSize(new Dimension(300, CM.getCuentas().size()*52));

        JScrollPane scrollCuentas = new JScrollPane(panelCuentas);
        scrollCuentas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollCuentas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCuentas.setVisible(true);
        scrollCuentas.setFocusable(false);
        scrollCuentas.setBackground(new Color(253, 242, 240));
        scrollCuentas.setBorder(new EmptyBorder(0,0,0,0));

        JButton botonCrearCuenta = new JButton("CREAR CUENTA");
        botonCrearCuenta.setFont(new Font("Lexend", Font.BOLD, 15));
        botonCrearCuenta.setBorder(new EmptyBorder(15, 15, 30, 15));
        botonCrearCuenta.setForeground(new Color(164, 227, 111));
        botonCrearCuenta.setBackground(new Color(253, 242, 240));


        panelConsultarCuentas.add(scrollCuentas, BorderLayout.CENTER);
        panelConsultarCuentas.add(botonCrearCuenta, BorderLayout.SOUTH);


        // VENTANA
        this.add(menuPanel, BorderLayout.WEST); // Menú en el lado izquierdo
        this.add(panelPrincipal, BorderLayout.CENTER); // Contenido principal en el centro


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
                VistaVentana.this.add(panelModificarCuenta, BorderLayout.CENTER);
                panelPrincipal.setVisible(false);
                panelModificarCuenta.setVisible(true);
            }
        });

        botonNombreCuenta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaVentana.this.remove(panelModificarCuenta);
                panelPrincipal.setVisible(true);
                panelModificarCuenta.setVisible(false);
                nuevoNombre.setText("");
            }
        });

        botonGuardarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textField = nuevoNombre.getText().toUpperCase();
                if (!textField.isEmpty()) {
                    panelPrincipal.setVisible(true);
                    panelModificarCuenta.setVisible(false);
                    VistaVentana.this.remove(panelModificarCuenta);
                    infoCambioNombre.setVisible(false);
                    CM.modificarCuenta(textField);
                    updateInfo();
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
                updateInfo();
                nuevoNombre.setText("");
                if (cuentaEilinada){
                    if (cuenta == null){
                        VistaVentana.this.remove(panelModificarCuenta);
                        VistaVentana.this.add(panelNoHayCuentas, BorderLayout.CENTER);
                        panelModificarCuenta.setVisible(false);
                        panelNoHayCuentas.setVisible(true);
                    }else{
                        panelPrincipal.setVisible(true);
                        VistaVentana.this.remove(panelModificarCuenta);
                        panelModificarCuenta.setVisible(false);
                    }
                }
            }
        });

        botonNoHayCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CuentaManager.getInstancia().inicializar();
                updateInfo();
                VistaVentana.this.remove(panelNoHayCuentas);
                panelNoHayCuentas.setVisible(false);
                panelPrincipal.setVisible(true);
            }
        });

        realizarOperacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelNoHayCuentas.isVisible()){
                    VistaVentana.this.remove(panelModificarCuenta);
                    panelModificarCuenta.setVisible(false);
                    VistaVentana.this.remove(panelPrincipal);
                    panelPrincipal.setVisible(false);
                    VistaVentana.this.add(panelRealizarOperacion, BorderLayout.CENTER);
                    panelRealizarOperacion.setVisible(true);
                }
            }
        });

        checkBoxTransferencia.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (opciones.length == 0){
                    cuentasTransf.setEnabled(false);
                }else{
                    cuentasTransf.setEnabled(true);
                    cuentasTransf.setSelectedIndex(0);
                }
            } else {
                cuentasTransf.setEnabled(false);
            }
        });

        botonGuardarOp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !txtFCant.getText().isEmpty() && !txtFAsunto.getText().isEmpty()
                        && !txtFCategoria.getText().isEmpty()) {
                    if (Float.parseFloat(txtFCant.getText()) != 0) {
                        if (checkBoxTransferencia.isSelected()) {
                            if (cuentasTransf.getSelectedItem().equals(null)) {
                               popUpDatosOp(VistaVentana.this);
                            } else {
                                Cuenta cuentaDest = CM.getCuenta((String) cuentasTransf.getSelectedItem());
                                OM.registrarTransferencia(txtFAsunto.getText(), Float.parseFloat(txtFCant.getText()),
                                        txtFCategoria.getText(), cuentaDest);
                            }
                        } else {
                            OM.registrarIngresoGasto(txtFAsunto.getText(), Float.parseFloat(txtFCant.getText()),
                                    txtFCategoria.getText());
                        }
                        updateInfo();
                        VistaVentana.this.remove(panelRealizarOperacion);
                        panelRealizarOperacion.setVisible(false);
                        VistaVentana.this.add(panelPrincipal);
                        panelPrincipal.setVisible(true);
                    } else {
                        popUpDatosOp(VistaVentana.this);
                    }
                }else {
                    popUpDatosOp(VistaVentana.this);
                }
                txtFAsunto.setText("");
                txtFCant.setText("");
                txtFCategoria.setText("");
                checkBoxTransferencia.setSelected(false);
            }
        });

        botonCancelarOp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               VistaVentana.this.remove(panelRealizarOperacion);
               panelRealizarOperacion.setVisible(false);
               VistaVentana.this.add(panelPrincipal);
               panelPrincipal.setVisible(true);
               txtFAsunto.setText("");
               txtFCant.setText("");
               txtFCategoria.setText("");
               checkBoxTransferencia.setSelected(false);
            }
        });

        consultarCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panelNoHayCuentas.isVisible()){
                    VistaVentana.this.remove(panelModificarCuenta);
                    panelModificarCuenta.setVisible(false);
                    VistaVentana.this.remove(panelPrincipal);
                    panelPrincipal.setVisible(false);
                    VistaVentana.this.add(panelConsultarCuentas, BorderLayout.CENTER);
                    panelConsultarCuentas.setVisible(true);
                }
            }
        });

        botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpNewCuenta(VistaVentana.this);
            }
        });

    }

    private void popUpBienvenida(JFrame parentFrame){

        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(340, 250);
        dialogo.setLayout(new FlowLayout());

        JLabel bienvenidaTitulo = new JLabel();
        bienvenidaTitulo.setFont(new Font("Lexend", Font.BOLD, 30));
        bienvenidaTitulo.setBorder(new EmptyBorder(25, 15, 15, 15));
        bienvenidaTitulo.setForeground(new Color(164, 227, 111));
        bienvenidaTitulo.setBackground(new Color(253, 242, 240));
        bienvenidaTitulo.setHorizontalAlignment(0);
        bienvenidaTitulo.setText("¡BIENVENIDO!");
        dialogo.add(bienvenidaTitulo);

        JLabel bienvenidaTexto = new JLabel();
        bienvenidaTexto.setFont(new Font("Lexend", Font.BOLD, 12));
        bienvenidaTexto.setBorder(new EmptyBorder(15, 15, 25, 15));
        bienvenidaTexto.setForeground(new Color(164, 227, 111));
        bienvenidaTexto.setBackground(new Color(253, 242, 240));
        bienvenidaTexto.setHorizontalAlignment(0);
        bienvenidaTexto.setText("<html>BIENVENIDO A MONEY-MANAGER. <br> CIERRE ESTA VENTANA PARA COMENZAR. <br> SE CREARÁ UNA CUENTA POR <br> DEFECTO AUTOMÁTICAMENTE.</html>");
        dialogo.add(bienvenidaTexto);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
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
                    updateInfo();
                    cuentaEilinada = true;
                }else{
                    cuentaEilinada = false;
                }
            }
        });
        dialogo.add(confirmar);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    private void popUpDatosOp(JFrame parentFrame){
        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(400, 350);
        dialogo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("",SwingConstants.CENTER);
        titulo.setFont(new Font("Lexend", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(25, 15, 15, 15));
        titulo.setForeground(new Color(227, 111, 111));
        titulo.setBackground(new Color(253, 242, 240));
        titulo.setHorizontalTextPosition(0);
        titulo.setText("<html>ALGUNO DE LOS DATOS <br> INTRODUCIDOS ES INCORRECTO</html>");
        dialogo.add(titulo);

        JLabel txt = new JLabel();
        txt.setFont(new Font("Lexend", Font.BOLD, 12));
        txt.setBorder(new EmptyBorder(0, 15, 25, 15));
        txt.setForeground(new Color(164, 227, 111));
        txt.setBackground(new Color(253, 242, 240));
        txt.setHorizontalAlignment(0);
        txt.setText("<html><p style=\"color: rgb(227, 111, 111);\">CANTIDAD:</p> DEBE CONTENER UN  <br>VALOR NUMÉRICO DIFERENTE A 0" +
                "<br><p style=\"color: rgb(227, 111, 111);\">CATEGORÍA:</p>  DEBE CONTENER UNA CADENA <br>ALFANUMÉRICA QUE NO SUPERE LOS 20 <br>CARÁCTERES (NO PUEDE QUEDAR VACÍA)" +
                "<br><p style=\"color: rgb(227, 111, 111);\">ASUNTO:</p>  DEBE CONTENER UNA CADENA <br>ALFANUMÉRICA QUE NO SUPERE LOS 50 <br>CARÁCTERES (NO PUEDE QUEDAR VACÍA)</html>");
        dialogo.add(txt);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    private void popUpNewCuenta(JFrame parentFrame) {
        CuentaManager CM = CuentaManager.getInstancia();

        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(325, 200);
        dialogo.setLayout(new FlowLayout());

        JLabel mensaje = new JLabel("<html>INTRODUCE EL NOMBRE <br>DE LA NUEVA CUENTA.</html>");
        mensaje.setFont(new Font("Lexend", Font.BOLD, 20));
        mensaje.setBorder(new EmptyBorder(25, 15, 15, 15));
        mensaje.setForeground(new Color(164, 227, 111));
        dialogo.add(mensaje);

        JTextField nombre = new JTextField(15);
        nombre.setFont(new Font("Lexend", Font.BOLD, 12));
        nombre.setBorder(new EmptyBorder(10, 25, 10, 25));
        nombre.setForeground(new Color(253, 242, 240));
        nombre.setBackground(new Color(164, 227, 111));
        nombre.setDocument(new LimitDocument(20));
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        nombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = nombre.getText();
                if (!text.isEmpty()){
                    CM.crearCuenta(text.toUpperCase());
                    updateInfo();
                    VistaVentana.this.remove(panelConsultarCuentas);
                    panelConsultarCuentas.setVisible(false);
                    VistaVentana.this.add(panelPrincipal, BorderLayout.CENTER);
                    panelPrincipal.setVisible(true);
                    dialogo.dispose();
                }
            }
        });
        dialogo.add(nombre);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    private void updateInfo(){
        CuentaManager CM = CuentaManager.getInstancia();
        this.cuenta = CM.getCuentaActual();
        String nombre = CM.getNombreCuenta();
        String saldo = CM.getSaldo();
        botonNombreCuenta.setText(nombre);
        botonNombreCuenta2.setText(nombre);
        etiquetaSaldo.setText(saldo);
        panelCuentas.removeAll();
        panelCuentas.revalidate();
        panelCuentas.repaint();
        updateCuentas();
    }

    private void updateCuentas(){
        CuentaManager CM = CuentaManager.getInstancia();
        for (Cuenta aux : CM.getCuentas()) {
            String txt = aux.getNombre();
            JButton boton = new JButton(txt);

            boton.setFont(new Font("Lexend", Font.BOLD, 20));
            boton.setBorder(new EmptyBorder(1, 5, 1, 5));
            boton.setPreferredSize(new Dimension(200, 30));
            boton.setForeground(new Color(164, 227, 111));
            boton.setBackground(new Color(253, 242, 240));

            JLabel cant = new JLabel(aux.getSaldo()+"€");
            cant.setFont(new Font("Lexend", Font.BOLD, 20));
            cant.setBorder(new EmptyBorder(1, 20, 1, 5));
            cant.setPreferredSize(new Dimension(80, 30));
            cant.setForeground(new Color(164, 227, 111));
            cant.setBackground(new Color(253, 242, 240));


            panelCuentas.add(boton);
            panelCuentas.add(cant);
        }
    }

    private static void cambiarEstilo() {
        UIManager.put("Button.background", new Color(164, 227, 111));
        UIManager.put("Button.foreground", new Color(253, 242, 240));
        UIManager.put("Button.font", new Font("Lexend", Font.BOLD, 12));
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 15, 10, 15));
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.select", new Color(253, 242, 240));
        UIManager.put("Panel.background", new Color(253, 242, 240));
        UIManager.put("CheckBox.select", new Color(253, 242, 240));
        UIManager.put("CheckBox.focus", new Color(253, 242, 240));
        UIManager.put("ComboBox.selectionBackground", new Color(164, 227, 111));
        UIManager.put("ComboBox.selectionForeground", new Color(253, 242, 240));
        UIManager.put("ComboBox.focus", new Color(253, 242, 240));
        UIManager.put("ComboBox.buttonBackground", new Color(253, 242, 240));
        UIManager.put("ComboBox.buttonDarkShadow", new Color(253, 242, 240));
        UIManager.put("ComboBox.buttonHighlight", new Color(253, 242, 240));

        UIManager.put("ScrollBar.thumb", new Color(253, 242, 240));
        UIManager.put("ScrollBar.thumbDarkShadow", new Color(253, 242, 240));
        UIManager.put("ScrollBar.thumbHighlight", new Color(253, 242, 240));
        UIManager.put("ScrollBar.thumbShadow", new Color(253, 242, 240));
        UIManager.put("ScrollBar.track", new Color(253, 242, 240));
        UIManager.put("ScrollBar.trackHighlight", new Color(253, 242, 240));
        UIManager.put("ScrollBar.trackDarkShadow", new Color(253, 242, 240));
        UIManager.put("ScrollBar.trackShadow", new Color(253, 242, 240));
        UIManager.put("ScrollBar.foreground", new Color(253, 242, 240));
        UIManager.put("ScrollBar.background", new Color(253, 242, 240));
        UIManager.put("ScrollBar.incrementButtonSize", new Dimension(0, 0));
        UIManager.put("ScrollBar.decrementButtonSize", new Dimension(0, 0));
    }

}