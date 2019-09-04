package ca.demo.terminal;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";
    private static List<String> lines;

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

    static void error_handling(String input) throws MyException {
        String first = input.split(" ")[0];
        if (first.equals("todo") || first.equals("deadline") || first.equals("event") || first.equals("done") || first.equals("list")
                || first.equals("bye")) {
            if (input.equals("todo") || input.equals("deadline") || input.equals("event") || input.equals("done")) {
                throw new MyException("OOPS!!! The description of a " + input + " cannot be empty.");
            }
        } else {
            throw new MyException(("OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");
        //String[] storage= new String[100];
        ArrayList<Task> mytasks = new ArrayList<Task>();
        int i = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String st;
            while ((st = br.readLine()) != null) {
                String[] data = st.split("-");
                if (data[0].equals("T")) {
                    ToDo t = new ToDo(data[2]);
                    if (data[1].equals("1")) {
                        t.isDone = true;
                    } else {
                        t.isDone = false;
                    }
                    mytasks.add(t);
                    i = i + 1;
                }

                if (data[0].equals("D")) {
                    String deadline_date = data[3];
                    String deadline_time = data[4];
                    String deadline = data[2];
                    Deadline d = new Deadline(deadline, deadline_date + " -" + deadline_time);
                    if (data[1].equals("1")) {
                        d.isDone = true;
                    } else {
                        d.isDone = false;
                    }
                    mytasks.add(d);
                    i = i + 1;
                }

                if (data[0].equals("E")) {
                    String event_date = data[3];
                    String event_time = data[4];
                    String event = data[2];
                    Event e = new Event(event, event_date + " " + event_time);
                    if (data[1].equals("1")) {
                        e.isDone = true;
                    } else {
                        e.isDone = false;
                    }
                    mytasks.add(e);
                    i = i + 1;
                }
            }

        } catch (IOException e) {
                    e.printStackTrace();
        }



        while (true) {
            BufferedWriter bw = null;
            FileWriter fw = null;
            Scanner in = new Scanner(System.in);
            String inputString = in.nextLine();
            String bye = "bye";
            String list = "list";
            String check = inputString.split(" ")[0];
            String done = "done";
            int x = 0;

            try {
                error_handling(inputString);
            } catch (MyException e) {
                System.out.println("------------------------------");
                System.out.println(e);
                System.out.println("------------------------------");
                x = 1;
            }

            if (x == 0) {
                try {
                    File file = new File(filename);
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);

                    if (inputString.equals(bye)) {
                        System.out.println("------------------------------");
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("------------------------------");
                        break;
                    }

                    else if (inputString.equals(list)) {
                        System.out.println("------------------------------");
                        System.out.println("Here are the tasks in your list:");
                        for (int j = 0; j < mytasks.size(); j += 1) {
                            System.out.print((j + 1) + ". ");
                            System.out.println(mytasks.get(j));
                        }
                        System.out.println("------------------------------");
                    } else if (check.equals("deadline")) {
                        System.out.println("------------------------------");
                        System.out.println("Got it. I've added this task: ");
                        int index = inputString.indexOf('/');
                        String deadline_by = inputString.substring(index + 4, inputString.length() - 5);
                        String time_deadline = inputString.substring(inputString.length() - 4);
                        String deadline = inputString.substring(9, index - 1);

                        mytasks.add(new Deadline(deadline, UnderstandDate.convertDate(deadline_by, time_deadline)));
                        System.out.println(mytasks.get(i));

                        i = i + 1;
                        System.out.println("Now you have " + mytasks.size() + " tasks in the list.");
                        System.out.println("------------------------------");
                        String data = "D-" + mytasks.get(i - 1).getStatusNumber() + "-" + deadline + "-" + UnderstandDate.convertDate(deadline_by, time_deadline) + "\n";
                        bw.write(data);
                    } else if (check.equals("todo")) {

                        System.out.println("------------------------------");
                        System.out.println("Got it. I've added this task: ");
                        String desc = inputString.substring(5);
                        mytasks.add(new ToDo(desc));
                        System.out.println(mytasks.get(i));
                        i = i + 1;
                        System.out.println("Now you have " + mytasks.size() + " tasks in the list.");
                        System.out.println("------------------------------");
                        String data = "T-" + mytasks.get(i - 1).getStatusNumber() + "-" + desc + "\n";
                        bw.write(data);

                    } else if (check.equals("event")) {
                        System.out.println("------------------------------");
                        System.out.println("Got it. I've added this task: ");
                        int index = inputString.indexOf('/');
                        String event = inputString.substring(6, index - 1);
                        String event_at = inputString.substring(index + 4, inputString.length() - 5);
                        String time_event = inputString.substring(inputString.length() - 4);
                        mytasks.add(new Event(event, UnderstandDate.convertDate(event_at, time_event)));
                        System.out.println(mytasks.get(i));
                        i = i + 1;
                        System.out.println("Now you have " + mytasks.size() + " tasks in the list.");
                        System.out.println("------------------------------");
                        String data = "E-" + mytasks.get(i - 1).getStatusNumber() + "-" + event + "-" + UnderstandDate.convertDate(event_at, time_event) + "\n";
                        bw.write(data);
                    } else if (check.equals(done)) {
                        char[] chars = inputString.toCharArray();
                        StringBuilder sb = new StringBuilder();
                        for (char c : chars) {
                            if (Character.isDigit(c)) {
                                sb.append(c);
                            }
                        }

                        int result = Integer.parseInt(String.valueOf(sb));
                        mytasks.get(result - 1).markAsDone();
                        System.out.println("------------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.print("[" + mytasks.get(result - 1).getStatusIcon() + "] ");
                        System.out.println(mytasks.get(result - 1).description());
                        System.out.println("------------------------------");

                        String help = mytasks.get(result - 1).toString();
                        Character type = help.charAt(1);
                        Character todo = 'T';
                        Character event = 'E';
                        Character deadline = 'D';

                        if(type.equals(todo)) {
                            String imp = mytasks.get(result - 1).description();
                            lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
                            changeValueOf(imp, 1);
                            Files.write(file.toPath(), changeValueOf(imp, 1), Charset.defaultCharset());
                        }
                        if((type.equals(event)) || (type.equals(deadline))) {
                            String imp = mytasks.get(result - 1).description();
                            lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
                            changeValueOf1(imp, 1);
                            Files.write(file.toPath(), changeValueOf1(imp, 1), Charset.defaultCharset());
                        }

                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw != null) {
                            bw.close();
                        }
                        if(fw != null) {
                            fw.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}