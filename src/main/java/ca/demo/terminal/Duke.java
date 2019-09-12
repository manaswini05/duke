package ca.demo.terminal;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt"; //name of the texxt file in which the list is stored
    private static List<String> lines;
    private static ArrayList<Task> mytasks = new ArrayList<Task>(); //a dynamic array to store the different types of tasks

    public static ArrayList<Task> getTasks() {
        return mytasks;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");
        HandleFile hdf = new HandleFile(); //creates a new instance of the class HandleFile
        hdf.ReadFile(); //calls a function from this class to read the existing tasks
        Ui ui = new Ui(); //creates a new instance of the class Ui
        ui.TakeInput(); //calls a function to take user input regarding the tasks
    }
}