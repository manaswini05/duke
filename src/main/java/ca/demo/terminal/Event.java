package ca.demo.terminal;

public class Event extends Task {
    protected String event_at;

    public Event(String description, String event_at) {
        super(description);
        this.event_at = event_at;
    }

    @Override
    public String toString() {
        return ("[E][" + super.getStatusIcon() + "] " + super.description() + " (at: " + event_at + ")");
    }
}

