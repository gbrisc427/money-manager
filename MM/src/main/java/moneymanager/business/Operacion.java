package moneymanager.business;

import java.time.LocalDateTime;

public abstract class Operacion {

    private final String id;
    private final LocalDateTime fecha;
    private final String motivo;
    private final float cantidad;
    private final String categoria;

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

    public abstract TOperacion getTOperacion();



    }
