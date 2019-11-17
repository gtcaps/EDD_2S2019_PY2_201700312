package trees;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Archivo {

    private String nombre;
    private String contenido;
    private String timestamp;

    public Archivo(String nombre){
        this.nombre = nombre;
        this.contenido = "";
        timestamp =  getFecha();
    }

    public Archivo(String nombre, String contenido){
        this.nombre = nombre;
        this.contenido = contenido;
        timestamp =  getFecha();

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        timestamp = getFecha();
    }


    public void setContenido(String contenido) {
        this.contenido = contenido;
        timestamp = getFecha();
    }

    public String getContenido() {
        return contenido;
    }


    public String getNombre() {
        return nombre;
    }


    public String getTimestamp() {
        return timestamp;
    }

    private String getFecha(){
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh::mm::ss.SSS");
        return  dateFormat.format(Calendar.getInstance().getTime());
    }

    public void setTimestamp(String time){
        timestamp = time;
    }


}