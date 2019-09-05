package ca.demo.terminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HandleFile {
    private static final String filename = "/Users/manaswinitalagadadivi/duke/store.txt";
    File file = new File(filename);
    Duke dj = new Duke();
    ArrayList<Task> mytasks1 = Duke.getTasks();

    //reading and storing what already exists in the file
        public void ReadFile() {
            try {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String st;
                while ((st = br.readLine()) != null) {
                    String[] data = st.split("-");
                    if (data[0].equals("T")) {
                        if (data.length < 4) {
                            ToDo t = new ToDo(data[2]);
                            if (data[1].equals("1")) {
                                t.isDone = true;
                            } else {
                                t.isDone = false;
                            }
                            mytasks1.add(t);
                        }
                    }

                    if (data[0].equals("D")) {
                        if (data.length < 6) {
                            String deadline_date = data[3];
                            String deadline_time = data[4];
                            String deadline = data[2];
                            Deadline d = new Deadline(deadline, deadline_date + " -" + deadline_time);
                            if (data[1].equals("1")) {
                                d.isDone = true;
                            } else {
                                d.isDone = false;
                            }
                            mytasks1.add(d);
                        }
                    }

                    if (data[0].equals("E")) {
                        if (data.length < 6) {
                            String event_date = data[3];
                            String event_time = data[4];
                            String event = data[2];
                            Event e = new Event(event, event_date + " " + event_time);
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
