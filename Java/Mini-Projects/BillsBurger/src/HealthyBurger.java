public class HealthyBurger  extends  Hamburger {
    private String healthyExtra1Name;
    private String healthyExtra2Name;
    private double healthyExtra1Price;
    private double healthyExtra2Price;

    public HealthyBurger(String meat,  double price) {
        super("Healthy Burger", meat, "White", price);

    }

    public void addHealthyAddition1(String name, double price) {
        System.out.println("Added " + name + " for an extra " + price);
        this.healthyExtra1Name = name;
        this.healthyExtra1Price = price;
    }
    public void addHealthyAddition2(String name, double price) {
        System.out.println("Added " + name + " for an extra " + price);
        this.healthyExtra2Name = name;
        this.healthyExtra2Price = price;
    }

    @Override
    public double itemizeHamburger() {
        double healthyBurgerPrice = super.itemizeHamburger();
        if (healthyExtra1Name != null) {
            healthyBurgerPrice += healthyExtra1Price;
        }
        if (healthyExtra2Name != null) {
            healthyBurgerPrice += healthyExtra2Price;
        }
        System.out.println("Total Healthy Burger price is " + healthyBurgerPrice);
        return healthyBurgerPrice;
    }
}
