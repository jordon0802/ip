public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] " + (super.isMarked() ? "[x] " : "[ ] ")
            + super.task + " (from: " + this.from + " to: " + this.to + ")";
    }
}
