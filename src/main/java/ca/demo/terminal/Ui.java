package ca.demo.terminal;
import java.io.IOException;
import java.util.Scanner;

public class Ui {

    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";

    public void TakeInput() throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in);
            String inputString = in.nextLine();
            String check = inputString.split(" ")[0];
            int x = 0;

            ErrorHandling abc = new ErrorHandling();
            x = abc.handle_error(inputString);

            if (x == 0) {
                ProcessCommands pcd = new ProcessCommands();
                pcd.process(inputString, check);
            }

            if(inputString.equals("bye")){
                break;
            }
        }
    }

}
