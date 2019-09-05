package ca.demo.terminal;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DeleteItems {

    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";
    File file = new File(filename);
    private static List<String> lines;
    Duke dj = new Duke();
    ArrayList<Task> mytasks1 = Duke.getTasks();

    private static List<String> AddRemoved(String imp){
        List<String> newLines = new ArrayList<String>();
        for(String line: lines){
            String [] vals = line.split("-");
            if(vals[2].equals(imp)){
                if(vals[0].equals("T")) {
                    newLines.add(vals[0] + "-" + vals[1] + "-" + vals[2] + "-" + "REMOVED");
                }
                if(vals[0].equals("D") || vals[0].equals("E")) {
                    newLines.add(vals[0] + "-" + vals[1] + "-" + vals[2] + "-" + vals[3] + "-" + vals[4] + "-" + "REMOVED");
                }
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

        String imp = mytasks1.get(result - 1).description();
        lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        AddRemoved(imp);
        Files.write(file.toPath(), AddRemoved(imp), Charset.defaultCharset());

        System.out.println("------------------------------");
        System.out.println("Noted I've removed this task:");
        System.out.println(mytasks1.get(result - 1));
        mytasks1.remove(result - 1);
        System.out.println("Now you have " + mytasks1.size() + " tasks in your list.");
        System.out.println("------------------------------");
    }
}
