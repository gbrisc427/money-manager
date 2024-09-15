package moneymanager.business;

import java.util.Date;

public class Ingreso extends  Operacion{

    private final TOperacion tipoOperacion = TOperacion.INGRESO;
    private final Cuenta cuenta;

    public Ingreso(Date fecha, String motivo, float cantidad, String color, Cuenta cuenta) {
        super( fecha, motivo, cantidad, color);
        this.cuenta = cuenta;
    }

    public TOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }
}
