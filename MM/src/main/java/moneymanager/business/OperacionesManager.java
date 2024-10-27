package moneymanager.business;

import moneymanager.persistencia.AccesoDatos;
import moneymanager.persistencia.AccesoDatosOperaciones;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OperacionesManager {
    
    private final List<Operacion> operaciones = new ArrayList<>();
    private static  OperacionesManager instancia = null;
    private final CuentaManager CM;
    private final AccesoDatos AD;
    

    private OperacionesManager(){
        CM = CuentaManager.getInstancia();
        AD = AccesoDatosOperaciones.getInstance();
    }

    public static OperacionesManager getInstancia() {
        if(instancia == null) instancia = new OperacionesManager();
        return instancia;
    }

    public void leerCSV(){
        try {
            AD.leerCsv("CSV/operaciones.csv");}
        catch (IOException ignored){
        }
    }

    public void escribirCSV(){
        try {
            AD.escribirCsv("CSV/operaciones.csv");}
        catch (IOException ignored){

        }
    }

    public void cargarOperacionesCSV(String[] array){
        Operacion operacion = null;
        LocalDateTime fecha = stringToFecha(array[2]);
        Cuenta cuenta = CM.getCuenta(array[6]);
        switch (array[1]){
            case "G":
                operacion = new Gasto(array[0],fecha,array[5],Float.parseFloat(array[3]), cuenta, array[4]);
                cuenta.cargarOperacion(operacion);
                break;
            case "I":
                operacion = new Ingreso(array[0],fecha,array[5],Float.parseFloat(array[3]), cuenta, array[4]);
                cuenta.cargarOperacion(operacion);
                break;
            case "T":
                Cuenta cuentaDestino = CM.getCuenta(array[7]);
                operacion = new Transferencia(array[0],fecha,array[5],Float.parseFloat(array[3]), cuenta, cuentaDestino,array[4]);
                cuenta.cargarOperacion(operacion);
                break;
        }
        if (operacionNueva(operacion)){
            operaciones.add(operacion);
        }
    }

    public void registrarIngresoGasto(String motivo, float cantidad, String categoria){
        String id = generarIDAleatorio();
        LocalDateTime fecha = LocalDateTime.now();
        Cuenta cuenta = CM.getCuentaActual();
        Operacion operacion = null;
        if (cantidad>0){
            operacion = new Ingreso(id,fecha,motivo,cantidad,cuenta,categoria);
        }else{
            operacion = new Gasto(id,fecha,motivo,cantidad,cuenta,categoria);
        }
        if (operacionNueva(operacion)){
            cuenta.aniadirOperacion(operacion);
            operaciones.add(operacion);
        }
    }

    public void registrarTransferencia(String motivo, float cantidad, String categoria, Cuenta cuentaDestino){
        String id = generarIDAleatorio();
        LocalDateTime fecha = LocalDateTime.now();
        Cuenta cuenta = CM.getCuentaActual();
        Transferencia transferencia = new Transferencia(id,fecha,motivo,-1*cantidad,cuenta,cuentaDestino,categoria);
        Ingreso ingreso = new Ingreso(id,fecha,motivo,cantidad,cuentaDestino,categoria);
        if (operacionNueva(transferencia)){
            cuenta.aniadirOperacion(transferencia);
            cuentaDestino.aniadirOperacion(ingreso);
            operaciones.add(transferencia);
            operaciones.add(ingreso);
        }
    }

    public void modificarTransferencia(String id, String categoria, String asunto, Float cant){

    }
    public void modificarIngresoGasto(TOperacion tOPc,String id, String categoria, String asunto, Float cant){
        Operacion operacion = buscarOperacion(id, tOPc);
        if (!categoria.isEmpty()){
            operacion.setCategoria(categoria);
        }
        if (!asunto.isEmpty()){
            operacion.setMotivo(asunto);
        }
        if (cant != 0){
            CM.getCuentaActual().modificarSaldo(operacion.getCantidad(), cant);
            operacion.setCantidad(cant);
        }
    }

    public void eliminarOperacion(String id, TOperacion tOpc){
        Operacion opc = buscarOperacion(id,tOpc);
        if (opc != null){
            float saldoAct = CM.getCuentaActual().getSaldo();
            operaciones.remove(opc);
            CM.getCuentaActual().getHistorial().remove(opc);
            CM.getCuentaActual().setSaldo(saldoAct - opc.getCantidad());
            if (tOpc.equals(TOperacion.INGRESO)){
                opc = buscarOperacion(id, TOperacion.TRANSFERENCIA);
                if (opc != null){
                    Cuenta cuentaRemitente = ((Transferencia)opc).getCuentaRemitente();
                    cuentaRemitente.getHistorial().remove(opc);
                    cuentaRemitente.setSaldo(saldoAct - opc.getCantidad());
                    operaciones.remove(opc);
                }
            } else if (tOpc.equals(TOperacion.TRANSFERENCIA)){
                Cuenta cuentaDestino = ((Transferencia)opc).getCuentaDestino();
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + opc.getCantidad());
                opc = buscarOperacion(id, TOperacion.INGRESO);
                operaciones.remove(opc);
                cuentaDestino.getHistorial().remove(opc);
            }
        }
    }

    public void eliminarOperacion(Operacion operacion){
        operaciones.remove(operacion);
    }

    public Operacion buscarOperacion(String id, TOperacion tOpc){
        Operacion opc = null;
        for (Operacion aux : operaciones){
            if (aux.getId().equals(id) && aux.getTOperacion().equals(tOpc)){
                opc = aux;
            }
        }
        return opc;
    }

    private String generarIDAleatorio() {
        String id = "";
        do{
            String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuilder idB = new StringBuilder(5);

            for (int i = 0; i < 5; i++) {
                int index = random.nextInt(caracteres.length());
                idB.append(caracteres.charAt(index));
            }
            id = idB.toString();
        }while (!idNuevo(id));
        return id;
    }

    private boolean idNuevo(String id){
        boolean nuevo = true;
        for (Operacion operacion : operaciones){
            if (operacion.getId().equals(id)){
                nuevo = false;
            }
        }
        return nuevo;
    }

    private boolean operacionNueva(Operacion operacion){
        boolean nuevo = true;
        for (Operacion aux : this.operaciones){
            if (operacion.equals(aux)){
                nuevo = false;
            }
        }
        return nuevo;
    }

    private LocalDateTime stringToFecha(String localDate){
        int[] fecha = new int[3];

        for (int i = 0; i < 3; i++) fecha[i] = Integer.parseInt(localDate.split("/")[i]);
        return LocalDateTime.of(fecha[0], fecha[1], fecha[2], 0, 0, 0);
    }

    public List<Operacion> getOperaciones() {
        return operaciones;
    }



}
