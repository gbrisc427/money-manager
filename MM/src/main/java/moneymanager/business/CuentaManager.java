package moneymanager.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CuentaManager {

    private List<Cuenta> cuentas = new ArrayList<>();
    private static  CuentaManager instancia = null;
    private Cuenta cuentaActual;

    private CuentaManager(){
        if (!cuentas.isEmpty()){
            this.cuentaActual = cuentas.get(0);
        }else this.cuentaActual = null;
    }

    public static CuentaManager getInstancia() {
        if(instancia == null) instancia = new CuentaManager();
        return instancia;
    }

    public void crearCuenta(){
        String nombre = "";   // MEDIANTE LA VISTA HACER VENTANA EMERGENTE QUE SOLICITE EL NOMBRE DE LA NUEVA CUENTA
        Cuenta cuenta = new Cuenta(nombre, 0, new ArrayList<>());
        cuentas.add(cuenta);
    }

    public void modificarCuenta(){
        if (cuentaActual != null){
            String nombre = ""; // MEDIANTE LA VISTA HACER VENTANA EMERGENTE QUE SOLICITE EL NUEVO NOMBRE DE LA CUENTA
            cuentaActual.setNombre(nombre);
        }else{
            // VISTA, MOSTRAR EN LA PANTALLA QUE NO HAY CUENTAS DISPONIBLES PARA MODIFICAR
        }
    }

    public void eliminarCuenta(){
        if (cuentaActual != null){
            // MOSTRAR VENTANA QUE PIDA INTRODUCIR EL NOMBRE DE LA CUENTA PARA ELIMINARLA
            cuentas.remove(cuentaActual);
            if (cuentas.isEmpty()){
                cuentaActual = null;
            }else{
                cuentaActual = cuentas.get(0);
            }
        }else{
            // VISTA, MOSTRAR EN LA PANTALLA QUE NO HAY CUENTAS DISPONIBLES PARA ELIMINAR
        }
    }

    public String getSaldo(){
        String saldo = "0.00€";
        if (cuentaActual != null){
            saldo = cuentaActual.getSaldo() + "€";
        }
        return saldo;
    }

        public String getNombreCuenta(){
        String nombre = "";
        if (cuentaActual != null){
            nombre = cuentaActual.getNombre();
        }
        return nombre;
    }


    public Cuenta getCuentaActual() {
        return cuentaActual;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
}
