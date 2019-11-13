package Main;

import java.io.*;

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
        this.writer = new BufferedWriter(new FileWriter(this.fileName));
        this.writer.write(this.fileData);
        this.writer.close();

        generateGraphic(fileName);
        executeCommand(".\\$.png".replace("$",fileName));
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
        executeCommand(".\\$.png".replace("$",fileName));
    }

    private void generateGraphic(String fileName) throws IOException, InterruptedException {
        executeCommand("dot -Tpng $.txt -o $.png".replace("$", fileName));
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
