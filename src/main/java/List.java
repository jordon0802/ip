import java.util.LinkedList;

public class List {
    private LinkedList<Task> list;

    public List() {
        this.list = new LinkedList<>();
    }

    public void append(Task t) {
        this.list.add(t);
    }

    public Task[] toTaskArray() {
        if (this.list != null) {
            Task[] temp = new Task[this.list.size()];
            return this.list.toArray(temp);
        } else {
            return new Task[0];
        }
    }
}
