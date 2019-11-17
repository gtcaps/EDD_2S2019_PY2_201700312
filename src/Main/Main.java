package Main;

import structures.graph.Directorio;
import structures.*;

import java.io.*;
import java.nio.file.Paths;
import Views.Login;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class Main {

    public static HashTable users = new HashTable();
    public static Bitacora bitacora = new Bitacora();
    public static Usuario user = new Usuario("admin", "admin");
    public static Login log = new Login();

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        log.setVisible(true);

    }

    public static boolean initSession(String usuario, String password) {
        Usuario u = users.getUsuario(usuario);

        if (!u.getUsuario().isBlank()) {
            if (u.verifyPassword(password)) {
                System.out.println("Bienvenido " + u.getUsuario() + " has iniciado sesion exitosamente");
                user = u;
                return true;
            }
        }

        System.out.println("La sesion no pudo ser iniciada, verifique sus credenciales");
        return false;
    }

    public static void addUser(String usuario, String password) {
        bitacora.add(usuario, "Se registro un nuevo usuario");
        users.add(usuario, new Usuario(usuario, password));
    }

    public static void bulkLoadingUsers(String filename) throws IOException {
        String[] name = filename.split(Pattern.quote("\\"));

        bitacora.add("Administrador", "Carga Masiva - " + name[name.length - 1]);
        users.addCSV(filename);
    }

    public static void graphUsers() throws IOException, InterruptedException {
        users.graph();
    }
}
