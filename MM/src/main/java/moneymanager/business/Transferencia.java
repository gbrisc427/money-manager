package moneymanager.business;

import java.util.Date;

public class Transferencia extends  Operacion{

    private final TOperacion tipoOperacion = TOperacion.TRANSFERENCIA;
    private Cuenta cuentaRemitente;
    private Cuenta cunetaDestino;

    public Transferencia(Date fecha, String motivo, float cantidad, String color, Cuenta remitente, Cuenta destino, Categoria categoria) {
        super(fecha, motivo, cantidad, color, categoria);
        this.cuentaRemitente = remitente;
        this.cunetaDestino = destino;
    }

    public TOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public Cuenta getCunetaDestino() {
        return cunetaDestino;
    }

    public Cuenta getCuentaRemitente() {
        return cuentaRemitente;
    }
}
