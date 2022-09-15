public class DeluxeBurger extends Hamburger{
    public DeluxeBurger() {
        super("Deluxe", "Sausage & Bacon", "White", 15.00);
        super.addHamburgerAddition1("chips", 3.00);
        super.addHamburgerAddition2("drink", 1.10);
    }

    @Override
    public void addHamburgerAddition1(String name, double price) {
        System.out.println("Cannot not add additional items to a deluxe burger");
    }

    @Override
    public void addHamburgerAddition2(String name, double price) {
        System.out.println("Cannot not add additional items to a deluxe burger");
    }

    @Override
    public void addHamburgerAddition3(String name, double price) {
        System.out.println("Cannot not add additional items to a deluxe burger");
    }

    @Override
    public void addHamburgerAddition4(String name, double price) {
        System.out.println("Cannot not add additional items to a deluxe burger");
    }
}
