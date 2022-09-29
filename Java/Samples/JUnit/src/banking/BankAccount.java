package banking;

class Main {
	

	public static void main(String[] args) {
		System.out.println("Hello from main");

	}

}

public class BankAccount {

	private String firstName;
	private String lastName;
	private double balance;

	public static final int CHECKING = 1;
	public static final int SAVINGS = 2;

	private int accountType;

	public BankAccount(String firstName, String lastName, double balance, int accountType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.accountType = accountType;
	}

	// branch is true if from office, false if from ATM
	public double deposit(double amount, boolean branch) {
		balance += amount;
		return balance;
	}

	public double withdraw(double amount, boolean branch) {
		if ((amount> 500.00) & !branch) {
			throw new IllegalArgumentException("Can't withdraw more than 500.00 from ATM");
		}
		balance -= amount;
		return balance;
	}

	public double getBalance() {
		return balance;
	}

	public boolean isChecking() {
		return accountType == CHECKING;
	}

}
