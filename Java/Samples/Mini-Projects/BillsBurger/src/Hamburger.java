public class Hamburger {
    private String name;
    private String meat;
    private String breadRollType;
    private double price;

    public Hamburger() {

    }

    public Hamburger(String name, String meat, String breadRollType, double price) {
        this.name = name;
        this.meat = meat;
        this.breadRollType = breadRollType;
        this.price = price;
        System.out.println(name + " on a " + breadRollType + " with " + meat + ", price is " + price);
    }

    private String addition1Name;
    private double addition1Price;
    private String addition2Name;
    private double addition2Price;
    private String addition3Name;
    private double addition3Price;
    private String addition4Name;
    private double addition4Price;

    public void addHamburgerAddition1(String name, double price) {
        System.out.println("Added " + name + " for an extra " + price);
        this.price += price;
    }

    public void addHamburgerAddition2(String name, double price) {
        System.out.println("Added " + name + " for an extra " + price);
        this.price += price;
    }

    public void addHamburgerAddition3(String name, double price) {
        System.out.println("Added " + name + " for an extra " + price);
        this.price += price;
    }

    public void addHamburgerAddition4(String name, double price) {
        System.out.println("Added " + name + " for an extra " + price);
        this.price += price;
    }

    public double itemizeHamburger() {
        System.out.println("Total " + name + " price is " + price);
        return price;
    }


}
