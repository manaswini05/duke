package ca.demo.terminal;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

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

    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");
        //String[] storage= new String[100];
        ArrayList<Task> mytasks = new ArrayList<Task>();
        int i = 0;

        while (true) {
            Scanner in = new Scanner(System.in);
            String inputString = in.nextLine();
            String bye = "bye";
            String list = "list";
            String check = inputString.split(" ")[0];
            String done = "done";
            int x = 0;

            try {
                error_handling(inputString);
            }
            catch (MyException e) {
                System.out.println("------------------------------");
                System.out.println(e);
                System.out.println("------------------------------");
                x = 1;
            }

            if(x == 0) {
                if (inputString.equals(bye)) {
                    System.out.println("------------------------------");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("------------------------------");
                    break;
                } else if (inputString.equals(list)) {
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
                    String deadline_by = inputString.substring(index + 4);
                    String deadline = inputString.substring(9, index - 1);
                    mytasks.add(new Deadline(deadline, deadline_by));
                    System.out.println(mytasks.get(i));
                    i = i + 1;
                    System.out.println("Now you have " + mytasks.size() + " tasks in the list.");
                    System.out.println("------------------------------");
                } else if (check.equals("todo")) {
                    System.out.println("------------------------------");
                    System.out.println("Got it. I've added this task: ");
                    String desc = inputString.substring(5);
                    mytasks.add(new ToDo(desc));
                    System.out.println(mytasks.get(i));
                    i = i + 1;
                    System.out.println("Now you have " + mytasks.size() + " tasks in the list.");
                    System.out.println("------------------------------");
                } else if (check.equals("event")) {
                    System.out.println("------------------------------");
                    System.out.println("Got it. I've added this task: ");
                    int index = inputString.indexOf('/');
                    String event = inputString.substring(6, index - 1);
                    String event_at = inputString.substring(index + 4);
                    mytasks.add(new Event(event, event_at));
                    System.out.println(mytasks.get(i));
                    i = i + 1;
                    System.out.println("Now you have " + mytasks.size() + " tasks in the list.");
                    System.out.println("------------------------------");
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

                } else {
                }
            }
        }
    }
}
