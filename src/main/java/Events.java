public class Events extends Task {
    public Events(String event) {
        super(event);
    }

    @Override
    public String toString() {
        return "[E] " + (super.isMarked() ? "[x] " : "[ ] ")
            + super.task.substring(0, super.task.indexOf(" /from ")) + " (from: "
            + super.task.substring(super.task.indexOf(" /from ") + 7, super.task.indexOf(" /to "))
            + " to: " + super.task.substring(super.task.indexOf(" /to ") + 5) + ")";
    }
}
