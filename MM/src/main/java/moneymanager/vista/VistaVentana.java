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
    public static Color COLOR_PRIMARIO;
    public static Color COLOR_SECUNDARIO;
    public static Color COLOR_ALERTA;
    public static Color COLOR_TRANSFERENCIA;
    public static Color COLOR_TRANSFERENCIA_REC;
    public static Color COLOR_TXT;


    private final panelMenu PANEL_MENU = panelMenu.getInstancia();

    public static VistaVentana getInstancia() {
        if (instancia == null) {
            establecerColores();
            initEstilo();
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
        Image icon = Toolkit.getDefaultToolkit().getImage(VistaVentana.class.getResource("/icono.png"));
        this.setIconImage(icon);


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
        bienvenidaTitulo.setForeground(COLOR_SECUNDARIO);
        bienvenidaTitulo.setBackground(COLOR_PRIMARIO);
        bienvenidaTitulo.setHorizontalAlignment(0);
        bienvenidaTitulo.setText("¡BIENVENIDO!");
        dialogo.add(bienvenidaTitulo);

        JLabel bienvenidaTexto = new JLabel();
        bienvenidaTexto.setFont(new Font("Lexend", Font.BOLD, 12));
        bienvenidaTexto.setBorder(new EmptyBorder(15, 15, 25, 15));
        bienvenidaTexto.setForeground(COLOR_SECUNDARIO);
        bienvenidaTexto.setBackground(COLOR_PRIMARIO);
        bienvenidaTexto.setHorizontalAlignment(0);
        bienvenidaTexto.setText("<html>BIENVENIDO A MONEY-MANAGER. <br> CIERRE ESTA VENTANA PARA COMENZAR. <br> SE CREARÁ UNA CUENTA POR <br> DEFECTO AUTOMÁTICAMENTE.</html>");
        dialogo.add(bienvenidaTexto);

        dialogo.setLocationRelativeTo(parentFrame);
        dialogo.setVisible(true);
    }

    private static void establecerColores(){
        AjustesManager AM =AjustesManager.getInstancia();
        COLOR_PRIMARIO = AM.getCOLORES().get(0);
        COLOR_SECUNDARIO = AM.getCOLORES().get(1);
        COLOR_ALERTA = AM.getCOLORES().get(2);
        COLOR_TRANSFERENCIA = AM.getCOLORES().get(3);
        COLOR_TXT = AM.getCOLORES().get(4);
    }

    public void guardarColores(){
        AjustesManager AM =AjustesManager.getInstancia();
        AM.getCOLORES().set(0, COLOR_PRIMARIO);
        AM.getCOLORES().set(1, COLOR_SECUNDARIO);
        AM.getCOLORES().set(2, COLOR_ALERTA);
        AM.getCOLORES().set(3, COLOR_TRANSFERENCIA);
        AM.getCOLORES().set(4, COLOR_TXT);
    }

    private static void initEstilo() {
        UIManager.put("Label.foreground", COLOR_TXT);
        UIManager.put("Button.background", COLOR_SECUNDARIO);
        UIManager.put("Button.foreground",COLOR_PRIMARIO);
        UIManager.put("Button.font", new Font("Lexend", Font.BOLD, 12));
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 15, 10, 15));
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Button.select",COLOR_PRIMARIO);
        UIManager.put("Panel.background",COLOR_PRIMARIO);
        UIManager.put("CheckBox.select",COLOR_PRIMARIO);
        UIManager.put("CheckBox.focus", COLOR_PRIMARIO);
        UIManager.put("ComboBox.selectionBackground", COLOR_SECUNDARIO);
        UIManager.put("ComboBox.selectionForeground", COLOR_PRIMARIO);
        UIManager.put("ComboBox.focus", COLOR_PRIMARIO);
        UIManager.put("ComboBox.buttonBackground", COLOR_PRIMARIO);
        UIManager.put("ComboBox.buttonDarkShadow", COLOR_PRIMARIO);
        UIManager.put("ComboBox.buttonHighlight", COLOR_PRIMARIO);
        UIManager.put("ComboBox.disabledBackground", COLOR_PRIMARIO);

        UIManager.put("ScrollBar.thumb", COLOR_SECUNDARIO);
        UIManager.put("ScrollBar.thumbDarkShadow", COLOR_SECUNDARIO);
        UIManager.put("ScrollBar.thumbHighlight", COLOR_SECUNDARIO);
        UIManager.put("ScrollBar.thumbShadow", COLOR_SECUNDARIO);
        UIManager.put("ScrollBar.track", COLOR_PRIMARIO);
        UIManager.put("ScrollBar.trackHighlight", COLOR_PRIMARIO);
        UIManager.put("ScrollBar.trackDarkShadow", COLOR_PRIMARIO);
        UIManager.put("ScrollBar.trackShadow", COLOR_PRIMARIO);
        UIManager.put("ScrollBar.foreground", COLOR_PRIMARIO);
        UIManager.put("ScrollBar.background", COLOR_PRIMARIO);
        UIManager.put("ScrollBar.incrementButtonSize", new Dimension(0, 0));
        UIManager.put("ScrollBar.decrementButtonSize", new Dimension(0, 0));
    }

}