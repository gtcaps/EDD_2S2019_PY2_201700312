package structures;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Main.Graphic;
import Main.Usuario;

public class HashTable {

    private Usuario [] table;
    private int size;
    private int capacity;
    private Stack<Integer> numerosPrimos;
    private Stack<Usuario> usuariosRepetidos;
    private Stack<Usuario> usuariosPassInvalidos;

    public HashTable(){

        String primos = "7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109 113" +
                "127 131 137 139 149 151 157 163 167 173 179 181 191 193 197 199 211 223 227 229 233 239 241 251 257 263 269 271 277 281" +
                "283 293 307 311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401 409 419 421 431 433 439 443 449 457 461 463" +
                "467 479 487 491 499 503 509 521 523 541 547 557 563 569 571 577 587 593 599 601 607 613 617 619 631 641 643 647 653 659" +
                "661 673 677 683 691 701 709 719 727 733 739 743 751 757 761 769 773 787 797 809 811 821 823 827 829 839 853 857 859 863" +
                "877 881 883 887 907 911 919 929 937 941 947 953 967 971 977 983 991 997 1009 1013 1019 1021 1031 1033 1039 1049 1051 1061 1063 1069" +
                "1087 1091 1093 1097 1103 1109 1117 1123 1129 1151 1153 1163 1171 1181 1187 1193 1201 1213 1217 1223 1229 1231 1237 1249 1259 1277 1279 1283 1289 1291" +
                "1297 1301 1303 1307 1319 1321 1327 1361 1367 1373 1381 1399 1409 1423 1427 1429 1433 1439 1447 1451 1453 1459 1471 1481 1483 1487 1489 1493 1499 1511" +
                "1523 1531 1543 1549 1553 1559 1567 1571 1579 1583 1597 1601 1607 1609 1613 1619 1621 1627 1637 1657 1663 1667 1669 1693 1697 1699 1709 1721 1723 1733" +
                "1741 1747 1753 1759 1777 1783 1787 1789 1801 1811 1823 1831 1847 1861 1867 1871 1873 1877 1879 1889 1901 1907 1913 1931 1933 1949 1951 1973 1979 1987" +
                "1993 1997 1999 2003 2011 2017 2027 2029 2039 2053 2063 2069 2081 2083 2087 2089 2099 2111 2113 2129 2131 2137 2141 2143 2153 2161 2179 2203 2207 2213" +
                "2221 2237 2239 2243 2251 2267 2269 2273 2281 2287 2293 2297 2309 2311 2333 2339 2341 2347 2351 2357 2371 2377 2381 2383 2389 2393 2399 2411 2417 2423" +
                "2437 2441 2447 2459 2467 2473 2477 2503 2521 2531 2539 2543 2549 2551 2557 2579 2591 2593 2609 2617 2621 2633 2647 2657 2659 2663 2671 2677 2683 2687" +
                "2689 2693 2699 2707 2711 2713 2719 2729 2731 2741 2749 2753 2767 2777 2789 2791 2797 2801 2803 2819 2833 2837 2843 2851 2857 2861 2879 2887 2897 2903" +
                "2909 2917 2927 2939 2953 2957 2963 2969 2971 2999 3001";
        String [] primos_array = primos.split(" ");
        numerosPrimos = new Stack<>();

        for(int i = (primos_array.length - 1); i >= 0; i-- ){
            int numero = Integer.parseInt(primos_array[i]);
            numerosPrimos.push(numero);
        }


        size = 0;
        capacity = numerosPrimos.pop().getData();
        table = new Usuario[capacity];

        usuariosRepetidos = new Stack<>();
        usuariosPassInvalidos = new Stack<>();

    }

    public void add(String key, Usuario value){

        incrementCapacity();

        String key_sha = sha_256(key);

        int hash = getHash(key_sha) % capacity;
        hash = hash < 0 ? (hash * -1) : hash;
        hash = verify_hash_pos_empty(hash, 0);


        if(!exists(value.getUsuario())){
            table[hash] = value;
            size++;

        }else{
            usuariosRepetidos.push(value);
        }


    }

    private void incrementCapacity(){
        if(usePercentage() > 75) {

            //System.out.println("Actual Capacity: " + capacity + " with use of " + usePercentage() + "% equivalent to " + size +" values");

            capacity = numerosPrimos.pop().getData();

            Usuario [] aux_tabla = new Usuario[size];
            int index_aux = 0;

            for(Usuario u: table){
                if(u != null){
                    aux_tabla[index_aux] = u;
                    index_aux++;
                }
            }

            table = new Usuario[capacity];
            size = 0;

            for(Usuario u: aux_tabla){
                add(u.getUsuario(), u);
            }

            //System.out.println("Actual Capacity: " + capacity + " with use of " + usePercentage() + "% equivalent to " + size +" values");
        }
    }

    private int usePercentage(){

        int percentage = (100 * size) / capacity;
        return percentage;

    }

    private int verify_hash_pos_empty(int hash, int i){
        hash = (int) (hash + Math.pow(i,2));

        do {
            hash = hash >= capacity ? (int) hash/capacity : (int) hash;
        }while(hash >= capacity);

        if(table[hash] == null){
            return hash;
        }else{
            hash = verify_hash_pos_empty(hash, i + 1);
        }

        return hash;
    }

