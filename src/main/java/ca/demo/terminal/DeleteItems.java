package ca.demo.terminal;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
This class is used to delete items from the list upon user input and also make the likewise changes to the text file.
 */
public class DeleteItems {

    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";
    File file = new File(filename);
    private static List<String> lines;
    Duke dj = new Duke();
    ArrayList<Task> mytasks1 = Duke.getTasks(); //obtains the dynamic array list to store tasks from the main class - Duke.

    /*
        This function adds the word REMOVED on the line in which the particular task to be deleted is found. It will then
        not be processed for further calculations. It takes in the description of the task as a parameter and if the
        3rd part of the split string in the file is the same as the parameter, the word REMOVED is then tagged along.
     */
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

    /* This function obtains the number in the inputString i.e. the number of the task in the list that is to be removed.
        The index is then located in mylists1 and deleted.
     */

    public void processing(String inputString) throws IOException {
        char[] chars = inputString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        int result = Integer.parseInt(String.valueOf(sb)); //converts the digit in the form of a character to integer

        String imp = mytasks1.get(result - 1).description(); //the description of the task at the corresponding index is stored
        lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        AddRemoved(imp); //the description of the task is passed onto the AddRemoved method which has been described above.
        Files.write(file.toPath(), AddRemoved(imp), Charset.defaultCharset());

        System.out.println("------------------------------");
        System.out.println("Noted I've removed this task:");
        System.out.println(mytasks1.get(result - 1));
        mytasks1.remove(result - 1);
        System.out.println("Now you have " + mytasks1.size() + " tasks in your list.");
        System.out.println("------------------------------");
    }
}
