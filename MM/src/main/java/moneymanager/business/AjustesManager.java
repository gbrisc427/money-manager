package moneymanager.business;

import moneymanager.persistencia.AccesoDatos;
import moneymanager.persistencia.AccesoDatosAjustes;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AjustesManager {

    private final List<Color> COLORES = new ArrayList<>();
    private static  AjustesManager instancia = null;
    private final AccesoDatos AD;

    private AjustesManager(){
        AD = AccesoDatosAjustes.getInstance();
    }

    public static AjustesManager getInstancia() {
        if(instancia == null) instancia = new AjustesManager();
        return instancia;
    }

    public void leerCSV(){
        try {
            AD.leerCsv("AJUSTES/ajustes.csv");}
        catch (IOException ignored){

        }
    }

    public void escribirCSV(){
        try {
            AD.escribirCsv("AJUSTES/ajustes.csv");}
        catch (IOException ignored){

        }
    }

    public  void declararColores(int r, int g, int b){
        this.COLORES.add(new Color(r,g,b));
    }


    public List<Color> getCOLORES() {
        return COLORES;
    }
}
