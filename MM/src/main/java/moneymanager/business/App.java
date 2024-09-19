package moneymanager.business;


import moneymanager.vista.VistaVentana;

import static moneymanager.vista.VistaVentana.*;

public class App {

    private final CuentaManager CM;
    private final OperacionesManager OM;
    private VistaVentana VV;


    public App(){
        CM = CuentaManager.getInstancia();
        OM = OperacionesManager.getInstancia();
    }

    public void init(){
        OM.leerCSV();
        CM.leerCSV();
        CM.init();
        VV = VistaVentana.getInstancia();
    }


    public void start(){
        while (ventanaAbierta) {
            Thread.onSpinWait();
        }
    }


    public void end(){
        OM.escribirCSV();
        CM.escribirCSV();
        System.out.println("end");
    }

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
        app.end();
    }
}