    private String sha_256(String key){
        MessageDigest md = null;
        String key_sha256 = (String) key;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(key_sha256.getBytes("utf8"));
            key_sha256 =  String.format("%064x", new BigInteger(1, md.digest()));
            return key_sha256;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private int getHash(String key){

        char [] key_array = key.toCharArray();
        int hash = 0;

        for(char c : key_array){
            hash = (int) c;
        }

        return hash;
    }

    public boolean exists(String usuario){
        if(size > 0){
            for (Usuario user: table) {
                if(user != null){
                    if(user.getUsuario().equals(usuario)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Usuario getUsuario(String u){

        if(size > 0){
            for(Usuario usuario: table){
                if(usuario != null){
                    if(usuario.getUsuario().equals(u.strip())){
                        return usuario;
                    }
                }
            }
        }

        return new Usuario("","");
    }



    public boolean verifyPassword(String password){
        if(password.length() >= 8){
            return true;
        }
        return false;
    }

    public void addCSV(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String s = "";

        String [] headers = br.readLine().toLowerCase().strip().split(",");

        while((st = br.readLine()) != null){
            String [] user = st.strip().split(",");

            if(headers[0].equalsIgnoreCase("usuario")){
                if(verifyPassword(user[1].strip())){
                    add(user[0].strip(), new Usuario(user[0].strip(), user[1].strip()));
                }else{
                    usuariosPassInvalidos.push(new Usuario(user[0].strip(), user[1].strip()));
                }
            }else{
                if(verifyPassword(user[0].strip())){
                    add(user[1].strip(), new Usuario(user[1].strip(), user[0].strip()));
                }else{
                    usuariosPassInvalidos.push(new Usuario(user[1].strip(), user[0].strip()));
                }
            }
        }

    }


    public int getSize() {
        return size;
    }


    public void graph() throws IOException, InterruptedException {
        String data_graph = "";
        String relations = "";

        for(int i = 0; i < table.length; i++){
            if(table[i] == null){
                data_graph += "" +
                        "   <TR>\n" +
                        "       <TD>"+ i +"</TD>\n" +
                        "   </TR>\n\n";
            }else{
                data_graph += "" +
                        "   <TR>\n" +
                        "        <TD>"+ i +"</TD>\n" +
                        "         <TD>\n" +
                        "             <TABLE BORDER=\"0\">\n" +
                        "                 <TR><TD><b>USER: </b>" + table[i].getUsuario() + "</TD></TR>\n" +
                        "                 <TR><TD><b>PASSWORD: </b>" + table[i].getPassword() + "</TD></TR>\n" +
                        "                 <TR><TD><b>TIMESTAMP: </b>" + table[i].getFecha() + "</TD></TR>\n" +
                        "              </TABLE>\n" +
                        "         </TD>\n" +
                        "   </TR>\n\n";
            }
        }

        data_graph = String.format("hashtable[label=<<TABLE BORDER=\"0\"  CELLBORDER=\"1\"  CELLSPACING=\"0\">%s</TABLE>>];\n",data_graph);
        data_graph += "labelloc=\"t\";\n";
        data_graph += "label=\""+ "HashTable - " + capacity + " positions [" + usePercentage() + "% of use = " + size +" users ]"  +"\";\n";

        Graphic g = new Graphic("hashTable", data_graph, "LR","plaintext");
    }


    public void print(){
        if(size > 0){
            for (Usuario u: table){
                if (u != null){
                    System.out.println(u.getUsuario() + " ---------- " + u.getFecha());
                }
            }
        }
    }

    public void printInvalidUsers(){
        Stack<Usuario> u_reps = new Stack<>();
        Stack<Usuario> u_pass = new Stack<>();

        while(usuariosRepetidos.hasNext()){
            Usuario u = usuariosRepetidos.pop().getData();
            u_reps.push(u);
            System.out.println("REPETIDO: " + u.getUsuario());
        }

        while(usuariosPassInvalidos.hasNext()){
            Usuario u = usuariosPassInvalidos.pop().getData();
            u_pass.push(u);
            System.out.println("CONTRASEÑA MALA: " + u.getUsuario());
        }

        usuariosRepetidos = u_reps;
        usuariosPassInvalidos = u_pass;
    }


    public String [] getInvalidUsers(){
        String [] invalidUsers = new String[usuariosRepetidos.getSize() + usuariosPassInvalidos.getSize()];
        Stack<Usuario> u_reps = new Stack<>();
        Stack<Usuario> u_pass = new Stack<>();
        int index = 0;

        while(usuariosRepetidos.hasNext()){
            Usuario u = usuariosRepetidos.pop().getData();
            u_reps.push(u);
            invalidUsers[index] = u.getUsuario() + ",Usuario Repetido";
            index++;
        }

        while(usuariosPassInvalidos.hasNext()){
            Usuario u = usuariosPassInvalidos.pop().getData();
            u_pass.push(u);
            invalidUsers[index] = u.getUsuario() + ",Contraseña menor a 8 caracteres";
            index++;
        }

        usuariosRepetidos = u_reps;
        usuariosPassInvalidos = u_pass;

        return invalidUsers;

    }



}
