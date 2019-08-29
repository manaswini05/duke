package ca.demo.terminal;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");

        while (true) {
            Scanner in = new Scanner(System.in);
            String inputString = in.nextLine();
            String t = "bye";
            if (inputString.equals(t)) {
                System.out.println("------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------");
                break;
            } else {
                System.out.println("------------------------------");
                System.out.println(inputString);
                System.out.println("------------------------------");
            }
        }
    }
}

