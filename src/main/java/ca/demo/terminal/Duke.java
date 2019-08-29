package ca.demo.terminal;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");
        String[] storage= new String[100];
        int i = 0;


        while (true) {
            Scanner in = new Scanner(System.in);
            String inputString = in.nextLine();
            String bye = "bye"; String list = "list";
            if (inputString.equals(bye)) {
                System.out.println("------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("------------------------------");
                break;
            }
            else if (inputString.equals(list)) {
                System.out.println("------------------------------");
                for (int j = 0; j < 100; j += 1) {
                    if(storage[j] != null) {
                        System.out.print((j + 1) + ". ");
                        System.out.println(storage[j]);
                    }
                }
                System.out.println("------------------------------");
            }

            else {
                storage[i] = inputString;
                i = i + 1;
                System.out.println("------------------------------");
                System.out.print("added: ");
                System.out.println(inputString);
                System.out.println("------------------------------");
            }
        }
    }
}

