package moneymanager.business;

import java.util.Date;

public class Gasto extends Operacion{

    private final TOperacion tipoOperacion = TOperacion.GASTO;

    public Gasto(int id, Date fecha, String motivo, float cantidad, String color) {
        super(id, fecha, motivo, cantidad, color);
    }
}
