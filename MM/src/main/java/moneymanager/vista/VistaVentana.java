package moneymanager.vista;


import moneymanager.business.*;
import moneymanager.vista.paneles.panelMenu;
import moneymanager.vista.paneles.panelPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class VistaVentana extends JFrame {

    private static VistaVentana instancia = null;
    public static volatile boolean ventanaAbierta = true;

    public static TOperacion TIPO_OPERACION;
    public static String ID_OPERACION;
    public static Cuenta CUENTA;

    private final panelMenu PANEL_MENU = panelMenu.getInstancia();

    public static VistaVentana getInstancia() {
        if (instancia == null) {
            cambiarEstilo();
            instancia = new VistaVentana();
        }
        return instancia;
    }

    private VistaVentana() {
        setTitle("");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        // CONTROL DEL CIERRE DE LA VENTANA
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                VistaVentana.this.dispose();
                ventanaAbierta = false;
            }
        });
    }

    public void initVentana(){
        CuentaManager CM = CuentaManager.getInstancia();
        CUENTA = CM.getCuentaActual();
        if (CM.inicializar()){
            popUpBienvenida(VistaVentana.this);
        }
        panelPrincipal PANEL_PRINCIPAL = panelPrincipal.getInstancia();
        PANEL_MENU.mostrarPanel();
        PANEL_PRINCIPAL.mostrarPanel();
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