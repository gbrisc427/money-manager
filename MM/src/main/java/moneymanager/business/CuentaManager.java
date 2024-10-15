package moneymanager.business;


import moneymanager.persistencia.AccesoDatos;
import moneymanager.persistencia.AccesoDatosCuentas;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CuentaManager {


    private final List<Cuenta> cuentas = new ArrayList<>();
    private static  CuentaManager instancia = null;
    private Cuenta cuentaActual;
    private final AccesoDatos AD;


    private CuentaManager(){
        AD = AccesoDatosCuentas.getInstance();
    }


    public static CuentaManager getInstancia() {
        if(instancia == null) instancia = new CuentaManager();
        return instancia;
    }


    public boolean inicializar(){
        if (!cuentas.isEmpty()){
            this.cuentaActual = cuentas.getFirst();
            return false;
        }else{
            crearCuenta("CUENTA");
            return true;
        }


    }


    public void leerCSV(){
        try {
            AD.leerCsv("CSV/cuentas.csv");}
        catch (IOException e){
            // CREAR MÉTODO QUE LANCE UNA POP UP CON ERROR AL GUARDAR LOS DATOS
        }
    }


    public void escribirCSV(){
        try {
            AD.escribirCsv("CSV/cuentas.csv");}
        catch (IOException e){
            // CREAR MÉTODO QUE LANCE UNA POP UP CON ERROR AL GUARDAR LOS DATOS
        }
    }


    public void crearCuenta(String nombre){
        Cuenta cuenta = new Cuenta(generarIDAleatorio(),nombre, 0, new ArrayList<>());
        cuentas.add(cuenta);
        this.cuentaActual = cuentas.getLast();
    }


    public void crearCuenta(String id, String nombre, float saldo){
        Cuenta cuenta = new Cuenta(id,nombre, saldo, new ArrayList<>());
        cuentas.add(cuenta);
    }


    public void modificarCuenta(String nombre){
        if (cuentaActual != null){
            cuentaActual.setNombre(nombre);
        }
    }


    public void eliminarCuenta(){
        if (cuentaActual != null){
            cuentas.remove(cuentaActual);
            for (Operacion opc : cuentaActual.getHistorial()){
                OperacionesManager.getInstancia().eliminarOperacion(opc);
            }
            if (cuentas.isEmpty()){
                cuentaActual = null;
            }else{
                cuentaActual = cuentas.getFirst();
            }
        }
    }


    public void cambiarCuenta(Cuenta cuenta){
        if (cuentas.contains(cuenta)){
            cuentaActual = cuenta;
        }
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
        for (Cuenta cuenta : cuentas){
            if (cuenta.getId().equals(id)){
                nuevo = false;
            }
        }
        return nuevo;
    }


    public String getSaldo(){
        String saldo = "0.00€";
        if (cuentaActual != null){
            saldo = cuentaActual.getSaldo() + "€";
        }
        return saldo;
    }


    public String getNombreCuenta(){
        String nombre = "";
        if (cuentaActual != null){
            nombre = cuentaActual.getNombre();
        }
        return nombre;
    }


    public String getId(){
        String saldo = "#";
        if (cuentaActual != null){
            saldo += cuentaActual.getId();
        }
        return saldo;
    }


    public Cuenta getCuenta(String id){
        Cuenta cuenta = null;
        for (Cuenta aux : cuentas){
            if (aux.getId().equals(id)){
                cuenta = aux;
            }
        }
        return cuenta;
    }




    public Cuenta getCuentaActual() {
        return cuentaActual;
    }


    public List<Cuenta> getCuentas() {
        return cuentas;
    }


    public String[] getCuentasOpTransf() {
        String[] opc = new String[cuentas.size()-1];
        int cont = 0;
        for (Cuenta cuenta : cuentas){
            if (!cuenta.equals(cuentaActual)){
                opc[cont] = cuenta.getId();
                cont++;
            }
        }
        return opc;
    }


    public List<Operacion> getOperacionesCAFechaDeterminada (int mes, int anio){
        List<Operacion> operaciones = new ArrayList<>();
        for (Operacion op : cuentaActual.getHistorial()){
            if (op.getFecha().getMonthValue() == mes && anio == op.getFecha().getYear()){
                operaciones.add(op);
            }
        }
        return operaciones;
    }




}



