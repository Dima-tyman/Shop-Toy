public class Toy {

    public static int ids = 1;
    protected int id;
    protected String name;


    Toy (String name, int id) {
        this.name = name;
        this.id = id;
    }

    Toy (String name) {
        this(name, ids++);
    }


    @Override
    public String toString() {
        return String.format("(#%d)%s", this.id, this.name);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
