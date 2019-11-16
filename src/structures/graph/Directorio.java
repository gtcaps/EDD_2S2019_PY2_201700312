package structures.graph;

import structures.list.ListaDirectorios;
import Main.Graphic;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import structures.Stack;
import structures.matrix.Matrix;

public class Directorio {

    private String nombre;
    private ListaDirectorios directorios;
    private Matrix m = new Matrix(); 

    public Directorio(String nombre) {
        this.nombre = nombre;
        directorios = new ListaDirectorios();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addDirectorio(String nombreDirectorio) {
        directorios.add(nombreDirectorio);
    }

    public Directorio getDirectorio(String nombreDirectorio) {
        return directorios.get(nombreDirectorio);
    }

    public void removeDirectorio(String nombreDirectorio) {
        directorios.eliminar(nombreDirectorio);
    }

    public void imprimirDirectorio() {
        System.out.println(nombre);

        Directorio[] ds = directorios.getIterable();
        for (Directorio d : ds) {
            System.out.println("----->" + d.getNombre());
        }
    }

    public ListaDirectorios getDirectorios() {
        return directorios;
    }
    
    private void imprimirDirectorioCompleto(Directorio d, int level) {

        //System.out.println("--".repeat(level) + d.getNombre() + " con " + d.getDirectorios().getSize() + " directorios dentro ");

        Directorio[] ds = d.getDirectorios().getIterable();

        for (Directorio folder : ds) {
            System.out.println(d.getNombre() + "/" + folder.getNombre());
            m.add(d.getNombre(), folder.getNombre());
            imprimirDirectorioCompleto(folder, level + 1);
        }

    }

    public void graficar() {
        try {
            String s = "";
            s = graficar(this, 0);
            s += "\nlabelloc=\"t\";\n";
            s += "label=\"" + "Directorios - USUARIO: " + Main.Main.user.getUsuario() + "\";\n";

            Graphic g = new Graphic("graph_" + Main.Main.user.getUsuario(), s, "LR", "neato", true);
        } catch (IOException ex) {
            Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String graficar(Directorio d, int level) {
        String nodeName = (d.getNombre().equals("/") ? "root" : d.getNombre().replace(" ", "")) + "_" + level;
        String label = d.getNombre() + " \\n  " + d.getDirectorios().getSize();
        String node = String.format("%s[label=\"%s\"];\n", nodeName, label);

        String s = node;

        Directorio[] ds = d.getDirectorios().getIterable();

        for (Directorio folder : ds) {
            s += ("    " + nodeName + "->" + folder.getNombre().replace(" ", "") + "_" + (level + 1) + ";\n");
        }

        for (Directorio folder : ds) {
            s += graficar(folder, level + 1);
        }

        return s;

    }

    public void imprimirDirectorioCompleto() {
        imprimirDirectorioCompleto(this, 0);
        m.imprimir();
        //m.graficar();
    }

    private void getTreeRoot(Directorio folder, DefaultMutableTreeNode node) {

        Directorio[] di = folder.getDirectorios().getIterable();
        for (Directorio d : di) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(d.getNombre());
            node.add(newNode);
            getTreeRoot(d, newNode);
        }

    }

    public DefaultMutableTreeNode getTreeRoot() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(nombre);
        getTreeRoot(this, root);
        return root;
    }

    public void graficarMatrizAdyacencia() {
        Matrix m = new Matrix();
        m.add(this);
        m.graficar();
    }

 

}
