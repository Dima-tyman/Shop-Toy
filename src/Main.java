public class Main {
    public static void main(String[] args) {
        Toy cat = new Toy("Cat", 3);
        Toy dog = new Toy("Dog");
        Shop shop = new Shop(new Toy("Bear", 3), new Toy("Rabbit"));
        shop.putToys(cat, dog);
        shop.putToy(new Toy("Snake", 2), 2);

        shop.viewAll();

        shop.playToy();
        shop.playToy();
        shop.playToy();

        shop.viewAll();

        shop.getToy();

        shop.viewAll();

        shop.changeToyPriority(5, 4);
        shop.removeToy(dog);
        shop.changeToyCount(1, -5);
        shop.putToy(shop.findToy(3), 4);

        shop.viewAll();
    }
}