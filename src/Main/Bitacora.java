package Main;

import structures.Stack;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Bitacora {

    private Stack<String> bitacora;

    public Bitacora(){
        bitacora = new Stack<>();
    }

    public void add(String usuario, String operacion){
        var date = getDate();
        String fecha, hora;

        usuario = "[USUARIO]:  " + usuario + "\\n";
        operacion = "[OPERACION]:  " + operacion + "\\n";
        fecha = "[FECHA]:  " + date[0] + "\\n";
        hora = "[HORA]:  " + date[1] + "\\n";

        String reporte = usuario + operacion + fecha + hora;
        bitacora.push(reporte);

    }

    public void graph(){
        try {
            bitacora.graph();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String[] getDate(){

        Date date = Calendar.getInstance().getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh::mm::ss.SSS");
        String strDate = dateFormat.format(date);

        return strDate.split(" ");

    }




}
