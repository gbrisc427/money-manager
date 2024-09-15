package moneymanager.business;

import java.util.Date;

public class Transferencia extends  Operacion{

    private final TOperacion tipoOperacion = TOperacion.TRANSFERENCIA;

    public Transferencia(int id, Date fecha, String motivo, float cantidad, String color) {
        super(id, fecha, motivo, cantidad, color);
    }
}
