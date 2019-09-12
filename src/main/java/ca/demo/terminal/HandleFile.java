package ca.demo.terminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HandleFile {
    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt"; //text file that stores all the information
    File file = new File(filename);
    Duke dj = new Duke(); //new instance of the main class Duke
    ArrayList<Task> mytasks1 = Duke.getTasks(); //gets the dynamic array from Duke to add tasks to it

    /* The public void function ReadFile reads the text file using the BufferedReader method.
        The string is split at the character '-' to read the different details of the task in hand and add it to the mytasks1 Array List.
        This is because this is the format in which it is stored in the
        Depending on the first letter of each line, the type of task i.e. ToDo/Event/Deadline is created and stored.
        The length of the split string is analysed as some of the tasks in the list might be marked by REMOVED if they have been deleted
     */
        public void ReadFile() {
            try {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String st;
                while ((st = br.readLine()) != null) {
                    String[] data = st.split("-"); //splitting the string at '-' as that is the format with which it is stored
                    if (data[0].equals("T")) { //checks if it is a ToDo
                        if (data.length < 4) { //if it is not REMOVED
                            ToDo t = new ToDo(data[2]); //a new Todo is created
                            if (data[1].equals("1")) {
                                t.isDone = true; //checks if the task is done as the task being done is denoted by '1'
                            } else {
                                t.isDone = false;
                            }
                            mytasks1.add(t); //added to the dynamic array
                        }
                    }

                    if (data[0].equals("D")) { //checks if it is a Deadline
                        if (data.length < 6) { //if it is not REMOVED
                            String deadline_date = data[3]; // the date on which the deadline is due is the 4th part of the split string
                            String deadline_time = data[4]; // the time at which it is due is the 5th part of the split string
                            String deadline = data[2]; //the description of the deadline is the 2nd part of the split string
                            Deadline d = new Deadline(deadline, deadline_date + " -" + deadline_time); //a new deadline is created
                            if (data[1].equals("1")) {
                                d.isDone = true; //checks if it is done
                            } else {
                                d.isDone = false;
                            }
                            mytasks1.add(d);
                        }
                    }

                    if (data[0].equals("E")) { //checks if it is an Event
                        if (data.length < 6) { //if it is not REMOVED
                            String event_date = data[3]; // the date on which the event is on is the 4th part of the split string
                            String event_time = data[4]; // the time of the event is the 5th part of the split string
                            String event = data[2]; //the description of the event is the 2nd part of the split string
                            Event e = new Event(event, event_date + " " + event_time); // a new event is created
                            if (data[1].equals("1")) {
                                e.isDone = true;
                            } else {
                                e.isDone = false;
                            }
                            mytasks1.add(e);
                        }
                    }
                }
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }

}
