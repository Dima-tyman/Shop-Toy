import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Shop {

    private final File wonToys = new File("wonToys.txt");
    private final ArrayDeque<Toy> outQueue = new ArrayDeque<>();
    private final ArrayList<ToyNote> toysList = new ArrayList<>();


    Shop(ToyNote... toys) {
        this.putToy(toys);
        try (FileWriter writer = new FileWriter(this.wonToys, false)) {
            writer.write("Won toys:\n");
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void viewShop() {
        System.out.println("Toys in shop:");
        for (ToyNote toyNote : this.toysList) {
            System.out.println("\t" + toyNote);
        }
        if (isShopEmpty()) System.out.println("\tNOT TOY!");
    }

    public void viewWonToys() {
        System.out.println("Won toys in queue:");
        for (Toy toy : this.outQueue) {
            System.out.println("\t" + toy);
        }
        if (isWonEmpty()) System.out.println("\tNOT TOY!");
    }

    public void viewAll() {
        viewShop();
        viewWonToys();
    }

    public void putToy(ToyNote... toys) {
        this.toysList.addAll(Arrays.asList(toys));
    }

    public void playToy() {
        if (this.toysList.isEmpty()) {
            System.out.println("No toys in shop! Put him and try play again!");
            return;
        }
        ArrayList<ToyNote> randomToys = new ArrayList<>();
        for (ToyNote toy : this.toysList) {
            for (int i = 0; i < toy.getCount() * toy.getPriority(); i++) {
                randomToys.add(toy);
            }
        }

        int randomIdx = new Random().nextInt(randomToys.size());
        ToyNote randomToy = randomToys.get(randomIdx);

        if (randomToy.getCount() <= 1) this.toysList.remove(randomToy);
        randomToy.setCount(randomToy.getCount() - 1);

        this.outQueue.add(new Toy(randomToy.getName(), randomToy.getId()));
        System.out.printf("Toy %s won! \n", outQueue.getLast());
    }

    public void getToy() {
        if (this.outQueue.isEmpty()) {
            System.out.println("No toys won! Play and try get again!");
            return;
        }
        try (FileWriter writer = new FileWriter(this.wonToys, true)) {
            writer.write(Objects.requireNonNull(outQueue.poll()).toString());
            writer.write("\n");
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Toy getting!");
    }

    public boolean isWonEmpty() {
        return this.outQueue.isEmpty();
    }

    public boolean isShopEmpty() {
        return this.toysList.isEmpty();
    }

    public ToyNote findToy(int id) {
        for (ToyNote toyNote : this.toysList) {
            if (toyNote.getId() == id) return toyNote;
        }
        return null;
    }

    public void changeToyPriority(int id, int priority) {
        if (findToy(id) == null) {
            System.out.println("Toy not found!");
            return;
        }
        findToy(id).setPriority(priority);
    }


    public ArrayList<Toy> getOutQueue() {
        return new ArrayList<>(this.outQueue);
    }

    public ArrayList<ToyNote> getToysList() {
        return toysList;
    }
}