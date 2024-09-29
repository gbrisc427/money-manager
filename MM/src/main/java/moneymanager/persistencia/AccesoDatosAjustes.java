package moneymanager.persistencia;

import moneymanager.business.AjustesManager;

import java.awt.*;
import java.io.*;

public class AccesoDatosAjustes implements AccesoDatos {
    private static AccesoDatosAjustes instance = null;

    private AccesoDatosAjustes(){}

    public static AccesoDatosAjustes getInstance(){
        if(instance == null) instance = new AccesoDatosAjustes();
        return instance;
    }

    public void escribirCsv(String ruta) throws IOException {
        AjustesManager AM = AjustesManager.getInstancia();
        PrintWriter escritor = null;
        try{
            escritor = new PrintWriter(new FileWriter(ruta, false));
            for (Color color : AM.getCOLORES()) {
                escritor.println(color.getRed() + ";" + color.getGreen() + ";" + color.getBlue());
            }
        }catch (IOException e){
            throw e;
        }finally {
            if (escritor != null) escritor.close();
        }
    }

    public void leerCsv(String ruta) throws IOException{
        AjustesManager AM = AjustesManager.getInstancia();
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new FileReader(ruta));
            String line;
            while ((line = lector.readLine()) != null) {
                String[] rgb = line.split(";");
                AM.declararColores(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (lector != null) lector.close();
        }
    }
}

