package Main;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import structures.graph.Directorio;

public class Usuario {

    private String usuario;
    private String password;
    private String fechaCreacion;
    private Directorio directorio;

    public Usuario(String usuario, String password){
        this.usuario = usuario;
        this.password = encrypt_pass(password);
        this.fechaCreacion = getDate();
        this.directorio = new Directorio("/");
        
        
        //------------------- CODIGO DE PRUEBA PARA SIMULAR CARPETAS ----------------------- DEBE DE SER BORRADO ---------
        directorio.addDirectorio("temp");
        directorio.addDirectorio("var");
        directorio.addDirectorio("user");
        directorio.addDirectorio("home");
        directorio.addDirectorio("documents");
        directorio.addDirectorio("lib");
        directorio.addArchivo("archivo1.txt");
        directorio.addArchivo("archivo2.txt");
        directorio.addArchivo("archivo3.txt");
        directorio.addArchivo("HolaMundo.java");
        
        directorio.getDirectorio("temp").addDirectorio("cache");
        
        directorio.getDirectorio("var").addDirectorio("oracle");
        
        directorio.getDirectorio("user").addDirectorio("credentials");
        directorio.getDirectorio("user").addDirectorio("folder");
        
        directorio.getDirectorio("home").addDirectorio("documents");
        directorio.getDirectorio("home").addDirectorio("videos");
        
        directorio.getDirectorio("home").getDirectorio("documents").addDirectorio("usac");
        
        directorio.getDirectorio("documents").addDirectorio("universidad");
        //-----------------------------------------------------------------------------------------------------------------
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword(){
        return password;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = encrypt_pass(password);
    }

    public String getFecha() {
        return fechaCreacion;
    }

    private String encrypt_pass(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(password.getBytes("utf8"));
            password =  String.format("%064x", new BigInteger(1, md.digest()));
            return password;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean verifyPassword(String password){

        password = encrypt_pass(password);

        if(this.password.equalsIgnoreCase(password)){
            return true;
        }

        return false;
    }

    private String getDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh::mm::ss.SSS");
        return dateFormat.format(date);
    }
    
    public Directorio getDirectorio(){
        return directorio;
    }
    
}
