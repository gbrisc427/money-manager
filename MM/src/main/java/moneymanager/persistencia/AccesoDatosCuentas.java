package moneymanager.persistencia;

import moneymanager.business.Cuenta;
import moneymanager.business.CuentaManager;

import java.io.*;

public class AccesoDatosCuentas implements AccesoDatos {
    private static AccesoDatosCuentas instance = null;

    private AccesoDatosCuentas(){}

    public static AccesoDatosCuentas getInstance(){
        if(instance == null) instance = new AccesoDatosCuentas();
        return instance;
    }

    public void escribirCsv(String ruta) throws IOException {
        CuentaManager CM = CuentaManager.getInstancia();
        PrintWriter escritor = null;
        try{
            escritor = new PrintWriter(new FileWriter(ruta, false));
            for (Cuenta cuenta : CM.getCuentas()) {
                escritor.println(cuenta.getId()+";"+cuenta.getNombre().toUpperCase()+";"+cuenta.getSaldo());
            }
        }catch (IOException e){
            throw e;
        }finally {
            if (escritor != null) escritor.close();
        }
    }

    public void leerCsv(String ruta) throws IOException{
        CuentaManager CM = CuentaManager.getInstancia();
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new FileReader(ruta));
            String line;

            while ((line = lector.readLine()) != null) {
                String[] array = line.split(";");
                CM.crearCuenta(array[0],array[1], Float.parseFloat(array[2]));
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (lector != null) lector.close();
        }
    }
}
