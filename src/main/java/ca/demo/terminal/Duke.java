package ca.demo.terminal;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";
    private static List<String> lines;
    private static ArrayList<Task> mytasks = new ArrayList<Task>();

    public static ArrayList<Task> getTasks() {
        return mytasks;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");
        HandleFile hdf = new HandleFile();
        hdf.ReadFile();
        Ui ui = new Ui();
        ui.TakeInput();
    }
}