package moneymanager.business;

import java.time.LocalDateTime;

public abstract class Operacion {

    private final String id;
    private final LocalDateTime fecha;
    private  String motivo;
    private  float cantidad;
    private  String categoria;

    public Operacion(String id, LocalDateTime fecha, String motivo, float cantidad, String categoria) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId() {
        return id;
    }

    public void setMotivo(String motivo){
        this.motivo = motivo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public abstract TOperacion getTOperacion();



    }
