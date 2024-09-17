package moneymanager.business;

import java.util.Date;

public abstract class Operacion {

    private Date fecha;
    private String motivo;
    private float cantidad;
    private String color;                       // COLOR EN CÓDIGO HEX
    private Categoria categoria;

    public Operacion(Date fecha, String motivo, float cantidad, String color, Categoria categoria) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.color = color;
        this.categoria = categoria;
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
