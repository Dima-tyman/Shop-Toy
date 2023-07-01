public class Toy {

    public static int ids = 1;
    protected int id;
    protected String name;
    protected int priority;

    Toy(String name, int priority) {
        this.id = ids++;
        this.name = name;
        setPriority(priority);
    }

    Toy(String name) {
        this(name, 1);
    }


    @Override
    public String toString() {
        return String.format("№%d \"%s\"(%d)", this.id, this.name, this.priority);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = (priority > 100 || priority <= 0) ? 1 : priority;
    }
}