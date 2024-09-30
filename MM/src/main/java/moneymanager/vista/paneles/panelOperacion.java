package moneymanager.vista.paneles;

import moneymanager.business.*;
import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelOperacion extends JPanel implements Panel{

    private static panelOperacion instancia = null;

    private final JLabel ETIQUETA_ID_OPERACION;
    private final JLabel ETIQUETA_FECHA;
    private final JLabel ETIQUETA_CANTIDAD;
    private final JLabel ETIQUETA_CATEGORIA;
    private final JLabel ETIQUETA_ASUNTO;

    private boolean operacionEliminada;

    public static panelOperacion getInstancia() {
        if (instancia == null) {
            instancia = new panelOperacion();
        }
        return instancia;
    }

    private panelOperacion(){
        this.setLayout(new BorderLayout());
        this.setVisible(false);

        ETIQUETA_ID_OPERACION = new JLabel("",SwingConstants.CENTER);
        ETIQUETA_ID_OPERACION.setFont(new Font("Lexend", Font.BOLD, 30));
        ETIQUETA_ID_OPERACION.setBorder(new EmptyBorder(25, 15, 0, 15));
        ETIQUETA_ID_OPERACION.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_ID_OPERACION.setBackground(VistaVentana.COLOR_PRIMARIO);
        this.add(ETIQUETA_ID_OPERACION, BorderLayout.NORTH);


        JPanel PANEL_DATOS_OPERACION = new JPanel();
        PANEL_DATOS_OPERACION.setLayout(new FlowLayout(FlowLayout.CENTER));
        PANEL_DATOS_OPERACION.setVisible(true);

        ETIQUETA_CANTIDAD = new JLabel("", SwingConstants.CENTER);
        ETIQUETA_CANTIDAD.setFont(new Font("Lexend", Font.BOLD, 70));
        ETIQUETA_CANTIDAD.setBorder(new EmptyBorder(60, 0, 30, 0));
        ETIQUETA_CANTIDAD.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_CANTIDAD.setPreferredSize(new Dimension(400,160));
        PANEL_DATOS_OPERACION.add(ETIQUETA_CANTIDAD);

        ETIQUETA_FECHA = new JLabel("", SwingConstants.CENTER);
        ETIQUETA_FECHA.setFont(new Font("Lexend", Font.BOLD, 20));
        ETIQUETA_FECHA.setBorder(new EmptyBorder(10, 10, 10, 10));
        ETIQUETA_FECHA.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_FECHA.setPreferredSize(new Dimension(400,40));
        PANEL_DATOS_OPERACION.add(ETIQUETA_FECHA);

        ETIQUETA_CATEGORIA = new JLabel("", SwingConstants.CENTER);
        ETIQUETA_CATEGORIA.setFont(new Font("Lexend", Font.BOLD, 20));
        ETIQUETA_CATEGORIA.setBorder(new EmptyBorder(10, 10, 10, 10));
        ETIQUETA_CATEGORIA.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_CATEGORIA.setPreferredSize(new Dimension(400,40));
        PANEL_DATOS_OPERACION.add(ETIQUETA_CATEGORIA);

        ETIQUETA_ASUNTO = new JLabel("", SwingConstants.CENTER);
        ETIQUETA_ASUNTO.setFont(new Font("Lexend", Font.BOLD, 12));
        ETIQUETA_ASUNTO.setBorder(new EmptyBorder(10, 10, 10, 10));
        ETIQUETA_ASUNTO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_ASUNTO.setPreferredSize(new Dimension(200,40));
        PANEL_DATOS_OPERACION.add(ETIQUETA_ASUNTO);

        JPanel PANEL_BOTONES = new JPanel();
        PANEL_BOTONES.setLayout(new FlowLayout(FlowLayout.CENTER));
        PANEL_BOTONES.setVisible(true);

        JButton BOTON_MODIFICAR = new JButton("MODIFICAR");
        BOTON_MODIFICAR.setBorder(new EmptyBorder(9, 9,9, 9));
        BOTON_MODIFICAR.setForeground(VistaVentana.COLOR_PRIMARIO);
        PANEL_BOTONES.add(BOTON_MODIFICAR);

        JButton BOTON_ELIMINAR = new JButton("ELIMINAR");
        BOTON_ELIMINAR.setBorder(new EmptyBorder(9, 9, 9, 9));
        BOTON_ELIMINAR.setForeground(VistaVentana.COLOR_PRIMARIO);
        BOTON_ELIMINAR.setBackground(VistaVentana.COLOR_ALERTA);
        PANEL_BOTONES.add(BOTON_ELIMINAR);

        PANEL_BOTONES.setBorder(new EmptyBorder(0,0,30,0));
        this.add(PANEL_BOTONES, BorderLayout.SOUTH);

        this.add(PANEL_DATOS_OPERACION, BorderLayout.CENTER);

        BOTON_ELIMINAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpEliminarOperacion(ventana);
                if (operacionEliminada){
                    ocultarPanel();
                    panelHistorialOperaciones.getInstancia().mostrarPanel();
                }
            }
        });

        BOTON_MODIFICAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpModOperacion(ventana);
            }
        });
    }

    private void popUpEliminarOperacion(JFrame parentFrame) {

        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(275, 200);
        dialogo.setLayout(new FlowLayout());

        JLabel mensaje = new JLabel("<html>PARA ELIMINAR LA OPERACIÓN <br> ESCRIBE \"CONFIRMAR\".</html>");
        mensaje.setFont(new Font("Lexend", Font.BOLD, 15));
        mensaje.setBorder(new EmptyBorder(25, 15, 15, 15));
        mensaje.setForeground(VistaVentana.COLOR_ALERTA);
        dialogo.add(mensaje);

        JTextField confirmar = new JTextField(8);
        confirmar.setFont(new Font("Lexend", Font.BOLD, 12));
        confirmar.setBorder(new EmptyBorder(10, 25, 10, 25));
        confirmar.setForeground(VistaVentana.COLOR_PRIMARIO);
        confirmar.setBackground(VistaVentana.COLOR_ALERTA);
        confirmar.setDocument(new LimitDocument(9));
        confirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = confirmar.getText();
                if (text.equals("CONFIRMAR")) {
                    dialogo.dispose();
                    OperacionesManager.getInstancia().eliminarOperacion(ETIQUETA_ID_OPERACION.getText().replace("#", ""),
                            VistaVentana.TIPO_OPERACION);
                    operacionEliminada = true;
                }else{
                    operacionEliminada = false;
                }
            }
        });
        dialogo.add(confirmar);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    private void popUpModOperacion(JFrame parentFrame) {

        GridBagConstraints gbc = new GridBagConstraints();
        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(400, 400);
        dialogo.setLayout(new BorderLayout());

        JLabel tituloModifOp = new JLabel(ETIQUETA_ID_OPERACION.getText(), SwingConstants.CENTER);
        tituloModifOp.setFont(new Font("Lexend", Font.BOLD, 30));
        tituloModifOp.setBorder(new EmptyBorder(25, 15, 0, 15));
        tituloModifOp.setForeground(VistaVentana.COLOR_SECUNDARIO);
        tituloModifOp.setBackground(VistaVentana.COLOR_PRIMARIO);

        dialogo.add(tituloModifOp, BorderLayout.NORTH);

        JPanel nuevosDatosOp = new JPanel();
        nuevosDatosOp.setLayout(new GridBagLayout());
        nuevosDatosOp.setVisible(true);

        gbc.insets = new Insets(15, 0, 15, 0);

        JLabel txtSubTitulo = new JLabel("<html> DEJE EN BLANCO LOS VALORES QUE NO QUIERE MODIFICAR. </html>", SwingConstants.LEFT);
        txtSubTitulo.setFont(new Font("Lexend", Font.BOLD, 10));
        txtSubTitulo.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        nuevosDatosOp.add(txtSubTitulo, gbc);

        gbc.insets = new Insets(5, 0, 5, 5);

        JLabel txtNewCant = new JLabel("<html>CANTIDAD: </html>", SwingConstants.LEFT);
        txtNewCant.setFont(new Font("Lexend", Font.BOLD, 15));
        txtNewCant.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        nuevosDatosOp.add(txtNewCant, gbc);

        JTextField txtFNewCant = new JTextField(4);
        txtFNewCant.setFont(new Font("Lexend", Font.BOLD, 13));
        txtFNewCant.setBorder(new EmptyBorder(8, 8, 8, 8));
        txtFNewCant.setForeground(VistaVentana.COLOR_PRIMARIO);
        txtFNewCant.setBackground(VistaVentana.COLOR_SECUNDARIO);
        txtFNewCant.setText("0.0");
        txtFNewCant.setDocument(new NumericDocument(6));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        nuevosDatosOp.add(txtFNewCant, gbc);

        JLabel txtNewCategoria = new JLabel("<html>CATEGORÍA: </html>", SwingConstants.LEFT);
        txtNewCategoria.setFont(new Font("Lexend", Font.BOLD, 15));
        txtNewCategoria.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        nuevosDatosOp.add(txtNewCategoria, gbc);

        JTextField txtFNewCategoria = new JTextField(15);
        txtFNewCategoria.setFont(new Font("Lexend", Font.BOLD, 13));
        txtFNewCategoria.setBorder(new EmptyBorder(8, 8, 8, 8));
        txtFNewCategoria.setForeground(VistaVentana.COLOR_PRIMARIO);
        txtFNewCategoria.setBackground(VistaVentana.COLOR_SECUNDARIO);
        txtFNewCategoria.setDocument(new LimitDocument(20));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        nuevosDatosOp.add(txtFNewCategoria, gbc);

        JLabel txtNewAsunto = new JLabel("<html>ASUNTO: </html>", SwingConstants.LEFT);
        txtNewAsunto.setFont(new Font("Lexend", Font.BOLD, 15));
        txtNewAsunto.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        nuevosDatosOp.add(txtNewAsunto, gbc);

        JTextField txtFNewAsunto = new JTextField(15);
        txtFNewAsunto.setFont(new Font("Lexend", Font.BOLD, 13));
        txtFNewAsunto.setBorder(new EmptyBorder(8, 8, 8, 8));
        txtFNewAsunto.setForeground(VistaVentana.COLOR_PRIMARIO);
        txtFNewAsunto.setBackground(VistaVentana.COLOR_SECUNDARIO);
        txtFNewAsunto.setDocument(new LimitDocument(50));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        nuevosDatosOp.add(txtFNewAsunto, gbc);

        nuevosDatosOp.setBorder(new EmptyBorder(15,30,15,15));
        dialogo.add(nuevosDatosOp, BorderLayout.CENTER);

        JButton botonGuardarModOp = new JButton("GUARDAR");
        botonGuardarModOp.setBorder(new EmptyBorder(9, 9,40, 9));
        botonGuardarModOp.setForeground(VistaVentana.COLOR_SECUNDARIO);
        botonGuardarModOp.setBackground(VistaVentana.COLOR_PRIMARIO);

        dialogo.add(botonGuardarModOp, BorderLayout.SOUTH);

        botonGuardarModOp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperacionesManager OP = OperacionesManager.getInstancia();
                if (VistaVentana.TIPO_OPERACION.equals(TOperacion.TRANSFERENCIA)){
                    if (txtFNewCant.getText().isEmpty()){
                        OP.modificarTransferencia(tituloModifOp.getText(), txtFNewCategoria.getText(),
                                txtFNewAsunto.getText(), 0.0F);
                        updateOperacion();
                        dialogo.dispose();
                    }else{
                        if (Float.parseFloat(txtFNewCant.getText()) != 0){
                            float res = VistaVentana.CUENTA.getSaldo() - Float.parseFloat(txtFNewCant.getText());
                            if (res < 0 || Float.parseFloat(txtFNewCant.getText()) < 0){
                                dialogo.dispose();
                                popUpDatosOp(ventana);
                            }else{
                                OP.modificarTransferencia(tituloModifOp.getText(), txtFNewCategoria.getText(),
                                        txtFNewAsunto.getText(), Float.parseFloat(txtFNewCant.getText()));
                                updateOperacion();
                                dialogo.dispose();
                            }
                        }
                    }
                }else{
                    if (txtFNewCant.getText().isEmpty()){
                        OP.modificarIngresoGasto(VistaVentana.TIPO_OPERACION, tituloModifOp.getText().replace("#", ""),
                                txtFNewCategoria.getText(),txtFNewAsunto.getText(), 0.0F);
                        updateOperacion();
                        dialogo.dispose();
                    }else{
                        if (Float.parseFloat(txtFNewCant.getText()) != 0){
                            float res = VistaVentana.CUENTA.getSaldo() + Float.parseFloat(txtFNewCant.getText());
                            if (res < 0){
                                popUpDatosOp(ventana);
                            }else{
                                OP.modificarIngresoGasto(VistaVentana.TIPO_OPERACION, tituloModifOp.getText().replace("#", ""),
                                        txtFNewCategoria.getText(),txtFNewAsunto.getText(),
                                        Float.parseFloat(txtFNewCant.getText()));
                                updateOperacion();
                                dialogo.dispose();
                            }
                        }
                    }
                }

                txtFNewAsunto.setText("");
                txtFNewCant.setText("");
                txtFNewCategoria.setText("");
            }
        });

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    private void updateOperacion(){
        Operacion operacion = OperacionesManager.getInstancia().buscarOperacion(VistaVentana.ID_OPERACION,
                VistaVentana.TIPO_OPERACION);
        String fecha = operacion.getFecha().getDayOfMonth() + "/"  +
                operacion.getFecha().getMonthValue() + "/" + operacion.getFecha().getYear();
        ETIQUETA_ID_OPERACION.setText("#" + VistaVentana.ID_OPERACION);
        ETIQUETA_ASUNTO.setText(operacion.getMotivo());
        ETIQUETA_FECHA.setText(fecha);
        ETIQUETA_CATEGORIA.setText(operacion.getCategoria());
        ETIQUETA_CANTIDAD.setText(operacion.getCantidad() + "€");
        if (operacion.getCantidad()< 0){
            ETIQUETA_CANTIDAD.setForeground(VistaVentana.COLOR_ALERTA);
        }
        else ETIQUETA_CANTIDAD.setForeground(VistaVentana.COLOR_SECUNDARIO);

    }

    private void popUpDatosOp(JFrame parentFrame){
        JDialog dialogo = new JDialog(parentFrame, "", true);
        dialogo.setSize(400, 440);
        dialogo.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("",SwingConstants.CENTER);
        titulo.setFont(new Font("Lexend", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(25, 15, 15, 15));
        titulo.setForeground(VistaVentana.COLOR_ALERTA);
        titulo.setBackground(VistaVentana.COLOR_PRIMARIO);
        titulo.setHorizontalTextPosition(0);
        titulo.setText("<html>ALGUNO DE LOS DATOS <br> INTRODUCIDOS ES INCORRECTO</html>");
        dialogo.add(titulo);

        JLabel txt = new JLabel();
        txt.setFont(new Font("Lexend", Font.BOLD, 12));
        txt.setBorder(new EmptyBorder(0, 15, 25, 15));
        txt.setForeground(VistaVentana.COLOR_SECUNDARIO);
        txt.setBackground(VistaVentana.COLOR_PRIMARIO);
        txt.setHorizontalAlignment(0);
        txt.setText("<html><p style=\"color: rgb(227, 111, 111);\">CANTIDAD:</p> DEBE CONTENER UN  <br>VALOR NUMÉRICO DIFERENTE A 0. <br>" +
                " LA OPERACIÓN NO PUEDE <br> DEJAR LA CUENTA EN NEGATIVO. " +
                "<br><p style=\"color: rgb(227, 111, 111);\">CATEGORÍA:</p>  DEBE CONTENER UNA CADENA <br>ALFANUMÉRICA QUE NO SUPERE LOS 20 <br>" +
                "CARÁCTERES (NO PUEDE QUEDAR VACÍA)" +
                "<br><p style=\"color: rgb(227, 111, 111);\">ASUNTO:</p>  DEBE CONTENER UNA CADENA <br>ALFANUMÉRICA QUE NO SUPERE LOS 50 <br>" +
                "CARÁCTERES (NO PUEDE QUEDAR VACÍA)" +
                "</html>");
        dialogo.add(txt);

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
        updateOperacion();
    }
}
