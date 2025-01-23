public class Deadlines extends Task {
    public Deadlines(String deadline) {
        super(deadline);
    }

    @Override
    public String toString() {
        return "[D] " + (super.isMarked() ? "[x] " : "[ ] ")
            + super.task.substring(0, super.task.indexOf(" /by ")) + " (by: "
            + super.task.substring(super.task.indexOf(" /by ") + 5) + ")";
    }
}
