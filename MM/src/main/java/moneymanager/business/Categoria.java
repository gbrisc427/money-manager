package moneymanager.business;

import java.util.List;

public class Categoria {

    private String nombre;
    private List<Operacion> operaciones;

    public Categoria(String nombre, List<Operacion> operaciones) {
        this.nombre = nombre;
        this.operaciones = operaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Operacion> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<Operacion> operaciones) {
        this.operaciones = operaciones;
    }
}
