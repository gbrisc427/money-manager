package moneymanager.vista.paneles;

import moneymanager.business.*;
import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class panelRealizarOperacion  extends JPanel implements Panel {

    private static panelRealizarOperacion instancia = null;

    private final  JComboBox<String> COMBOBOX_CUENTAS_TRASNFERENCIA;
    private final JCheckBox CHECKBOX_TRANSFERENCIA;
    private final JTextField FIELD_CANTIDAD;
    private final JTextField FIELD_CATEGORIA;
    private final JTextField FIELD_ASUNTO;

    public static panelRealizarOperacion getInstancia() {
        if (instancia == null) {
            instancia = new panelRealizarOperacion();
        }
        return instancia;
    }

    private panelRealizarOperacion(){
        CuentaManager CM = CuentaManager.getInstancia();

        this.setLayout(new BorderLayout());
        this.setVisible(false);

        JLabel ETIQUETA_TITULO = new JLabel("REALIZAR OPERACIÓN", SwingConstants.CENTER);
        ETIQUETA_TITULO.setFont(new Font("Lexend", Font.BOLD, 25));
        ETIQUETA_TITULO.setBorder(new EmptyBorder(35, 15, 20, 15));
        ETIQUETA_TITULO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_TITULO.setBackground(VistaVentana.COLOR_PRIMARIO);
        ETIQUETA_TITULO.setVisible(true);
        this.add(ETIQUETA_TITULO,BorderLayout.NORTH);

        JPanel PANEL_REALIZAR_OPERACION = new JPanel();
        PANEL_REALIZAR_OPERACION.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        PANEL_REALIZAR_OPERACION.setVisible(true);

        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel ETIQUETA_SUBTITULO = new JLabel("<html> INTRODUCE LOS VALORES: </html>", SwingConstants.LEFT);
        ETIQUETA_SUBTITULO.setFont(new Font("Lexend", Font.BOLD, 17));
        ETIQUETA_SUBTITULO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_REALIZAR_OPERACION.add(ETIQUETA_SUBTITULO, gbc);

        gbc.insets = new Insets(5, 0, 5, 5);

        JLabel ETIQUETA_CANTIDAD = new JLabel("<html>CANTIDAD: </html>", SwingConstants.LEFT);
        ETIQUETA_CANTIDAD.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_CANTIDAD.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        PANEL_REALIZAR_OPERACION.add(ETIQUETA_CANTIDAD, gbc);

        FIELD_CANTIDAD = new JTextField(4);
        FIELD_CANTIDAD.setFont(new Font("Lexend", Font.BOLD, 13));
        FIELD_CANTIDAD.setBorder(new EmptyBorder(8, 8, 8, 8));
        FIELD_CANTIDAD.setForeground(VistaVentana.COLOR_PRIMARIO);
        FIELD_CANTIDAD.setBackground(VistaVentana.COLOR_SECUNDARIO);
        FIELD_CANTIDAD.setText("0.0");
        FIELD_CANTIDAD.setDocument(new NumericDocument(6));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_REALIZAR_OPERACION.add(FIELD_CANTIDAD, gbc);

        JLabel ETIQUETA_CATEGORIA = new JLabel("<html>CATEGORÍA: </html>", SwingConstants.LEFT);
        ETIQUETA_CATEGORIA.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_CATEGORIA.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        PANEL_REALIZAR_OPERACION.add(ETIQUETA_CATEGORIA, gbc);

        FIELD_CATEGORIA = new JTextField(15);
        FIELD_CATEGORIA.setFont(new Font("Lexend", Font.BOLD, 13));
        FIELD_CATEGORIA.setBorder(new EmptyBorder(8, 8, 8, 8));
        FIELD_CATEGORIA.setForeground(VistaVentana.COLOR_PRIMARIO);
        FIELD_CATEGORIA.setBackground(VistaVentana.COLOR_SECUNDARIO);
        FIELD_CATEGORIA.setDocument(new LimitDocument(20));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_REALIZAR_OPERACION.add(FIELD_CATEGORIA, gbc);

        JLabel ETIQUETA_ASUNTO = new JLabel("<html>ASUNTO: </html>", SwingConstants.LEFT);
        ETIQUETA_ASUNTO.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_ASUNTO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        PANEL_REALIZAR_OPERACION.add(ETIQUETA_ASUNTO, gbc);

        FIELD_ASUNTO = new JTextField(15);
        FIELD_ASUNTO.setFont(new Font("Lexend", Font.BOLD, 13));
        FIELD_ASUNTO.setBorder(new EmptyBorder(8, 8, 8, 8));
        FIELD_ASUNTO.setForeground(VistaVentana.COLOR_PRIMARIO);
        FIELD_ASUNTO.setBackground(VistaVentana.COLOR_SECUNDARIO);
        FIELD_ASUNTO.setDocument(new LimitDocument(50));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        PANEL_REALIZAR_OPERACION.add(FIELD_ASUNTO, gbc);

        gbc.insets = new Insets(10, 0, 10, 5);

        CHECKBOX_TRANSFERENCIA = new JCheckBox("TRANSEFERENCIA");
        CHECKBOX_TRANSFERENCIA.setFont(new Font("Lexend", Font.BOLD, 15));
        CHECKBOX_TRANSFERENCIA.setForeground(VistaVentana.COLOR_SECUNDARIO);
        CHECKBOX_TRANSFERENCIA.setBackground(VistaVentana.COLOR_PRIMARIO);
        CHECKBOX_TRANSFERENCIA.setBorder(new EmptyBorder(1,1,1,1));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        PANEL_REALIZAR_OPERACION.add(CHECKBOX_TRANSFERENCIA, gbc);

        String[] opciones = new String[CM.getCuentasOpTransf().length];
        COMBOBOX_CUENTAS_TRASNFERENCIA = new JComboBox<>(opciones);
        COMBOBOX_CUENTAS_TRASNFERENCIA.setFont(new Font("Lexend", Font.BOLD, 15));
        COMBOBOX_CUENTAS_TRASNFERENCIA.setForeground(VistaVentana.COLOR_SECUNDARIO);
        COMBOBOX_CUENTAS_TRASNFERENCIA.setBackground(VistaVentana.COLOR_PRIMARIO);
        COMBOBOX_CUENTAS_TRASNFERENCIA.setBorder(new EmptyBorder(0,0,0,0));
        if (opciones.length == 0){
            COMBOBOX_CUENTAS_TRASNFERENCIA.setEnabled(false);
            COMBOBOX_CUENTAS_TRASNFERENCIA.setVisible(false);
            CHECKBOX_TRANSFERENCIA.setVisible(false);
        }else{
            COMBOBOX_CUENTAS_TRASNFERENCIA.setSelectedIndex(0);
            COMBOBOX_CUENTAS_TRASNFERENCIA.setEnabled(false);
        }
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        PANEL_REALIZAR_OPERACION.add(COMBOBOX_CUENTAS_TRASNFERENCIA, gbc);

        PANEL_REALIZAR_OPERACION.setBorder(new EmptyBorder(15,30,15,15));
        this.add(PANEL_REALIZAR_OPERACION, BorderLayout.CENTER);

        JPanel PANEL_BOTONES = new JPanel();
        PANEL_BOTONES.setLayout(new FlowLayout(FlowLayout.CENTER));
        PANEL_BOTONES.setVisible(true);

        JButton BOTON_GUARDAR = new JButton("ACEPTAR");
        BOTON_GUARDAR.setBorder(new EmptyBorder(9, 9,9, 9));
        BOTON_GUARDAR.setForeground(VistaVentana.COLOR_PRIMARIO);
        PANEL_BOTONES.add(BOTON_GUARDAR);

        JButton BOTON_CANCELAR = new JButton("CANCELAR");
        BOTON_CANCELAR.setBorder(new EmptyBorder(9, 9, 9, 9));
        BOTON_CANCELAR.setForeground(VistaVentana.COLOR_PRIMARIO);
        BOTON_CANCELAR.setBackground(VistaVentana.COLOR_ALERTA);
        PANEL_BOTONES.add(BOTON_CANCELAR);

        PANEL_BOTONES.setBorder(new EmptyBorder(0,0,30,0));
        this.add(PANEL_BOTONES, BorderLayout.SOUTH);

        CHECKBOX_TRANSFERENCIA.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (opciones.length == 0){
                    COMBOBOX_CUENTAS_TRASNFERENCIA.setEnabled(false);
                }else{
                    COMBOBOX_CUENTAS_TRASNFERENCIA.setEnabled(true);
                    COMBOBOX_CUENTAS_TRASNFERENCIA.setSelectedIndex(0);
                }
            } else {
                COMBOBOX_CUENTAS_TRASNFERENCIA.setEnabled(false);
            }
        });

        BOTON_GUARDAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperacionesManager OM = OperacionesManager.getInstancia();
                if ( !FIELD_CANTIDAD.getText().isEmpty() && !FIELD_ASUNTO.getText().isEmpty()
                        && !FIELD_CANTIDAD.getText().isEmpty()) {
                    if (Float.parseFloat(FIELD_CANTIDAD.getText()) != 0) {
                        if (CHECKBOX_TRANSFERENCIA.isSelected()) {
                            COMBOBOX_CUENTAS_TRASNFERENCIA.getSelectedItem();
                            float res = VistaVentana.CUENTA.getSaldo() - Float.parseFloat(FIELD_CANTIDAD.getText());
                            if (res < 0 || Float.parseFloat(FIELD_CANTIDAD.getText()) < 0){
                                popUpDatosOp(ventana);
                            }else{
                                Cuenta cuentaDest = CM.getCuenta((String) COMBOBOX_CUENTAS_TRASNFERENCIA.getSelectedItem());
                                OM.registrarTransferencia(FIELD_ASUNTO.getText(), Float.parseFloat(FIELD_CANTIDAD.getText()),
                                        FIELD_CATEGORIA.getText(), cuentaDest);

                                ocultarPanel();
                                panelPrincipal.getInstancia().mostrarPanel();
                            }
                        } else {
                            float res = VistaVentana.CUENTA.getSaldo() + Float.parseFloat(FIELD_CANTIDAD.getText());
                            if (res < 0){
                                popUpDatosOp(ventana);
                            }else{
                                OM.registrarIngresoGasto(FIELD_ASUNTO.getText(), Float.parseFloat(FIELD_CANTIDAD.getText()),
                                        FIELD_CATEGORIA.getText());
                                ocultarPanel();
                                panelPrincipal.getInstancia().mostrarPanel();
                            }
                        }
                    } else {
                        popUpDatosOp(ventana);
                    }
                }else {
                    popUpDatosOp(ventana);
                }
                updatePanel();
            }
        });

        BOTON_CANCELAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarPanel();
                panelPrincipal.getInstancia().mostrarPanel();
                updatePanel();
            }
        });
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
        txt.setText("<html><p style=\"color: rgb(227, 111, 111);\">CANTIDAD:</p> DEBE CONTENER UN  <br>VALOR NUMÉRICO DIFERENTE A 0. <br> LA OPERACIÓN NO PUEDE <br> DEJAR LA CUENTA EN NEGATIVO. " +
                "<br><p style=\"color: rgb(227, 111, 111);\">CATEGORÍA:</p>  DEBE CONTENER UNA CADENA <br>ALFANUMÉRICA QUE NO SUPERE LOS 20 <br>CARÁCTERES (NO PUEDE QUEDAR VACÍA)" +
                "<br><p style=\"color: rgb(227, 111, 111);\">ASUNTO:</p>  DEBE CONTENER UNA CADENA <br>ALFANUMÉRICA QUE NO SUPERE LOS 50 <br>CARÁCTERES (NO PUEDE QUEDAR VACÍA)" +
                "<br><p style=\"color: rgb(227, 111, 111);\">TRANSFERENCIA:</p>  SI SE SELECCIONA <br>ESTA OPCIÓN, LA CANTIDAD DEBE DE <br>SER UN VALOR POSITIVO.</html>");
        dialogo.add(txt);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }


    private void updatePanel(){
        COMBOBOX_CUENTAS_TRASNFERENCIA.setEnabled(false);
        CHECKBOX_TRANSFERENCIA.setSelected(false);
        FIELD_ASUNTO.setText("");
        FIELD_CANTIDAD.setText("");
        FIELD_CATEGORIA.setText("");
        updateCuentasTransf();
    }

    private void updateCuentasTransf(){
        for (int i = COMBOBOX_CUENTAS_TRASNFERENCIA.getItemCount() - 1; i >= 0; i--){
            COMBOBOX_CUENTAS_TRASNFERENCIA.removeItemAt(i);
        }
        for (String opc : CuentaManager.getInstancia().getCuentasOpTransf()){
            System.out.println(CuentaManager.getInstancia().getCuentasOpTransf().length);
            COMBOBOX_CUENTAS_TRASNFERENCIA.addItem(opc);
        }
        COMBOBOX_CUENTAS_TRASNFERENCIA.setSelectedItem(null);
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
        updatePanel();
    }
}
