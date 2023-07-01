import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Shop {

    private final File wonToys = new File("wonToys.txt");
    private final ArrayDeque<Toy> outQueue = new ArrayDeque<>();
    private final Map<Toy, Integer> toysList = new HashMap<>();


    Shop(Toy... toys) {
        putToys(toys);
        createEmptyOutFile();
    }

    Shop(Toy toy, int count) {
        putToy(toy, count);
        createEmptyOutFile();
    }


    public void putToys(Toy... toys) {
        for (Toy toy : toys) {
            if (toy == null) continue;
            this.toysList.put(toy, 1);
        }
    }

    public void putToy(Toy toy, int count) {
        if (toy == null) return;
        if (count < 0) {
            removeToy(toy, count);
            return;
        }
        if (this.toysList.containsKey(toy)) {
            int currentCount = this.toysList.get(toy);
            this.toysList.put(toy, currentCount + count);
        } else {
            this.toysList.put(toy, Math.max(1, count));
        }
    }

    public void removeToy(Toy toy, int count) {
        if (!this.toysList.containsKey(toy)) {
            System.out.println("Toy not found!");
            return;
        }
        int currentCount = this.toysList.get(toy);
        if (currentCount <= count) {
            removeToy(toy);
            return;
        }
        this.toysList.put(toy, currentCount - count);
    }

    public void removeToy(Toy toy) {
        if (!this.toysList.containsKey(toy)) {
            System.out.println("Toy not found!");
            return;
        }
        this.toysList.remove(toy);
    }

    public void playToy() {
        if (this.toysList.isEmpty()) {
            System.out.println("No toys in shop! Put him and try play again!");
            return;
        }
        ArrayList<Toy> randomToys = new ArrayList<>();
        for (Map.Entry<Toy, Integer> entry: this.toysList.entrySet()) {
            for (int i = 0;
                 i < entry.getValue() * entry.getKey().getPriority(); i++) {
                randomToys.add(entry.getKey());
            }
        }

        int randomIdx = new Random().nextInt(randomToys.size());
        Toy randomToy = randomToys.get(randomIdx);

        removeToy(randomToy, 1);

        this.outQueue.add(randomToy);
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

    public void viewShop() {
        System.out.println("Toys in shop:");
        for (Map.Entry<Toy, Integer> entry: this.toysList.entrySet()) {
            System.out.printf("\t%s- %d ptc.\n", entry.getKey(), entry.getValue());
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

    public boolean isWonEmpty() {
        return this.outQueue.isEmpty();
    }

    public boolean isShopEmpty() {
        return this.toysList.isEmpty();
    }

    public Toy findToy(int id) {
        for (Map.Entry<Toy, Integer> toyNote : this.toysList.entrySet()) {
            if (toyNote.getKey().getId() == id) return toyNote.getKey();
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

    public void changeToyCount(int id, int count) {
        Toy foundToy = findToy(id);
        if (foundToy == null) {
            System.out.println("Toy not found!");
            return;
        }

        if (count <= 0) {
            removeToy(foundToy);
        }
        else {
            this.toysList.put(foundToy, count);
        }
    }

    public ArrayList<Toy> getOutQueue() {
        return new ArrayList<>(this.outQueue);
    }

    public Map<Toy, Integer> getToysList() {
        return toysList;
    }


    private void createEmptyOutFile() {
        try (FileWriter writer = new FileWriter(this.wonToys, false)) {
            writer.write("Won toys:\n");
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}