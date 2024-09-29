package moneymanager.vista.paneles;

import moneymanager.business.Cuenta;
import moneymanager.vista.VistaVentana;

public interface Panel {
    VistaVentana ventana = VistaVentana.getInstancia();
    public void ocultarPanel();
    public void mostrarPanel();
}
