package moneymanager.persistencia;

import moneymanager.business.*;

import java.io.*;

public class AccesoDatosOperaciones implements AccesoDatos {
    private static AccesoDatosOperaciones instance = null;

    private AccesoDatosOperaciones(){}

    public static AccesoDatosOperaciones getInstance(){
        if(instance == null) instance = new AccesoDatosOperaciones();
        return instance;
    }


    public void escribirCsv(String ruta) throws IOException {
        OperacionesManager OM = OperacionesManager.getInstancia();
        PrintWriter escritor = null;
        try{
            escritor = new PrintWriter(new FileWriter(ruta, false));
            String tOperacion = "";
            for (Operacion operacion : OM.getOperaciones()) {
                switch (operacion.getTOperacion()){
                    case TOperacion.TRANSFERENCIA:
                        tOperacion = "T";
                        break;
                    case TOperacion.GASTO:
                        tOperacion = "G";
                        break;
                    case TOperacion.INGRESO:
                        tOperacion = "I";
                        break;
                }
                String fecha = operacion.getFecha().getYear()+"/"+ operacion.getFecha().getMonthValue()+"/"
                        +operacion.getFecha().getDayOfMonth();
                switch (tOperacion){
                    case "G":
                        escritor.println(operacion.getId() +";"+tOperacion+ ";"+fecha+";"+operacion.getCantidad()+";"
                                +operacion.getCategoria()+";"+operacion.getMotivo()+";"+ ((Gasto) operacion).getCuenta().getId());
                        break;
                    case "I":
                        escritor.println(operacion.getId() +";"+tOperacion+ ";"+fecha+";"+operacion.getCantidad()+";"
                                +operacion.getCategoria()+";"+operacion.getMotivo()+";"+ ((Ingreso) operacion).getCuenta().getId());
                        break;
                    case "T":
                        escritor.println(operacion.getId() +";"+tOperacion+ ";"+fecha+";"+operacion.getCantidad()+";"
                                +operacion.getCategoria()+";"+operacion.getMotivo()+";"+
                                ((Transferencia) operacion).getCuentaRemitente().getId()+ ";"+
                                ((Transferencia) operacion).getCuentaDestino().getId());
                }
            }
        }catch (IOException e){
            throw e;
        }finally {
            if (escritor != null) escritor.close();
        }
    }

    public void leerCsv(String ruta) throws IOException{
        OperacionesManager OM = OperacionesManager.getInstancia();
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new FileReader(ruta));
            String line;

            while ((line = lector.readLine()) != null) {
                String[] array = line.split(";");
                OM.cargarOperacionesCSV(array);

            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (lector != null) lector.close();
        }
    }
}
