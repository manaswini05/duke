package ca.demo.terminal;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
    This class is used to obtain the data in the text file. The status of the task i.e. whether it is done or not is stored
    as either 0 or 1. Whenever a task is marked done by the user through the console, the necessary update has to be made
    in the file to change the number from 0 to 1. This class processes the input string and likewise changes the values
    in the text file.
 */
public class UpdateFile {

    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";
    File file = new File(filename);
    private static List<String> lines;
    Duke dj = new Duke();
    ArrayList<Task> mytasks1 = Duke.getTasks();

    /* This function is called if the task that has been done is a To Do and not an event or Deadline. All the lines
        in the text file are split. Whenever the 3rd part of the split string(which is the description) is equal to the
        String imp(description of the task), the value of 0 is replaced with 1.
        @param imp the description of the task
        @param newVal the value to which the old value in a particular line is to be changed
        @return newLines the new line in the text file that has been updated
     */
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

    /* This function is called if the task that has been done is an Event or a Deadline and not a To Do. All the lines
        in the text file are split. Whenever the 3rd part of the split string(which is the description) is equal to the
        String imp(description of the task), the value of 0 is replaced with 1.
        @param imp the description of the task
        @param newVal the value to which the old value in a particular line is to be changed
        @return newLines the new line in the text file that has been updated
     */

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

    /*
        This function processes the input string. It obtains the number of the task in the list done and checks the nature
        of the task, if it is a To Do, Event or Deadline and then calls the relevant function from the above 2 for the
        change to be made.
        @param inputString
     */
    public void processing(String inputString) throws IOException {

        char[] chars = inputString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        int result = Integer.parseInt(String.valueOf(sb));
        mytasks1.get(result - 1).markAsDone(); //the task entered as done is located and marked as done by the change of
                                                //Boolean value in the class Task.
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
