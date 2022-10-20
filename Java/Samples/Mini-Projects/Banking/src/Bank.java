import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        branches = new ArrayList<Branch>();
    }

    public boolean addBranch(String name) {
        if (branches.contains(name)) {
            System.out.println("Branch already present");
            return false;
        } else {

            Branch newBranch = new Branch(name);
            branches.add(newBranch);
            System.out.println("Branch added");
            return true;
        }
    }

    public boolean addCustomer(String branchName, String name, double initTransaction) {
            Branch branchFound = (findBranch(branchName) != null) ? findBranch(branchName) : null;
            if (branchFound.findCustomer(name) != null) {
                return false;
            } else {
                branchFound.newCustomer(name, initTransaction);
                System.out.println(name + " added to the customers in branch " + branchName);
                return true;
            }


    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
        Branch branchFound = (findBranch(branchName) != null) ? findBranch(branchName) : null;
        if (branchFound != null) {
            if (branchFound.findCustomer(customerName) != null) {
                branchFound.findCustomer(customerName).addTransaction(transaction);
                return true;
            }
        } else return false;
        return false;
    }

    public Branch findBranch(String branchName) {
        for (int i = 0; i < branches.size(); i++) {
            if (branches.get(i).getName() == branchName) {
                Branch branchFound = branches.get(i);
//                System.out.println("Branch found");
                return branchFound;
            } else return null;
        } return null;
    }

    public boolean listCustomers(String branchName) {
        Branch branch = findBranch(branchName);
        if (branch!= null) {
            System.out.println("Customers details for branch: " + branch.getName());
            for( int i = 0; i < branch.getCustomers().size(); i++) {
                System.out.println("Customer: " + branch.getCustomers().get(i).getName() +
                        " Transaction list: " + branch.getCustomers().get(i).getTransaction().toString());
            }
            return  true;
        } return  false;
    }
}