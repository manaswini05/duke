package ca.demo.terminal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProcessCommands {
    ArrayList<Task> mytasks1 = Duke.getTasks();
    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";

    public void process(String inputString, String check) throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            if (inputString.equals("bye")) {
                System.out.println("------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------");
            } else if (inputString.equals("list")) {
                System.out.println("------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < mytasks1.size(); j += 1) {
                    System.out.print((j + 1) + ". ");
                    System.out.println(mytasks1.get(j));
                }
                System.out.println("------------------------------");
            } else if (check.equals("deadline")) {
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
                DeleteItems dlt = new DeleteItems();
                dlt.processing(inputString);
            } else if (check.equals("find")) {
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


