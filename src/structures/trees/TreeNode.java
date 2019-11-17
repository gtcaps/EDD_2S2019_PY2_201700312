package structures.trees;
import Main.Archivo;

public class TreeNode  {

    private Archivo archivo;
    private TreeNode izquierda, derecha;

    public TreeNode(Archivo a){
        archivo = a;
        izquierda = null;
        derecha = null;
    }

    public TreeNode(String nombreArchivo){
        archivo = new Archivo(nombreArchivo);
        izquierda = null;
        derecha = null;
    }

    public TreeNode(String nombreArchivo, String contenido){
        archivo = new Archivo(nombreArchivo , contenido);
        izquierda = null;
        derecha = null;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public TreeNode getDerecha() {
        return derecha;
    }

    public TreeNode getIzquierda() {
        return izquierda;
    }

    public void setDerecha(TreeNode derecha) {
        this.derecha = derecha;
    }

    public void setIzquierda(TreeNode izquierda) {
        this.izquierda = izquierda;
    }
}
