package ca.demo.terminal;

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

