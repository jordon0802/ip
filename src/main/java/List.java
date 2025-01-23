import java.util.LinkedList;

public class List {
    LinkedList<String> list;

    public List() {
        list = new LinkedList<>();
    }

    public void append(String s) {
        list.add(s);
    }

    public String[] toStringArray() {
        if (list != null) {
            String[] temp = new String[list.size()];
            return list.toArray(temp);
        } else {
            return new String[0];
        }
    }
}
