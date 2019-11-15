package structures.list;

import structures.graph.Directorio;

public class ListaDirectorios{

    private Node cabeza;
    private Node cola;
    private int size;
    
    public ListaDirectorios(){
        cabeza = null;
        cola = null;
        size = 0;
    }
    
    public void add(String nombreDirectorio){
        if(isNull(cabeza)){
            cabeza = new Node(nombreDirectorio);
            cola = cabeza;
            size++;
        }else{
            Node newDirectorio = new Node(nombreDirectorio);
            newDirectorio.setAnterior(cola);
            cola.setSiguiente(newDirectorio);
            cola = newDirectorio;
            size++;
        }
    }
    
    public int getSize(){
        return size;
    }
    
    public void imprimir(){
        if(isNull(cabeza)){
            System.out.println("Lista Vacia");
        }else{
            Node aux = cabeza;
            while(!isNull(aux)){
                System.out.println("-->" + aux.getDirectorio().getNombre());
                aux = aux.getSiguiente();
            }
        }
    }
    
    public Directorio get(String nombreDirectorio){
        
        if(!isNull(cabeza)){
            Node aux = cabeza;
            while(!isNull(aux)){
                if(aux.getDirectorio().getNombre().equals(nombreDirectorio)){
                    return aux.getDirectorio();
                }
                aux = aux.getSiguiente();
            }
        }
        
        return new Directorio("");
    }
    
    public Directorio getPrimero(){
        return cabeza.getDirectorio();
    }
    
    public Directorio getUltimo(){
        return cola.getDirectorio();
    }
    
    public Directorio [] getIterable(){
        
        Directorio [] it = new Directorio[size];
        
        if(size > 0){
            Node aux = cabeza;
            int index = 0;
            while(!isNull(aux)){
                it[index] = aux.getDirectorio();
                index++;
                aux = aux.getSiguiente();
            }
        }
        
        return  it;
    }
    
    private void eliminarInicio(){
        if(!isNull(cabeza)){
            cabeza = cabeza.getSiguiente();
            size--;
        }
    }
    
    private void eliminarFinal(){
        if(!isNull(cola)){
            cola = cola.getAnterior();
            cola.setSiguiente(null);
            size--;
        }
    }
    
    public void eliminar(String nombreDirectorio){
        if(!isNull(cabeza)){
            
            if(cabeza.getDirectorio().getNombre().equals(nombreDirectorio)){
                eliminarInicio();
            }else if(cola.getDirectorio().getNombre().equals(nombreDirectorio)){
                eliminarFinal();
            }else{
                Node aux = cabeza;
                
                while(!isNull(aux)){
                    
                    if(aux.getDirectorio().getNombre().equals(nombreDirectorio)){                      
                        aux.getAnterior().setSiguiente(aux.getSiguiente());
                        aux.getSiguiente().setAnterior(aux.getAnterior());
                        aux = null;
                        size--;
                        break;
                    }
                    aux = aux.getSiguiente();
                }
            }
            
        }
    }
    
    
    private boolean isNull(Node nodo){
        return nodo == null;
    }
    
    public boolean existe(String nombreDirectorio){
        if(!isNull(cabeza)){
            Node aux = cabeza;
            while(!isNull(aux)){
                if(aux.getDirectorio().getNombre().equals(nombreDirectorio)){
                    return true;
                }
                aux = aux.getSiguiente();
            }
        }
        return false;
    }

    
}
