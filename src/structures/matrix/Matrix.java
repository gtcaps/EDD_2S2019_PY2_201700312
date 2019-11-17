/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.matrix;

import Main.Graphic;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import structures.graph.Directorio;

/**
 *
 * @author aybso
 */
public class Matrix {

    private NodoMatrix cabezaX, colaX;
    private NodoMatrix cabezaY, colaY;
    private int sizeX, sizeY;

    public Matrix() {
        cabezaX = null;
        cabezaY = null;
        colaX = null;
        colaY = null;
        sizeX = 1;
        sizeY = 1;
    }

    public void add(String x, String y) {

        addX(x);
        addY(x);

        addX(y);
        addY(y);

        NodoMatrix nodoX = getX(y);
        NodoMatrix nodoY = getY(x);
        
        NodoMatrix nodoXY = new NodoMatrix(x + "/" + y, nodoX.getX(), nodoY.getY());
               

        //AGREGANDO EN Y
        if (nodoY.getSiguiente() == null) {
            nodoY.setSiguiente(nodoXY);
        } else {
            
            NodoMatrix ant = nodoY;
            NodoMatrix aux = nodoY.getSiguiente();
            if (nodoXY.getX() < aux.getX()) {
                nodoXY.setSiguiente(aux);
                nodoY.setSiguiente(nodoXY);
            } else {
                while (!isNull(aux)) {
                    if (nodoXY.getX() < aux.getX()) {
                        ant.setSiguiente(nodoXY);
                        nodoXY.setSiguiente(aux);
                        break;
                    }
                    ant = aux;
                    aux = aux.getSiguiente();
                }
                ant.setSiguiente(nodoXY);
            }
        }

        //AGREGANDO EN X
        if (nodoX.getAbajo() == null) {
            nodoX.setAbajo(nodoXY);
        } else {
            
            NodoMatrix ant = nodoX;
            NodoMatrix aux = nodoX.getAbajo();
            if (nodoXY.getY() > aux.getY()) {
                nodoXY.setAbajo(aux);
                nodoX.setAbajo(nodoXY);
            } else {
                while (!isNull(aux)) {
                    if (nodoXY.getY() > aux.getY()) {
                        ant.setSiguiente(nodoXY);
                        nodoXY.setSiguiente(aux);
                        break;
                    }
                    ant = aux;
                    aux = aux.getAbajo();
                }
                ant.setAbajo(nodoXY);
            }
        }


    }

