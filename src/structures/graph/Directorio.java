package structures.graph;

import structures.list.ListaDirectorios;
import Main.Graphic;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

public class Directorio {

    private String nombre;
    private ListaDirectorios directorios;

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

        System.out.println("--".repeat(level) + d.getNombre() + " con " + d.getDirectorios().getSize() + " directorios dentro ");

        Directorio[] ds = d.getDirectorios().getIterable();

        for (Directorio folder : ds) {
            imprimirDirectorioCompleto(folder, level + 1);
        }

    }

    public void graficar() {
        try {
            String s = "";
            s = graficar(this, 0);
            System.out.println("--asd-----------------------");
            System.out.println(s);
            Graphic g = new Graphic("graph", s, "LR");
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
    }

    
    private void getTreeRoot(Directorio folder, DefaultMutableTreeNode node){
        
        System.out.println(folder.getNombre());
        Directorio [] di = folder.getDirectorios().getIterable();
        for(Directorio d: di){
            System.out.println("        " + d.getNombre());
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(d.getNombre());
            node.add(newNode);
            getTreeRoot(d, newNode);
        }
        
    }
    
    public DefaultMutableTreeNode getTreeRoot() {
        
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(nombre);
        getTreeRoot(this, root);
        
        /*
        //------
        System.out.println("--".repeat(level) + d.getNombre() + " con " + d.getDirectorios().getSize() + " directorios dentro ");

        Directorio[] ds = d.getDirectorios().getIterable();

        for (Directorio folder : ds) {
            imprimirDirectorioCompleto(folder, level + 1);
        }

        //---------
        ArrayList<String> l = new ArrayList<>();
        ArrayList<String> l1 = new ArrayList<>();

        l.add("folder");
        l1.add("f1");
        l1.add("f2");
        l1.add("f3");
        l.add("folder2");
        l.add("folder3");

        Iterator i = l.iterator();
        while (i.hasNext()) {
            Iterator j = l1.iterator();
            String ss = i.next().toString();

            DefaultMutableTreeNode node = new DefaultMutableTreeNode(ss);

            while (j.hasNext()) {
                node.add(new DefaultMutableTreeNode(j.next().toString()));
            }

            root.add(node);
        }
        */
        return root;
    }

}
