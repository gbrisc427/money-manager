package moneymanager.business;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Cuenta {

    private String id;
    private String nombre;
    private float saldo;
    private List<Operacion> historial;

    public Cuenta(String id, String nombre, float saldo, ArrayList<Operacion> historial) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.historial = historial;
        this.id = id;
    }

    public void aniadirOperacion(Operacion operacion){
        if (operacionNueva(operacion)) {
            this.saldo += operacion.getCantidad();
            historial.add(operacion);
        }
    }

    public void modificarSaldo(float cantAntigua, float cantNueva){
        this.saldo = this.saldo - cantAntigua + cantNueva;
    }

    public void cargarOperacion(Operacion operacion){
        if (operacionNueva(operacion)) {
            historial.add(operacion);
        }
    }

    private boolean operacionNueva(Operacion operacion){
        boolean nuevo = true;
        for (Operacion aux : this.historial){
            if (operacion.equals(aux)){
                nuevo = false;
            }
        }
        return nuevo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public List<Operacion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Operacion> historial) {
        this.historial = historial;
    }

    public String getId() {
        return id;
    }
}
