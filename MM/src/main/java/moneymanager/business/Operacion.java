package moneymanager.business;

import java.util.Date;

public abstract class Operacion {

    private Date fecha;
    private String motivo;
    private float cantidad;
    private String categoria;

    public Operacion(Date fecha, String motivo, float cantidad, String categoria) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.cantidad = cantidad;
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


}
