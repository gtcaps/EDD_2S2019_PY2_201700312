package structures.trees;

import Main.Archivo;
import Main.Graphic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AVL {

    private TreeNode raiz;
    private int size;

    public AVL(){
        raiz = null;
        size = 0;
    }

    public void add(String nombre){
        if(isNull(raiz)){
            raiz = new TreeNode(nombre);
            size++;
        }else{
            raiz = add(nombre, "", raiz);
            size++;
        }
    }

    public void addCSV(String ruta)  {
        try{

            File file = new File(ruta);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = "";
            String [] headers = br.readLine().toLowerCase().strip().split(",");

            while((line = br.readLine()) != null){
                String [] linea = line.strip().split(",");

                if(headers[0].contains("archivo")){
                    String nombre = linea[0].contains(".") ? linea[0] : linea[0] + ".txt";
                    add(nombre, linea[1]);
                }else{
                    String nombre = linea[1].contains(".") ? linea[1] : linea + ".txt";
                    add(nombre, linea[0]);
                }

            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void add(String nombre, String contenido){
        if(isNull(raiz)){
            raiz = new TreeNode(nombre);
        }else{
            raiz = add(nombre, contenido, raiz);
        }
    }

    private TreeNode add(String nombre, String contenido,  TreeNode node){

        if(node == null){
            return (new TreeNode(nombre, contenido));
        }


        if(nombre.compareTo(node.getArchivo().getNombre()) > 0){
            node.setDerecha(add(nombre,  contenido, node.getDerecha()));
        }else if(nombre.compareTo(node.getArchivo().getNombre()) < 0){
            node.setIzquierda(add(nombre, contenido, node.getIzquierda()));
        }

        int equilibrio = factorEquilibrio(node);


        //ROTACION SIMPLE A LA DERECHA SI EL FACTOR DE EQUILIBRIO ES -2 Y EL FACTOR DE EQUILIBRIO DE SU HIJO IZQUIERDO ES NEGATIVO
        //         LEFT - LEFT (IZQUIERDA - IZQUIERDA )
        //                  10  (-2)
        //                 /                                        8
        //                8     (-1)       ------>                /   \
        //               /                                       5     10
        //              5       (0)
        //
        if(equilibrio < -1 && factorEquilibrio(node.getIzquierda()) <= 0){
            return rightRotation(node);
        }

        //ROTACION SIMPLE A LA IZQUIERDA SI EL FACTOR DE EQUILIBRIO ES 2 Y EL FACTOR DE EQUILIBRIO DE SU HIJO DERECHO ES POSITIVO
        //         RIGHT - RIGHT (DERECHA - DERECHA )
        //                  10  (2)
        //                     \                                       18
        //                      18     (1)       ------>             /    \
        //                        \                                 10     25
        //                         25       (0)
        //
        if(equilibrio > 1 && factorEquilibrio(node.getDerecha()) >= 0){
            return leftRotation(node);
        }

        //ROTACION DOBLE (IZQUIERDA DERECHA) SI EL FACTOR DE EQUILIBRIO ES -2 Y EL FACTOR DE EQUILIBRIO DE SU HIJO IZQUIERDO ES POSITIVO
        //         LEFT - RIGHT (IZQUIERDA - DERECHA )
        //                  10      (-2)                                  10
        //                 /                                             /                       8
        //                5         (1)         ------>                8      ----->           /   \
        //                  \                                         /                       5     10
        //                    8      (0)                             5
        //
        if(equilibrio < -1 && factorEquilibrio(node.getIzquierda()) >= 0){
            node.setIzquierda(leftRotation(node.getIzquierda()));
            return rightRotation(node);
        }


        //ROTACION DOBLE (DERECHA IZQUIERDA) SI EL FACTOR DE EQUILIBRIO ES 2 Y EL FACTOR DE EQUILIBRIO DE SU HIJO DERECHO ES NEGATIVO
        //         RIGHT - LEFT (DERECHA - IZQUIERDA )
        //                  10           (2)                               10
        //                    \                                              \                              15
        //                     20        (-1)         ------>                  15        ----->           /    \
        //                    /                                                  \                      10      20
        //                  15            (0)                                     20
        //
        if(equilibrio > 1 && factorEquilibrio(node.getIzquierda()) <= 0){
            node.setDerecha(rightRotation(node.getDerecha()));
            return leftRotation(node);
        }

        return node;

    }

    public Archivo getArchivo(String nombre){

        TreeNode aux = raiz;
        while(aux != null) {
            if (nombre.compareTo(aux.getArchivo().getNombre()) > 0) {
                aux = aux.getDerecha();
            } else if (nombre.compareTo(aux.getArchivo().getNombre()) < 0) {
                aux = aux.getIzquierda();
            } else {
                return aux.getArchivo();
            }
        }
        return new Archivo(" ", " ");
    }



    private TreeNode rightRotation(TreeNode node){
        TreeNode newRoot = node.getIzquierda();
        TreeNode aux = newRoot.getDerecha();

        newRoot.setDerecha(node);
        node.setIzquierda(aux);

        return newRoot;

    }

    private TreeNode leftRotation(TreeNode node){
        TreeNode newRoot = node.getDerecha();
        TreeNode aux = newRoot.getIzquierda();

        newRoot.setIzquierda(node);
        node.setDerecha(aux);

        return newRoot;

    }


    private int altura(TreeNode node){
        if(isNull(node)){
            return  0;
        }

        if(isNull(node.getDerecha()) && isNull(node.getIzquierda())){
            return 1;
        }

        int alturaDerecha = altura(node.getDerecha());
        int alturaIzquierda = altura(node.getIzquierda());
        int alturaNodo = alturaDerecha > alturaIzquierda ? alturaDerecha + 1: alturaIzquierda + 1;

        return  alturaNodo;
    }

    private int factorEquilibrio(TreeNode node){
        return altura(node.getDerecha()) - altura(node.getIzquierda());
    }

    public void graficar(String directorio) throws IOException, InterruptedException{
        if(!isNull(raiz)){
            String dataGraphic = enOrden(raiz);
            dataGraphic += "\n" + "labelloc=\"t\";\n";
            dataGraphic += "label=\""+ "Arbol Balanceado de Archivos - [USUARIO] " + Main.Main.user.getUsuario()  +" - [DIRECTORIO]   " + directorio  +"\";\n";
            Graphic g = new Graphic("avl_" + Main.Main.user.getUsuario() , dataGraphic, "TB", "dot", "1");
        }else{
            Graphic g = new Graphic("avl_" + Main.Main.user.getUsuario() , "    node_info[label=\"El directorio " + directorio + " , no tiene archivos\"]", "TB", "dot", "1");
        }
    }

    private String enOrden(TreeNode node){
        String s = "";
        if(node != null){
            s += enOrden(node.getIzquierda() );
            s += ("    nodo_" + node.getArchivo().getNombre().replace(".","_") + "[label=\"<i>|" +
                    "[NOMBRE] "+ node.getArchivo().getNombre()+" \\n " +
                    "[TIMESTAMP] " + node.getArchivo().getTimestamp() + " \\n " +
                    "[F.E.] " + factorEquilibrio(node)+ " \\n " +
                    "[ALTURA] " + altura(node) + " \\n " +
                    "[CONTENIDO] \\n" + (node.getArchivo().getContenido().isBlank() ? "vacio": node.getArchivo().getContenido().replace("\"","\\\"")).replace("{", "\\{").replace("}", "\\}").replace("\n", "\\n") + " \\n " +
                    "|<d>\"];\n");

            if(node.getIzquierda() != null){
                s += ("    nodo_" + node.getArchivo().getNombre().replace(".","_") + "" +
                        ":i -> nodo_" + node.getIzquierda().getArchivo().getNombre().replace(".","_") + ";\n");
            }

            if(node.getDerecha() != null){
                s += ("    nodo_" + node.getArchivo().getNombre().replace(".","_") + "" +
                        ":d -> nodo_" + node.getDerecha().getArchivo().getNombre().replace(".","_") + ";\n");
            }

            s += enOrden(node.getDerecha());
        }
        return s;
    }

    public void delete(String nombre){
        raiz = delete(nombre, raiz, new AVL());
    }

    private TreeNode delete(String nombre, TreeNode node, AVL t){
        if(node != null){



            if(nombre.equals(node.getArchivo().getNombre())){
                size--;
            }else{
                Archivo a = node.getArchivo();
                t.add(a.getNombre(), a.getContenido());
                t.getArchivo(a.getNombre()).setTimestamp(a.getTimestamp());
            }


            delete(nombre, node.getDerecha(), t);
            delete(nombre, node.getIzquierda(), t);

        }

        return t.getRaiz();
    }

    public TreeNode getRaiz(){
        return raiz;
    }

    private TreeNode nodoMin(TreeNode node){
        TreeNode actual = node;

        while(actual.getIzquierda() != null){
            actual = actual.getIzquierda();
        }
        return  actual;
    }

    public boolean isNull(TreeNode node){
        return node == null;
    }


    public int getSize() {
        return size;
    }
    
    
    public String nombresArchivos(){
        return listarArchivosEnOrden(raiz);
    }
    
       private String listarArchivosEnOrden(TreeNode node){
        String s = "";
        if(node != null){
            s += listarArchivosEnOrden(node.getIzquierda() );
            s += node.getArchivo().getNombre() + " ";
            s += listarArchivosEnOrden(node.getDerecha());
        }
        return s;
    }
}
