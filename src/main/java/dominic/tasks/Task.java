package dominic.tasks;

import dominic.exceptions.InvalidKeywordException;

public abstract class Task {
    private final String TASK;
    private boolean isMarked;

    protected Task(String task) {
        this.TASK = task;
        this.isMarked = false;
    }

    public static void checkKeyword(String arg, String kw) throws InvalidKeywordException {
        if (!arg.contains(kw) || (arg.substring(arg.indexOf(kw) + kw.length()).trim().isEmpty())) {
            throw new InvalidKeywordException("(Use" + kw + "to specify.)");
        }
    }

    protected String getTask() {
        return this.TASK;
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

    public abstract String toFileString();

    @Override
    public String toString() {
        return (this.isMarked ? "[x] " : "[ ] ") + this.TASK;
    }
}
