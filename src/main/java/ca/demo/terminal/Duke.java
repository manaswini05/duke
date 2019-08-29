package ca.demo.terminal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
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
            String bye = "bye"; String list = "list"; String check = inputString.split(" ")[0];
            String done = "done";

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
                    System.out.print((j + 1) + ". [" );
                    System.out.print(mytasks.get(j).getStatusIcon() + "] ");
                    System.out.println(mytasks.get(j).description());
                }
                System.out.println("------------------------------");
            }

            else if (check.equals(done)) {
                char[] chars = inputString.toCharArray();
                StringBuilder sb = new StringBuilder();
                for(char c : chars) {
                    if(Character.isDigit(c)) {
                        sb.append(c);
                    }
                }

                int result = Integer.parseInt(String.valueOf(sb));
                mytasks.get(result - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("[" + mytasks.get(result - 1).getStatusIcon() + "] ");
                System.out.println(mytasks.get(result - 1).description());
            }

            else {
                Task t = new Task(inputString);
                mytasks.add(t);
                System.out.println("------------------------------");
                System.out.print("added: ");
                System.out.println(inputString);
                System.out.println("------------------------------");
            }
        }
    }
}

