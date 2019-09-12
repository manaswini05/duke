package ca.demo.terminal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
This class takes in the input string and processes the necessary actions to do. It also updates the text file with
the tasks that have been added by the user.
 */

public class ProcessCommands {
    ArrayList<Task> mytasks1 = Duke.getTasks();
    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";

    public void process(String inputString, String check) throws IOException {

        BufferedWriter bw = null; //initialising bufferedwriter and filewriter to
        FileWriter fw = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                //if the file doesnt exist as mentioned in the filename, it is created
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            if (inputString.equals("bye")) { //output to the console if "bye" is entered by the user.
                System.out.println("------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------");
            } else if (inputString.equals("list")) {
                System.out.println("------------------------------");
                System.out.println("Here are the tasks in your list:");
                /*from the dynamic array list that is taken from the main Duke class, tasks are added to it and are
                  printed out to the console one by one when "list" is entered by the user.
                 */
                for (int j = 0; j < mytasks1.size(); j += 1) {
                    System.out.print((j + 1) + ". ");
                    System.out.println(mytasks1.get(j));
                }
                System.out.println("------------------------------");
            } else if (check.equals("deadline")) {
                /* if the first word entered by the user is "deadline", the input string is broken down and processed in
                    order to get the date of the deadline ad the time. As these are needed to create a new Deadline, it
                    is passed onto the new Deadline that is created, which in turn is added onto mytasks1 ArrayList.
                    This then is written to the text file in the format: D-0/1-description-deadline date-deadline time.
                    The 0/1 indicate if the task is done or not.
                 */
                System.out.println("------------------------------");
                System.out.println("Got it. I've added this task: ");
                int index = inputString.indexOf('/');
                String deadline_by = inputString.substring(index + 4, inputString.length() - 5);
                String time_deadline = inputString.substring(inputString.length() - 4);
                String deadline = inputString.substring(9, index - 1);

                mytasks1.add(new Deadline(deadline, UnderstandDate.convertDate(deadline_by, time_deadline)));
                System.out.println(mytasks1.get(mytasks1.size() - 1));
                System.out.println("Now you have " + mytasks1.size() + " tasks in the list.");
                System.out.println("------------------------------");
                String data = "D-" + mytasks1.get(mytasks1.size() - 1).getStatusNumber() + "-" + deadline + "-" + UnderstandDate.convertDate(deadline_by, time_deadline) + "\n";
                bw.write(data);
            } else if (check.equals("todo")) {
                /* if the first word entered by the user is "to do", the input string is broken down and processed to
                    get the task as the "to do" doesn't have a date or time. A new instance of ToDo is then created and
                    added to the mytasks1 array list. Also, the string "data" is written to the text file in the format
                    T-0/1-description. The 0/1 indicate if the task is done or not.
                 */
                System.out.println("------------------------------");
                System.out.println("Got it. I've added this task: ");
                String desc = inputString.substring(5);
                mytasks1.add(new ToDo(desc));
                System.out.println(mytasks1.get(mytasks1.size() - 1));
                System.out.println("Now you have " + mytasks1.size() + " tasks in the list.");
                System.out.println("------------------------------");
                String data = "T-" + mytasks1.get(mytasks1.size() - 1).getStatusNumber() + "-" + desc + "\n";
                bw.write(data);
            } else if (check.equals("event")) {
                /* if the first word entered by the user is "event", the input string is broken down and processed in
                   order to get the date of the event and the time. As these are needed to create a new Even, it
                   is passed onto the new Event that is created, which in turn is added onto mytasks1 ArrayList.
                   This then is written to the text file in the format: D-0/1-description-event date-event time.
                   The 0/1 indicate if the task is done or not.
                 */
                System.out.println("------------------------------");
                System.out.println("Got it. I've added this task: ");
                int index = inputString.indexOf('/');
                String event = inputString.substring(6, index - 1);
                String event_at = inputString.substring(index + 4, inputString.length() - 5);
                String time_event = inputString.substring(inputString.length() - 4);
                mytasks1.add(new Event(event, UnderstandDate.convertDate(event_at, time_event)));
                System.out.println(mytasks1.get(mytasks1.size() - 1));
                System.out.println("Now you have " + mytasks1.size() + " tasks in the list.");
                System.out.println("------------------------------");
                String data = "E-" + mytasks1.get(mytasks1.size() - 1).getStatusNumber() + "-" + event + "-" + UnderstandDate.convertDate(event_at, time_event) + "\n";
                bw.write(data);
            } else if (check.equals("delete")) {
                //if the user input is "delete", a method from the class DeleteItems is called for further processing
                DeleteItems dlt = new DeleteItems();
                dlt.processing(inputString);
            } else if (check.equals("find")) {
                /*
                    if the user input is "find", the existence of the particular word is checked, i.e. if any of the
                    descriptions contain it by going through the array list. The task at that particular index is then
                    printed out to the console.
                 */
                String toBeFound = inputString.split(" ")[1];
                System.out.println("------------------------------");
                int b = 1;
                for (int a = 0; a < mytasks1.size(); a++) {
                    if (mytasks1.get(a).description().contains(toBeFound)) {
                        System.out.println(b + ". " + mytasks1.get(a));
                        b++;
                    }
                }
                System.out.println("------------------------------");
            } else if (check.equals("done")) {
                /* if the first word entered by the user is "done", a new instance of the class UpdateFile is created
                    to change the number 0 to 1, wherever the task is located in the text file. The method in the class
                    UpdateFile takes in the inputString as an argument.
                 */
                UpdateFile upd = new UpdateFile();
                upd.processing(inputString);
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}


