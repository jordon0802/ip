public class Task {
    protected final String task;
    private boolean isMarked;

    protected Task(String task) {
        this.task = task;
        this.isMarked = false;
    }

    protected boolean isMarked() {
        return this.isMarked;
    }

    public void setMarked() {
        this.isMarked = true;
    }

    public void setUnMarked() {
        this.isMarked = false;
    }

    @Override
    public String toString() {
        return (this.isMarked ? "[x] " : "[ ] ") + this.task;
    }
}
