package structures.list;

import structures.graph.Directorio;

public class Node {
    
    private Directorio directorio;
    private Node siguiente;
    private Node anterior;
    
    public Node(String nombreDirectorio){
        directorio = new Directorio(nombreDirectorio);
        siguiente = null;
        anterior = null;
    }

    public Directorio getDirectorio() {
        return directorio;
    }

    public void setDirectorio(Directorio directorio) {
        this.directorio = directorio;
    }

    public Node getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Node siguiente) {
        this.siguiente = siguiente;
    }

    public Node getAnterior() {
        return anterior;
    }

    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }

   
    
    
    
}
