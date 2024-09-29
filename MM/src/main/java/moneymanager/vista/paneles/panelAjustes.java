package moneymanager.vista.paneles;

import moneymanager.vista.VistaVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class panelAjustes extends JPanel implements Panel{

    private static panelAjustes instancia = null;

    private final  JComboBox<String> COMBOBOX_MODO;
    private final  JComboBox<String> COMBOBOX_COLORES;


    public static panelAjustes getInstancia() {
        if (instancia == null) {
            instancia = new panelAjustes();
        }
        return instancia;
    }

    private panelAjustes(){

        this.setLayout(new BorderLayout());
        this.setVisible(false);

        JLabel ETIQUETA_TITULO = new JLabel("AJUSTES", SwingConstants.CENTER);
        ETIQUETA_TITULO.setFont(new Font("Lexend", Font.BOLD, 25));
        ETIQUETA_TITULO.setBorder(new EmptyBorder(35, 15, 20, 15));
        ETIQUETA_TITULO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        ETIQUETA_TITULO.setBackground(VistaVentana.COLOR_PRIMARIO);
        ETIQUETA_TITULO.setVisible(true);
        this.add(ETIQUETA_TITULO,BorderLayout.NORTH);

        JPanel PANEL_AJUSTES = new JPanel();
        PANEL_AJUSTES.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        PANEL_AJUSTES.setVisible(true);

        JLabel ETIQUETA_MODO = new JLabel("<html>MODO: </html>", SwingConstants.LEFT);
        ETIQUETA_MODO.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_MODO.setBorder(new EmptyBorder(0,15,10,0));
        ETIQUETA_MODO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        PANEL_AJUSTES.add(ETIQUETA_MODO, gbc);

        String[] modo = {"CLARO", "OSCURO"};
        COMBOBOX_MODO = new JComboBox<>(modo);
        COMBOBOX_MODO.setFont(new Font("Lexend", Font.BOLD, 15));
        COMBOBOX_MODO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        COMBOBOX_MODO.setBackground(VistaVentana.COLOR_PRIMARIO);
        COMBOBOX_MODO.setBorder(new EmptyBorder(0,15,10,0));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        COMBOBOX_MODO.setSelectedItem(null);
        PANEL_AJUSTES.add(COMBOBOX_MODO, gbc);

        JLabel ETIQUETA_COLOR = new JLabel("<html>COLOR: </html>", SwingConstants.LEFT);
        ETIQUETA_COLOR.setFont(new Font("Lexend", Font.BOLD, 15));
        ETIQUETA_COLOR.setBorder(new EmptyBorder(0,15,10,0));
        ETIQUETA_COLOR.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        PANEL_AJUSTES.add(ETIQUETA_COLOR, gbc);

        String[] color = {"VERDE", "MORADO", "NARANJA", "AZUL"};
        COMBOBOX_COLORES = new JComboBox<>(color);
        COMBOBOX_COLORES.setFont(new Font("Lexend", Font.BOLD, 15));
        COMBOBOX_COLORES.setForeground(VistaVentana.COLOR_SECUNDARIO);
        COMBOBOX_COLORES.setBackground(VistaVentana.COLOR_PRIMARIO);
        COMBOBOX_COLORES.setBorder(new EmptyBorder(0,15,10,0));
        COMBOBOX_COLORES.setSelectedItem(null);
        COMBOBOX_COLORES.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        PANEL_AJUSTES.add(COMBOBOX_COLORES, gbc);

        JLabel ETIQUETA_INFO = new JLabel("<html>PARA GUARDAR LOS CAMBIOS CIERRA Y VUELVE HA ABRIR EL PROGRAMA </html>", SwingConstants.LEFT);
        ETIQUETA_INFO.setFont(new Font("Lexend", Font.BOLD, 10));
        ETIQUETA_INFO.setBorder(new EmptyBorder(0,15,10,0));
        ETIQUETA_INFO.setForeground(VistaVentana.COLOR_SECUNDARIO);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        PANEL_AJUSTES.add(ETIQUETA_INFO, gbc);

        PANEL_AJUSTES.setBorder(new EmptyBorder(15,30,15,15));
        this.add(PANEL_AJUSTES, BorderLayout.CENTER);

        COMBOBOX_MODO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(COMBOBOX_MODO.getSelectedItem(), "OSCURO")){
                    COMBOBOX_COLORES.setEnabled(false);
                    COMBOBOX_COLORES.setSelectedItem(null);
                    VistaVentana.COLOR_PRIMARIO = new Color(68, 72, 71);
                    VistaVentana.COLOR_SECUNDARIO = new Color(161, 160, 160);
                    VistaVentana.COLOR_TXT = new Color(228, 222, 221);
                }else{
                    COMBOBOX_COLORES.setEnabled(true);
                    COMBOBOX_COLORES.setSelectedIndex(0);
                    VistaVentana.COLOR_PRIMARIO = new Color(253, 242, 240);
                    VistaVentana.COLOR_TXT = new Color(68, 72, 71);
                }
            }
        });

        COMBOBOX_COLORES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(COMBOBOX_COLORES.getSelectedItem(), "VERDE")){
                    VistaVentana.COLOR_SECUNDARIO = new Color(164, 227, 111);
                }
                if (Objects.equals(COMBOBOX_COLORES.getSelectedItem(), "MORADO")){
                    VistaVentana.COLOR_SECUNDARIO = new Color(160, 91, 177);
                }
                if (Objects.equals(COMBOBOX_COLORES.getSelectedItem(), "AZUL")){
                    VistaVentana.COLOR_SECUNDARIO = new Color(100, 137, 218);
                }
                if (Objects.equals(COMBOBOX_COLORES.getSelectedItem(), "NARANJA")){
                    VistaVentana.COLOR_SECUNDARIO = new Color(214, 140, 46);
                }
            }
        });

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
    }
}
