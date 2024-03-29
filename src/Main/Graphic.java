package Main;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graphic {

    private String fileName;
    private String fileData;
    private BufferedWriter writer;

    public Graphic(String fileName, String fileData, String rankdir) throws IOException, InterruptedException {
        this.fileName = fileName + ".txt";
        this.fileData = String.format("" +
                        "digraph G{\n" +
                        "   rankdir = %s;\n" +
                        "   node[shape=record];\n" +
                        "   graph[ranksep=\"1\"];\n" +
                        "\n" +
                        "   %s\n" +
                        "}", rankdir, fileData);
        
        File f = new File(fileName);
        f.delete();
        
        this.writer = new BufferedWriter(new FileWriter(this.fileName));
        this.writer.write(this.fileData);
        this.writer.close();

        generateGraphic(fileName);
        //executeCommand(".\\$.png".replace("$",fileName));
    }
    
    public Graphic(String fileName, String fileData, String rankdir, String type, String ranksep) throws IOException, InterruptedException {
        this.fileName = fileName + ".txt";
        this.fileData = String.format("" +
                        "digraph G{\n" +
                        "   rankdir = %s;\n" +
                        "   node[shape=record];\n" +
                        "   graph[ranksep=\"%s\"];\n" +
                        "\n" +
                        "   %s\n" +
                        "}", rankdir, ranksep, fileData);
        
        File f = new File(fileName);
        f.delete();
        
        this.writer = new BufferedWriter(new FileWriter(this.fileName));
        this.writer.write(this.fileData);
        this.writer.close();

        generateGraphic(fileName, type);
        //executeCommand("C:/reports/$.png".replace("$",fileName));
    }

    public Graphic(String fileName, String fileData, String rankdir, String nodeShape ) throws IOException, InterruptedException {
        this.fileName = fileName + ".txt";
        this.fileData = String.format("" +
                "digraph G{\n" +
                "  rankdir = %s;\n" +
                "  node[shape=%s];\n" +
                "\n" +
                "  %s\n" +
                "}", rankdir,nodeShape, fileData);
        
                
        this.writer = new BufferedWriter(new FileWriter(this.fileName));
        this.writer.write(this.fileData);
        this.writer.close();

        
        generateGraphic(fileName);
        //executeCommand("C:/reports/$.png".replace("$",fileName));
    }
    
    public Graphic(String fileName){
        try {
            generateGraphic(fileName);
        } catch (IOException ex) {
            Logger.getLogger(Graphic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Graphic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateGraphic(String fileName) throws IOException, InterruptedException {
        
        System.out.println(new File("C:/reports").exists());
        
        if(!new File("C:/reports").exists()){
            new File("C:/reports").mkdirs();
        }

        
        executeCommand("dot -Tpng $.txt -o C:/reports/$.png".replace("$", fileName));
        
    }
    
    private void generateGraphic(String fileName, String type) throws IOException, InterruptedException {
        
        
        System.out.println(new File("C:/reports").exists());
        
        if(!new File("C:/reports").exists()){
            new File("C:/reports").mkdirs();
        }
        
        File f = new File("C:/reports/" + fileName + ".png");
        f.delete();
        
        executeCommand(type + " -Tpng $.txt -o C:/reports/$.png".replace("$", fileName));
    }

    private void executeCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("cmd.exe", "/c", command);
        Process p = pb.start();

        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        while((line = r.readLine()) != null){
            sb.append(line + "\n");
        }

        int exitVl = p.waitFor();
        if(exitVl == 0){
            System.out.println("Success!");
            System.out.println(sb);
        }else{
            System.out.println("Error!");
        }
    }



}
