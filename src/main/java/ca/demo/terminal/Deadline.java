package ca.demo.terminal;

/*
    This class is an extension of the class Task, wherein it takes in the description of the Task(Deadline) and by when it is
    supposed to be done. This then returns a string which contains the information of the type of task it is, whether
    it is done or not and by when it is supposed to be done.
 */
public class Deadline extends Task {
    protected String by;

    /*
        @param description description or what the deadline is
        @param by when the deadline is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /*
        @return a string in the format with which it is stored in the dynamic arraylist.
     */
    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + super.description()  + " (by: " + by + ")");
    }
}
