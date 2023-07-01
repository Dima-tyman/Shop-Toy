//Добавить имеющуюся игрушку

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop(new ToyNote("Bear", 2, 2), new ToyNote("Cat"), new ToyNote("Dog"));
        shop.viewAll();

        System.out.println();
        System.out.println(shop.findToy(1));
        shop.changeToyPriority(1, 1);
        System.out.println(shop.findToy(1));
        System.out.println();

        shop.playToy();
        shop.playToy();
        shop.playToy();

        shop.viewAll();

        shop.getToy();
        shop.getToy();

        shop.viewAll();
    }
}