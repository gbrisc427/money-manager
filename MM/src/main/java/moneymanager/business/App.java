package moneymanager.business;


import moneymanager.vista.VistaVentana;

import static moneymanager.vista.VistaVentana.*;

public class App {

    private final CuentaManager CM;
    private final OperacionesManager OM;
    private final AjustesManager AM;
    private VistaVentana VV;


    public App(){
        CM = CuentaManager.getInstancia();
        OM = OperacionesManager.getInstancia();
        AM = AjustesManager.getInstancia();
    }

    public void init(){
        CM.leerCSV();
        OM.leerCSV();
        AM.leerCSV();
        VV = getInstancia();
        VV.initVentana();
    }


    public void start(){
        while (ventanaAbierta) {
            Thread.onSpinWait();
        }
    }


    public void end(){
        VV.guardarColores();
        OM.escribirCSV();
        CM.escribirCSV();
        AM.escribirCSV();
        System.out.println("end");
    }

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
        app.end();
    }
}
