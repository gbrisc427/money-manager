package moneymanager.business;

import java.io.File;
import java.util.HashSet;

public class Cuenta {

    private int id;
    private String nombre;
    private float saldo;
    private HashSet<Operacion> historial;

    public Cuenta(int id, String nombre, float saldo, HashSet<Operacion> historial) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
        this.historial = historial;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public HashSet<Operacion> getHistorial() {
        return historial;
    }


}
