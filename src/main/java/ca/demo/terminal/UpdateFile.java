package ca.demo.terminal;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class UpdateFile {

    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";
    File file = new File(filename);
    private static List<String> lines;
    Duke dj = new Duke();
    ArrayList<Task> mytasks1 = Duke.getTasks();

    private static List<String> changeValueOf(String imp, int newVal){
        List<String> newLines = new ArrayList<String>();
        for(String line: lines){
            String [] vals = line.split("-");
            if(vals[2].equals(imp)){
                newLines.add(vals[0] + "-" + String.valueOf(newVal) + "-" + vals[2]);
            }
            else {
                newLines.add(line);
            }
        }
        return newLines;
    }

    private static List<String> changeValueOf1(String imp, int newVal){
        List<String> newLines = new ArrayList<String>();
        for(String line: lines){
            String [] vals = line.split("-");
            if(vals[2].equals(imp)){
                newLines.add(vals[0] + "-" + String.valueOf(newVal) + "-" + vals[2] + "-" + vals[3] + "-" + vals[4]);
            } else {
                newLines.add(line);
            }
        }
        return newLines;
    }

    public void processing(String inputString) throws IOException {

        char[] chars = inputString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        int result = Integer.parseInt(String.valueOf(sb));
        mytasks1.get(result - 1).markAsDone();
        System.out.println("------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("[" + mytasks1.get(result - 1).getStatusIcon() + "] ");
        System.out.println(mytasks1.get(result - 1).description());
        System.out.println("------------------------------");

        String help = mytasks1.get(result - 1).toString();
        Character type = help.charAt(1);
        Character todo = 'T';
        Character event = 'E';
        Character deadline = 'D';

        if(type.equals(todo)) {
            String imp = mytasks1.get(result - 1).description();
            lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            changeValueOf(imp, 1);
            Files.write(file.toPath(), changeValueOf(imp, 1), Charset.defaultCharset());
        }
        if((type.equals(event)) || (type.equals(deadline))) {
            String imp = mytasks1.get(result - 1).description();
            lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            changeValueOf1(imp, 1);
            Files.write(file.toPath(), changeValueOf1(imp, 1), Charset.defaultCharset());
        }
    }
}
