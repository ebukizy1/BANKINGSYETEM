package BankAccount2;

import bankaccount.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Bank1Test {

    Bank1 eMaxBank = new Bank1();

    @Test
    public void testBankExist() {
        assertNotNull(eMaxBank);
    }

    @Test
    public void testBankCanRegisterAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        assertEquals(1, eMaxBank.getNumbersOfAccount());
    }

    @Test
    public void testBankCanRegisterTwiceTheCustomer() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        eMaxBank.registerCustomers("joseph", "emeka", "07038243709", "1211");
        eMaxBank.depositMoney("8141221934", 5000);
        assertEquals(5000, eMaxBank.checkBalance("8141221934", "1111"));
    }

    @Test
    public void testCustomerCantRegisterWithPinAboveFourDigit() {
        assertThrows(WrongPinException.class, () -> eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "11121"));
    }

    @Test
    public void testCustomerNamesCannotAllowNumber() {
        assertThrows(WrongNameException.class, () -> eMaxBank.registerCustomers("emman76uel", "ch``ukwuma", "08141221934", "1111"));
    }

    @Test
    public void testCustomerNamesCannotHaveWhiteSpace() {
        assertThrows(WrongNameException.class, () -> eMaxBank.registerCustomers("    ", "        ", "08141221934", "1111"));
    }

    @Test
    public void testCustomerPhoneNumberIsNotMoreThanElevenDigit() {
        assertThrows(WrongPhoneNumberException.class, () -> eMaxBank.registerCustomers("emmanuel", "chukwuma", "89141221934", "1111"));
    }

    @Test
    public void testBankCanGenerateAccountNumberUsingPhoneNumber() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        assertEquals("8141221934", eMaxBank.searchForAccount("8141221934"));
    }

    @Test
    public void testAccountNumberNotFoundInTheListOfBank() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        assertThrows(AccountNumberNotFoundException.class, () -> eMaxBank.searchForAccount("8141221954"));
    }

    @Test
    public void testBankCanDepositToAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        eMaxBank.depositMoney("8141221934", 5000);
        double balance = eMaxBank.checkBalance("8141221934", "1111");
        assertEquals(5000, balance);
    }

    @Test
    public void testBankCanDepositCannotDepositNegativeAmountToAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        assertThrows(NegativeAmountException.class, () -> eMaxBank.depositMoney("8141221934", -5000));
    }

    @Test
    public void testBankCannotCheckBalanceWithWrongAccountNum() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        eMaxBank.depositMoney("8141221934", 5000);
        assertThrows(AccountNumberNotFoundException.class, () -> eMaxBank.checkBalance("08141221934", "1111"));
    }

    @Test
    public void testBankCanDepositTwiceToAnAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        eMaxBank.depositMoney("8141221934", 5000);
        eMaxBank.depositMoney("8141221934", 5000);
        assertEquals(10000, eMaxBank.checkBalance("8141221934", "1111"));
    }

    @Test
    public void testBankCanWithdrawFromAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        eMaxBank.depositMoney("8141221934", 5000);
        eMaxBank.withdrawMoney("8141221934", 2000, "1111");
        assertEquals(3000, eMaxBank.checkBalance("8141221934", "1111"));
    }

    @Test
    public void testBankCannotWithdrawAboveAmountFromAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        eMaxBank.depositMoney("8141221934", 5000);
        assertThrows(NegativeAmountException.class, () -> eMaxBank.withdrawMoney("8141221934", 12000, "1111"));
    }

    @Test
    public void testBankCannotWithdrawNegativeAmountFromAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "08141221934", "1111");
        eMaxBank.depositMoney("8141221934", 5000);
        assertThrows(NegativeAmountException.class, () -> eMaxBank.withdrawMoney("8141221934", -12000, "1111"));
    }

    @Test
    public void testBankCanTransferToAccount() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "07038243709", "1111");
        eMaxBank.registerCustomers("joseph", "emeka", "09073406000", "1211");
        eMaxBank.depositMoney("7038243709", 10000);
        assertEquals(2, eMaxBank.getNumbersOfAccount());
        assertEquals(10000, eMaxBank.checkBalance("7038243709", "1111"));
        eMaxBank.transferMoney("7038243709", 5000, "1111", "9073406000");
        assertEquals(5000, eMaxBank.checkBalance("7038243709", "1111"));
        assertEquals(5000, eMaxBank.checkBalance("9073406000", "1211"));
    }

    @Test
    public void testBanKCannotTransferToAccountWithWrongPin() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "07038243709", "1111");
        eMaxBank.registerCustomers("joseph", "emeka", "09073406000", "1211");
        eMaxBank.depositMoney("7038243709", 10000);
        assertEquals(2, eMaxBank.getNumbersOfAccount());
        assertThrows(WrongPinException.class, () -> eMaxBank.transferMoney("7038243709", 5000, "1113", "9073406000"));
    }

    @Test
    public void testBanKCannotTransferToAccountNotExit() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "07038243709", "1111");
        eMaxBank.registerCustomers("joseph", "emeka", "09073406000", "1211");
        eMaxBank.depositMoney("7038243709", 10000);
        assertEquals(2, eMaxBank.getNumbersOfAccount());
        assertThrows(WrongPinException.class, () -> eMaxBank.transferMoney("7038243709", 5000, "1113", "907340800"));
    }

    @Test
    public void testBanKCannotTransferFromWrongAccountNumbers() {
        eMaxBank.registerCustomers("emmanuel", "chukwuma", "07038243709", "1111");
        eMaxBank.registerCustomers("joseph", "emeka", "09073406000", "1211");
        eMaxBank.depositMoney("7038243709", 10000);
        assertEquals(2, eMaxBank.getNumbersOfAccount());
        assertThrows(AccountNumberNotFoundException.class, () -> eMaxBank.transferMoney("7033709", 5000, "1111", "9073406000"));
    }
}
