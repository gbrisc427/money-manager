package moneymanager.business;


import moneymanager.vista.VistaVentana;

import static moneymanager.vista.VistaVentana.*;

public class App {

    private final CuentaManager CM;
    private final VistaVentana VV;


    public App(){
        CM = CuentaManager.getInstancia();
        VV = VistaVentana.getInstancia();
    }

    public void init(){
        // CARGAR EL PROGRAMA CON LOS FICHEROS
    }


    public void start(){
        while (ventanaAbierta) {
            Thread.onSpinWait();
        }
    }


    public void end(){
        System.out.println("end");
    }

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
        app.end();
    }
}