    public NodoMatrix getX(String valor) {
        if (existeX(valor)) {
            NodoMatrix aux = cabezaX;
            while (!isNull(aux)) {
                if (aux.getValor() == valor) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }

    public NodoMatrix getY(String valor) {
        if (existeY(valor)) {
            NodoMatrix aux = cabezaY;
            while (!isNull(aux)) {
                if (aux.getValor() == valor) {
                    return aux;
                }
                aux = aux.getAbajo();
            }
        }
        return null;
    }

    public void add(Directorio directorio) {

        Directorio[] ds = directorio.getDirectorios().getIterable();

        for (Directorio folder : ds) {
            add(directorio.getNombre(), folder.getNombre());
            add(folder);
        }
    }

    public void graficar() {

        try {
            String dataGrafica = "";
            if (sizeX == 0 || sizeY == 0) {
                dataGrafica = "     init[label=\"Matriz Vacia\"];";
                Graphic g = new Graphic("matrix", dataGrafica, "LR", "neato", "1");
            } else {
                graficar("matrix");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public void imprimir(){
        
        if(cabezaY == null){
            System.out.println("La matriz esta vacia");
        }else{
            NodoMatrix x = cabezaX;
            while(x != null){
                System.out.print("<" + x.getValor() + ">");
                
                NodoMatrix y = x.getAbajo();
                while(y != null){
                    System.out.print("    <" + y.getValor() + ">");
                    y = y.getAbajo();
                }
                
                System.out.println("");
                
                x = x.getSiguiente();
            }
        }
        
    }

    private void graficar(String nombre) throws IOException, InterruptedException {
        double espacio = 2;
        String nodos = sizeX <= 1 ? "init[label=\"/\", shape=\"circle\", pos=\"0,0!\"];\n" :"init[label=\"\", shape=\"circle\", pos=\"0,0!\"];\n";
        String cabeceras = sizeX <= 1 ? " ": "init -> ";
        String relacionesX = "";
        System.out.println("---->" + sizeX);

        //System.out.println("----------------> POR FILAS");
        if (!isNull(cabezaY)) {
            NodoMatrix y = cabezaY;

            while (!isNull(y)) {
                //CABECERAS VERTICALES DE LA MATRIZ
                String nombreNodoY = y.getValor().equals("/") ? "root" : y.getValor();
                String nodoY = nombreNodoY + "_y[label=\"" + nombreNodoY + "\", pos=\"0," + (y.getY() * espacio) + "!\"];";
                nodos += nodoY + "\n";

                //RELACIONES ENTRE LOS NODOS CABECERA 
                if (y.getAbajo() != null) {
                    cabeceras += nombreNodoY + "_y  ->";
                } else {
                    cabeceras += nombreNodoY + "_y; \n";
                }
                
                if (y.getSiguiente() != null) {
                    //RELACIONES ENTRE LOS NODOS INTERNOS DE CADA CABECERA
                    relacionesX += nombreNodoY + "_y -> ";
                }

                NodoMatrix x = y.getSiguiente();

                while (!isNull(x)) {
                    //NODOS DENTRO DE CADA CABECERA VERTICAL DE LA MATRIZ
                    String nombreNodoX = x.getValor().equals("/") ? "root" : x.getValor().replace("/", "_");
                    String nodoXY = nombreNodoX + "_" + x.getX() + "_y[label=\"" + x.getValor() + "\", pos=\"" + (x.getX() * espacio) + "," + (x.getY() * espacio) + "!\"];";
                    nodos += nodoXY + "\n";

                    //RELACIONES ENTRE LOS NODOS INTERNOS DE CADA CABECERA      
                    if (x.getSiguiente() != null) {
                        relacionesX += nombreNodoX + "_" + x.getX() + "_y -> ";
                    } else {
                        relacionesX += nombreNodoX + "_" + x.getX() + "_y; \n";
                    }

                    x = x.getSiguiente();
                }

                y = y.getAbajo();
            }
        }

        cabeceras = cabeceras + cabeceras.replace("_y", "_x");

        //System.out.println("------------> POR COLUMNAS");
        if (!isNull(cabezaX)) {
            NodoMatrix x = cabezaX;

            while (!isNull(x)) {
                String nombreNodoX = x.getValor().equals("/") ? "root" : x.getValor();
                String nodoX = nombreNodoX + "_x[label=\"" + nombreNodoX + "\", pos=\"" + (x.getX() * espacio) + ",0!\"];";
                nodos += nodoX + "\n";

                //RELACIONES ENTRE LOS NODOS CABECERA 
                if (x.getAbajo() != null) {
                    //RELACIONES ENTRE LOS NODOS INTERNOS DE CADA CABECERA
                    relacionesX += nombreNodoX + "_x -> ";
                }

                NodoMatrix y = x.getAbajo();

                while (!isNull(y)) {
                    //NODOS DENTRO DE CADA CABECERA VERTICAL DE LA MATRIZ
                    String nombreNodoY = y.getValor().equals("/") ? "root" : y.getValor().replace("/", "_");

                    //RELACIONES ENTRE LOS NODOS INTERNOS DE CADA CABECERA      
                    if (y.getAbajo() != null) {
                        relacionesX += nombreNodoY + "_" + x.getX() + "_y -> ";
                    } else {
                        relacionesX += nombreNodoY + "_" + x.getX() + "_y; \n";
                    }

                    y = y.getAbajo();
                }

                x = x.getSiguiente();
            }
        }

        String data_graph = nodos + cabeceras + relacionesX;
        Graphic g = new Graphic(nombre, data_graph, "LR", "neato", "1");

    }

    private void addX(String valor) {
        NodoMatrix nodo = new NodoMatrix(valor, sizeX, 0);

        if (!existeX(valor)) {
            if (isNull(cabezaX)) {
                cabezaX = nodo;
                colaX = cabezaX;
                sizeX++;
            } else {
                colaX.setSiguiente(nodo);
                colaX = nodo;
                sizeX++;
            }
        }

    }

    private void addY(String valor) {
        NodoMatrix nodo = new NodoMatrix(valor, 0, -sizeY);

        if (!existeY(valor)) {
            if (isNull(cabezaY)) {
                cabezaY = nodo;
                colaY = cabezaY;
                sizeY++;
            } else {
                colaY.setAbajo(nodo);
                colaY = nodo;
                sizeY++;
            }
        }

    }

    private boolean existeX(String valor) {
        if (!isNull(cabezaX)) {
            NodoMatrix aux = cabezaX;
            while (!isNull(aux)) {
                if (aux.getValor() == valor) {
                    return true;
                }
                aux = aux.getSiguiente();
            }
        }
        return false;
    }

    private boolean existeY(String valor) {
        if (!isNull(cabezaY)) {
            NodoMatrix aux = cabezaY;
            while (!isNull(aux)) {
                if (aux.getValor() == valor) {
                    return true;
                }
                aux = aux.getAbajo();
            }
        }
        return false;
    }

    private boolean isNull(NodoMatrix nodo) {
        return nodo == null;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

}

class NodoMatrix {

    private String valor;
    private int x, y;
    private NodoMatrix siguiente;
    private NodoMatrix abajo;

    public NodoMatrix(String valor, int x, int y) {
        this.valor = valor;
        this.x = x;
        this.y = y;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public NodoMatrix getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMatrix siguiente) {
        this.siguiente = siguiente;
    }

    public NodoMatrix getAbajo() {
        return abajo;
    }

    public void setAbajo(NodoMatrix abajo) {
        this.abajo = abajo;
    }

}
