package moneymanager.business;

import java.time.LocalDateTime;

public class Gasto extends Operacion{

    private final TOperacion tipoOperacion = TOperacion.GASTO;
    private Cuenta cuenta;

    public Gasto(String id,LocalDateTime fecha, String motivo, float cantidad, Cuenta cuenta, String categoria) {
        super(id, fecha, motivo, cantidad, categoria);
        this.cuenta = cuenta;
    }

    public TOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    @Override
    public TOperacion getTOperacion() {
        return tipoOperacion;
    }
}
