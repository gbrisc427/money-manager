package moneymanager.vista.paneles;

import moneymanager.business.CuentaManager;
import moneymanager.business.LimitDocument;
import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelModificarCuenta extends JPanel implements Panel {

    private static panelModificarCuenta instancia = null;
    private final JButton BOTON_NOMBRE_CUENTA;
    private final JTextField FIELD_NUEVO_NOMBRE;
    private final JLabel ETIQUETA_INFO;

    private boolean cuentaEliminada;

    public static panelModificarCuenta getInstancia() {
        if (instancia == null) {
            instancia = new panelModificarCuenta();
        }
        return instancia;
    }

    private panelModificarCuenta(){
        CuentaManager CM = CuentaManager.getInstancia();

        String nombreCuenta = CM.getNombreCuenta();

        this.setLayout(new BorderLayout());
        this.setVisible(false);

        BOTON_NOMBRE_CUENTA = new JButton(nombreCuenta);
        BOTON_NOMBRE_CUENTA.setFont(new Font("Lexend", Font.BOLD, 30));
        BOTON_NOMBRE_CUENTA.setBorder(new EmptyBorder(25, 15, 0, 15));
        BOTON_NOMBRE_CUENTA.setForeground(new Color(164, 227, 111));
        BOTON_NOMBRE_CUENTA.setBackground(new Color(253, 242, 240));
        this.add(BOTON_NOMBRE_CUENTA, BorderLayout.NORTH);

        JButton BOTON_ELIMINAR_CUENTA = new JButton("ELIMINAR CUENTA");
        BOTON_ELIMINAR_CUENTA.setFont(new Font("Lexend", Font.BOLD, 15));
        BOTON_ELIMINAR_CUENTA.setBorder(new EmptyBorder(35, 15, 25, 15));
        BOTON_ELIMINAR_CUENTA.setForeground(new Color(227, 111, 111));
        BOTON_ELIMINAR_CUENTA.setBackground(new Color(253, 242, 240));
        this.add(BOTON_ELIMINAR_CUENTA, BorderLayout.SOUTH);

        JPanel PANEL_CAMBIO_NOMBRE = new JPanel();
        PANEL_CAMBIO_NOMBRE.setLayout(new FlowLayout(FlowLayout.LEFT));
        PANEL_CAMBIO_NOMBRE.setVisible(true);

        JLabel ETIQUETA_CAMBIO_NOMBRE = new JLabel("CAMBIAR NOMBRE:", SwingConstants.LEFT);
        ETIQUETA_CAMBIO_NOMBRE.setFont(new Font("Lexend", Font.BOLD, 20));
        ETIQUETA_CAMBIO_NOMBRE.setBorder(new EmptyBorder(25, 0, 15, 15));
        ETIQUETA_CAMBIO_NOMBRE.setForeground(new Color(164, 227, 111));
        PANEL_CAMBIO_NOMBRE.add(ETIQUETA_CAMBIO_NOMBRE);

        FIELD_NUEVO_NOMBRE = new JTextField(20);
        FIELD_NUEVO_NOMBRE.setFont(new Font("Lexend", Font.BOLD, 12));
        FIELD_NUEVO_NOMBRE.setBorder(new EmptyBorder(10, 10, 10, 10));
        FIELD_NUEVO_NOMBRE.setForeground(new Color(253, 242, 240));
        FIELD_NUEVO_NOMBRE.setBackground(new Color(164, 227, 111));
        FIELD_NUEVO_NOMBRE.setDocument(new LimitDocument(30));
        PANEL_CAMBIO_NOMBRE.add(FIELD_NUEVO_NOMBRE);

        JButton BOTON_GUARDAR_NOMBRE = new JButton("GUARDAR");
        BOTON_GUARDAR_NOMBRE.setBorder(new EmptyBorder(10, 10, 10, 10));
        BOTON_GUARDAR_NOMBRE.setForeground(new Color(253, 242, 240));
        PANEL_CAMBIO_NOMBRE.add(BOTON_GUARDAR_NOMBRE);

        ETIQUETA_INFO = new JLabel("<html>PORFAVOR INTRODUCE UN NOMBRE <br> PARA GUARDAR LOS CAMBIOS</html>");
        ETIQUETA_INFO.setFont(new Font("Lexend", Font.BOLD, 12));
        ETIQUETA_INFO.setBorder(new EmptyBorder(15, 0, 15, 15));
        ETIQUETA_INFO.setForeground(new Color(164, 227, 111));
        ETIQUETA_INFO.setVisible(false);
        PANEL_CAMBIO_NOMBRE.add(ETIQUETA_INFO);

        PANEL_CAMBIO_NOMBRE.setBorder(new EmptyBorder(10, 30, 20, 20));

        this.add(PANEL_CAMBIO_NOMBRE, BorderLayout.CENTER);

        BOTON_NOMBRE_CUENTA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarPanel();
                panelPrincipal.getInstancia().mostrarPanel();
                FIELD_NUEVO_NOMBRE.setText("");
            }
        });

        BOTON_GUARDAR_NOMBRE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textField = FIELD_NUEVO_NOMBRE.getText().toUpperCase();
                if (!textField.isEmpty()) {
                    ocultarPanel();
                    panelPrincipal.getInstancia().mostrarPanel();
                    ETIQUETA_INFO.setVisible(false);
                    CM.modificarCuenta(textField);
                    FIELD_NUEVO_NOMBRE.setText("");
                } else {
                    ETIQUETA_INFO.setVisible(true);
                }
            }
        });

        BOTON_ELIMINAR_CUENTA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpEliminarCuenta(ventana);
                FIELD_NUEVO_NOMBRE.setText("");
                if (cuentaEliminada){
                    ocultarPanel();
                    if (VistaVentana.CUENTA == null){
                        panelNoHayCuentas.getInstancia().mostrarPanel();
                    }else{
                        panelPrincipal.getInstancia().mostrarPanel();
                    }
                }
            }
        });
    }

    private void popUpEliminarCuenta(JFrame parentFrame) {
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
                    cuentaEliminada = true;
                }else{
                    cuentaEliminada = false;
                }
            }
        });
        dialogo.add(confirmar);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    private void updateInfo(){
        CuentaManager CM = CuentaManager.getInstancia();
        VistaVentana.CUENTA = CM.getCuentaActual();
        String nombre = CM.getNombreCuenta();
        BOTON_NOMBRE_CUENTA.setText(nombre);
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
        updateInfo();
    }
}
