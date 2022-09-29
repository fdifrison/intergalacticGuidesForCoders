package banking;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {

	private BankAccount account;

	@BeforeEach
	public void setup() {
		account = new BankAccount("Giovanni", "Frison", 1000.00, BankAccount.CHECKING);
	}

	@Test
	void testBankAccount() {
		assertEquals(1000, account.getBalance(), 0);
	}

	@Test
	void testDeposit() {
		double balance = account.deposit(200, false);
		assertEquals(1200, balance, 0);
		
	}

	@Test
	void testWithdraw() {
		assertEquals(400, account.withdraw(600, true));
	}

	@Test()
	void testWithdraw_failed() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			account.withdraw(600, false);
		});

		assertEquals("Can't withdraw more than 500.00 from ATM", exception.getMessage());
	}

	@Test
	void testGetBalance_deposit() {
		account.deposit(200, false);
		assertEquals(1200, account.getBalance(), 0);
	}

	@Test
	void testGetBalance_withdraw() {
		account.withdraw(200, false);
		assertEquals(800, account.getBalance(), 0);
	}

	@Test
	void isChecking_true() {
		assertTrue("The account is NOT a chekign account", account.isChecking());

	}

}
