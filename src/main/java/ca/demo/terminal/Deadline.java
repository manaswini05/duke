package ca.demo.terminal;

/*
    This class is an extension of the class Task, wherein it takes in the description of the Task(Deadline) and by when it is
    supposed to be done. This then returns a string which contains the information of the type of task it is, whether
    it is done or not and by when it is supposed to be done.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + super.description()  + " (by: " + by + ")");
    }
}
