package ca.demo.terminal;

/*
    This class is the defining class in the project. It contains the description of the task that is entered by the user
    and also the status of the task i.e. whether it is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // This function returns a cross or tick unicode character based on whether the task entered is done or not.
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //This function returns a 0 or 1 depending on whether the task is done or not as it is stored in the file this way.
    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    //This function changes the boolean value of isDone to true upon completion of a particular task
    public void markAsDone() {
        this.isDone = true;
    }

    //...
    //This function returns the description of the task in form of a string.
    public String description() {
        return this.description;
    }
}