package ca.demo.terminal;

/*
    This class is an extension of the class Task, wherein it takes in the description of the Task(ToDo) and by when it is
    supposed to be done. This then returns a string which contains the information of the type of task it is and whether
    it is done or not.
 */

public class ToDo extends Task {
    protected String by;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T][" + super.getStatusIcon() + "] " + super.description());
    }
}

