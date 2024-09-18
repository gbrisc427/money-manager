package moneymanager.persistencia;

import java.io.IOException;

public interface AccesoDatos {

    void escribirCsv(String ruta) throws IOException;
    void leerCsv(String ruta) throws IOException;
}