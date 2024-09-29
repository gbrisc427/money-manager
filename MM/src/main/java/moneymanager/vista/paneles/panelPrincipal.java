package moneymanager.vista.paneles;

import moneymanager.business.CuentaManager;
import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelPrincipal extends JPanel implements Panel{

    private static panelPrincipal instancia = null;

    private final JButton BOTON_NOMBRE_CUENTA;
    private final JLabel ETIQUETA_SALDO;
    private final JLabel ETIQUETA_ID;


    public static panelPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new panelPrincipal();
        }
        return instancia;
    }

    private panelPrincipal(){
        CuentaManager CM = CuentaManager.getInstancia();

        this.setLayout(new BorderLayout());
        this.setVisible(false);

        String nombreCuenta = CM.getNombreCuenta();
        BOTON_NOMBRE_CUENTA = new JButton(nombreCuenta);
        BOTON_NOMBRE_CUENTA.setFont(new Font("Lexend", Font.BOLD, 30));
        BOTON_NOMBRE_CUENTA.setBorder(new EmptyBorder(35, 15, 0, 15));
        BOTON_NOMBRE_CUENTA.setForeground(new Color(164, 227, 111));
        BOTON_NOMBRE_CUENTA.setBackground(new Color(253, 242, 240));
        this.add(BOTON_NOMBRE_CUENTA, BorderLayout.NORTH);

        String saldo = CM.getSaldo();
        ETIQUETA_SALDO = new JLabel(saldo, SwingConstants.CENTER);
        ETIQUETA_SALDO.setFont(new Font("Lexend", Font.BOLD, 70));
        ETIQUETA_SALDO.setBorder(new EmptyBorder(0, 15, 50, 15));
        this.add(ETIQUETA_SALDO, BorderLayout.CENTER);

        String id = "#"+CM.getCuentaActual().getId();
        ETIQUETA_ID = new JLabel(id, SwingConstants.CENTER);
        ETIQUETA_ID.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_ID.setBorder(new EmptyBorder(0, 15, 20, 15));
        this.add(ETIQUETA_ID, BorderLayout.SOUTH);

        BOTON_NOMBRE_CUENTA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelModificarCuenta.getInstancia().mostrarPanel();
                ocultarPanel();
            }
        });
    }

    private void updateInfo(){
        CuentaManager CM = CuentaManager.getInstancia();
        VistaVentana.CUENTA = CM.getCuentaActual();
        String nombre = CM.getNombreCuenta();
        String saldo = CM.getSaldo();
        String id = CM.getId();
        BOTON_NOMBRE_CUENTA.setText(nombre);
        ETIQUETA_SALDO.setText(saldo);
        ETIQUETA_ID.setText(id);
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
