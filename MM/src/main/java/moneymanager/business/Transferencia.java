package moneymanager.business;

import java.time.LocalDateTime;

public class Transferencia extends  Operacion{

    private final TOperacion tipoOperacion = TOperacion.TRANSFERENCIA;
    private final Cuenta cuentaRemitente;
    private final Cuenta cuentaDestino;

    public Transferencia(String id,LocalDateTime fecha, String motivo, float cantidad, Cuenta remitente, Cuenta destino, String categoria) {
        super(id, fecha, motivo, cantidad, categoria);
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

    @Override
    public TOperacion getTOperacion() {
        return tipoOperacion;
    }
}
