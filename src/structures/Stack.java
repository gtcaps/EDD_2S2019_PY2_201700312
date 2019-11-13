package structures;

import Main.Graphic;

import java.io.IOException;

public class Stack<T>{

    private Node<T> head;
    private int size;

    public Stack(){
        this.head = null;
        this.size = 0;
    }

    public void push(T value){
        if(isNull(head)){
            head = new Node<>(value);
            size++;
        }else{
            Node<T> node = new Node<>(value);
            node.setNext(head);
            head = node;
            size++;
        }
    }

    public Node<T> pop(){
        Node<T> node = head;
        head = head.getNext();
        size -= 1;
        return node;
    }

    public void previewStack(){
        if(isNull(head)){
            System.out.println("The stack is empty");
        }else{
            Node<T> aux = head;
            while(!isNull(aux)){
                System.out.println(aux.getData());
                aux = aux.getNext();
            }
        }
    }

    public void graph() throws IOException, InterruptedException {
        String data_graph = "";

        if (isNull(head)){
            data_graph = "node_empty[label=\"The stack is empty\"];";
        }else{
            Node<T> aux = head;
            int numberNode = 0;

            while(!isNull(aux)){
                data_graph += !isNull(aux.getNext()) ? aux.getData() + " | " : aux.getData();
                aux = aux.getNext();
            }

            data_graph = String.format("stack[label=\"%s\"];\n",data_graph);
        }

        Graphic g = new Graphic("stack", data_graph, "LR");
    }

    public boolean hasNext(){
        return true ? size > 0 : false;
    }

    public int getSize(){
        return size;
    }

    private boolean isNull(Node<T> node){
        return node == null;
    }


}
