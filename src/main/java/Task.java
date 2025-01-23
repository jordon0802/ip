public class Task {
    private String task;
    private boolean isMarked;

    public Task(String task) {
        this.task = task;
        this.isMarked = false;
    }

    public boolean isMarked() {
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
        return task == null ? "" : (this.isMarked ? "[x] " : "[ ] ") + this.task;
    }
}
