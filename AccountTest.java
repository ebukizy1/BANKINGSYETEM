package BankAccount2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    Account1 account = new Account1("chukwuam", "emmanuel", "08141221934", "1111");

    @Test
    public void testAnAccountExist(){
        assertNotNull(account);
    }
    @Test
    public void testAccountCanCheckBalance(){
        assertEquals(0.0, account.checkBalance("1111"));
    }
    @Test
    public void testAccountCanDeposit(){
        account.deposit(5000);
        assertEquals(5000, account.checkBalance("1111"));
    }
    @Test
    public void testAccountCanDepositTwice(){
        account.deposit(5000);
        account.deposit(5000);
        assertEquals(10000, account.checkBalance("1111"));
    }
    @Test
    public void testAccountCannotCheckBalanceWithWrongPin(){
        account.deposit(5000);
        assertThrows(WrongPinException.class,()->    account.checkBalance("2123"));
    }
    @Test
    public void testAccountCannotDepositNegativeAmount(){
        int amount = -5000;
        assertThrows(NegativeAmountException.class,()->  account.deposit(amount));
    }
    @Test
    public void testAccountCanWithdraw(){
        account.deposit(5000);
        account.withdraw(2000, "1111");
        assertEquals(3000, account.checkBalance("1111"));
    }
    @Test
    public void testAccountCannotWithdrawWithInvalidPin(){
        account.deposit(5000);
        assertThrows(WrongPinException.class, ()->    account.withdraw(2000, "1121"));
    }
    @Test
    public void testAccountCannotWithdrawNegativeAmount(){
        account.deposit(5000);
        assertThrows(NegativeAmountException.class,()->   account.withdraw(-2000, "1111"));
    }
    @Test
    public void testAccountCannotWithdrawAboveBalance(){
        int amount = 10000;
        account.deposit(5000);
        assertThrows(NegativeAmountException.class,()->  account.withdraw(amount, "1111"));
    }
    @Test
    public void testAccountPinIsNotAbove4digit(){
        account.deposit(5000);
    }
}
