package ca.demo.terminal;
import java.io.IOException;
import java.util.Scanner;

public class Ui {

    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";

    public void TakeInput() throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in); //taking user input
            String inputString = in.nextLine();
            /*
            The input string is split at the empty spaces in order to separate the words entered and obtain the first
            word. The first word describes the kind of task it is (i.e. Todo, Deadline or Event).
             */

            String check = inputString.split(" ")[0];
            int x = 0;

            /*
            An instance of the class ErrorHandling is created to account for random words entered or words with empty
            conditions. An error is then thrown to the console after processing the input string. It returns 1 if there
            is an error and the code won't be processed further. If it returns a 0, this indicates that there is no
            error and the input String is processed further.
             */

            ErrorHandling abc = new ErrorHandling();
            x = abc.handle_error(inputString);

            if (x == 0) {
                /*
                As there is no error, the input string is processed further. An instance of the class ProcessCommands is
                created and the input string is processed to carry out the necessary actions.
                 */
                ProcessCommands pcd = new ProcessCommands();
                pcd.process(inputString, check);
            }

            if(inputString.equals("bye")){
                break; //if the user enters bye, the program is stopped from running.
            }
        }
    }

}
