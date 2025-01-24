public class Deadlines extends Task {
    private final String deadline;

    public Deadlines(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + (super.isMarked() ? "[x] " : "[ ] ")
            + super.task + " (by: " + this.deadline + ")";
    }
}
