import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        customers = new ArrayList<Customer>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
      /*  System.out.println("Customers of branch: " + name);
        for(int i =0; i< customers.size(); i++){
            System.out.println(customers.get(i).getName());
        }*/
        return customers;
    }

    public boolean newCustomer(String name, double initTransaction) {
        if (customers.contains(name)) {
            System.out.println("Customer already present");
            return false;
        } else {
            Customer customer = new Customer(name);
            customer.addTransaction(initTransaction);
            customers.add(customer);
            return true;
        }
    }

    public boolean addCustomerTransaction(String name, double transaction) {
        if (findCustomer(name) != null) {
            findCustomer(name).addTransaction(transaction);
            return true;
        } else return false;
    }

    public Customer findCustomer(String name) {
        if (customers.contains(name)) {
            int indexOf = customers.indexOf(name);
            Customer customer = customers.get(indexOf);
            return customer;
        } else return null;
    }

}
