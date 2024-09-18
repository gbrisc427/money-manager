package moneymanager.business;

import java.util.Date;

public class Transferencia extends  Operacion{

    private final TOperacion tipoOperacion = TOperacion.TRANSFERENCIA;
    private Cuenta cuentaRemitente;
    private Cuenta cuentaDestino;

    public Transferencia(Date fecha, String motivo, float cantidad, Cuenta remitente, Cuenta destino, String categoria) {
        super(fecha, motivo, cantidad, categoria);
        this.cuentaRemitente = remitente;
        this.cuentaDestino = destino;
    }

    public TOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public Cuenta getCuentaRemitente() {
        return cuentaRemitente;
    }
}
