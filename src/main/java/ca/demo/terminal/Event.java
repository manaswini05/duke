package ca.demo.terminal;

/*
    This class is an extension of the class Task, wherein it takes in the description of the Task(Event) and  when it is
    at. This then returns a string which contains the information of the type of task it is, whether it is done or not
    and by when it is supposed to be done.
 */

public class Event extends Task {
    protected String event_at;

    /*
        @param description describes what the event is
        @param event_at when the event is going to be held
     */
    public Event(String description, String event_at) {
        super(description);
        this.event_at = event_at;
    }

    /*
        @return the string which contains all the details of the event in the format it has to be entered in the arraylist
     */
    @Override
    public String toString() {
        return ("[E][" + super.getStatusIcon() + "] " + super.description() + " (at: " + event_at + ")");
    }
}

