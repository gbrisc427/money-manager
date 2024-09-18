package moneymanager.business;

import java.util.ArrayList;
import java.util.List;

public class OperacionesManager {
    
    private final List<Operacion> operaciones = new ArrayList<>();
    private static  OperacionesManager instancia = null;
    private final CuentaManager CM;
    

    private OperacionesManager(){
        CM = CuentaManager.getInstancia();
    }

    public static OperacionesManager getInstancia() {
        if(instancia == null) instancia = new OperacionesManager();
        return instancia;
    }
    
    public void registrarOperacion(){
        
    }

    public void modificarOperacion(){

    }

    public void eliminarOperacion(){

    }
}
