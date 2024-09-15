package moneymanager.business;

import java.util.Date;

public class Ingreso extends  Operacion{

    private final TOperacion tipoOperacion = TOperacion.INGRESO;

    public Ingreso(int id, Date fecha, String motivo, float cantidad, String color) {
        super(id, fecha, motivo, cantidad, color);
    }
}
