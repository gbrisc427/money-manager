package moneymanager.business;

import java.util.Date;

public abstract class Operacion {

    private int id;
    private Date fecha;
    private String motivo;
    private float cantidad;
    private String color;                       // COLOR EN CÓDIGO HEX


    public Operacion(int id, Date fecha, String motivo, float cantidad, String color) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public String getColor() {
        return color;
    }

}
