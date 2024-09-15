package moneymanager.business;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Cuenta {

    private String nombre;
    private float saldo;
    private List<Operacion> historial;

    public Cuenta(String nombre, float saldo, ArrayList<Operacion> historial) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.historial = historial;
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
}
