package Main;

import structures.graph.Directorio;
import structures.*;

import java.io.*;
import java.nio.file.Paths;
import Views.Login;
import java.net.URISyntaxException;
import structures.graph.Directorio;

public class Main {

    //public static HashTable users = new HashTable();
    //public static Bitacora bitacora = new Bitacora();
    //public static Usuario user;
    //public static Login log = new Login();

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        Directorio n = new Directorio("/");
        n.addDirectorio("temp");
        n.addDirectorio("var");
        n.addDirectorio("user");
        n.addDirectorio("home");
        
        n.getDirectorio("temp").addDirectorio("cache");
        
        n.getDirectorio("var").addDirectorio("oracle");
        
        n.getDirectorio("user").addDirectorio("credentials");
        n.getDirectorio("user").addDirectorio("folder");
        
        n.getDirectorio("home").addDirectorio("documents");
        n.getDirectorio("home").addDirectorio("videos");
        
        n.getDirectorio("home").getDirectorio("documents").addDirectorio("usac");
        
        n.graficar();
        
        /*-------------------------NECESSARY
        String project_path = "C:\\Users\\aybso\\OneDrive\\Documents\\Universidad\\EDD\\Laboratorio\\DS_DRIVE\\";
        
        bulkLoadingUsers( project_path + "users.csv");
        addUser("huawei123","123123123");
        //bulkLoadingUsers("users2.csv");
        //graphUsers();

        log.setVisible(true);
        /*
        
        
        
        //-----------------------------NO NECESSARY
        /*
        String [] invalid_users = t.getInvalidUsers();
        for(String u: invalid_users){
            System.out.println(u);
        }
        */


        /*
        Bitacora bitacora = new Bitacora();
        bitacora.add("Pedro", "Elimino Carpeta Movies");
        Thread.sleep(1000);
        bitacora.add("Juan", "Elimino Carpeta Documents");
        Thread.sleep(5000);
        bitacora.add("Domingo", "Elimino Carpeta Pictures");
        bitacora.graph();
        */

    }

    
//    public static boolean initSession(String usuario, String password){
//        Usuario u = users.getUsuario(usuario);
//
//        if(!u.getUsuario().isBlank()){
//            if(u.verifyPassword(password)) {
//                System.out.println("Bienvenido " + u.getUsuario() + " has iniciado sesion exitosamente");
//                user = u;
//                return true;
//            }
//        }
//
//        System.out.println("La sesion no pudo ser iniciada, verifique sus credenciales");
//        return false;
//    }
//
//    public static void addUser(String usuario, String password){
//        users.add(usuario, new Usuario(usuario, password));
//    }
//
//    public static void bulkLoadingUsers(String filename) throws IOException {
//        users.addCSV(filename);
//    }
//
//    public static void graphUsers() throws IOException, InterruptedException {
//        users.graph();
//    }


}
