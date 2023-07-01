public class ToyNote extends Toy{

    protected int priority;
    protected int count;


    ToyNote(String name, int priority, int count) {
        super(name);
        setPriority(priority);
        setCount(count);
    }

    ToyNote(String name, int priority) {
        this(name, priority, 1);
    }

    ToyNote(String name) {
        this(name, 1);
    }


    @Override
    public String toString() {
        return String.format("#%d \"%s\" (%d) - %d ptc.",
                this.id, this.name, this.priority, this.count);
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = (priority > 100 || priority <= 0) ? 1 : priority;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count < 1 ? 0 : count;
    }
}