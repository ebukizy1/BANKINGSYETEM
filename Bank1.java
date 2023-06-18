package BankAccount2;

import java.util.ArrayList;
import java.util.List;

public class Bank1 {

    private final List<Account1> accounts = new ArrayList<>();
    private  Account1 account1;


    public void registerCustomers(String firstName, String lastName, String phoneNumber, String pin) {
        String accountNumber = generateAccountNumber(phoneNumber);
         account1 = new Account1(firstName, lastName, phoneNumber, pin);
        account1.setAccountNumber(accountNumber);
        accounts.add(account1);
    }

    public String searchForAccount(String accountNumber){
        for(Account1 account1 : accounts) if(accountNumber.equals(account1.getAccountNumber())) return accountNumber;
        throw new AccountNumberNotFoundException("account Number does Not Exit");
    }

    private String generateAccountNumber(String accountNumber){
      accountNumber =  accountNumber.substring(1);
        return accountNumber;
    }

    public int getNumbersOfAccount() {
        return accounts.size();
    }

    private Account1 searchForAccountNumber(String accountNumber){
        for (Account1 account1 : accounts) if (account1.getAccountNumber().equals(accountNumber)) return account1;
        throw new AccountNumberNotFoundException("Account does not exist");
    }
    public void depositMoney(String accountNumber, int amount) {
    account1 = searchForAccountNumber(accountNumber);
         account1.deposit(amount);
    }

    public double checkBalance(String accountNumber, String pin){
        double balance = 0;
            account1 =   searchForAccountNumber(accountNumber);
            balance = account1.checkBalance(pin);
        return balance;
    }

    public void withdrawMoney(String accountNumber, int amount, String pin) {
            account1 = searchForAccountNumber(accountNumber);
            account1.withdraw(amount, pin);
    }

    public void transferMoney(String senderAccountNumber, int amount, String pin, String receiverAccountNumber ) {
        account1 = searchForAccountNumber(senderAccountNumber);
            account1.withdraw(amount, pin);
        receiverValidation(receiverAccountNumber, amount);
    }
    private void receiverValidation(String receiverAccountNumber, int amount){
        account1 = searchForAccountNumber(receiverAccountNumber);
            account1.deposit(amount);
    }
    public void displayAccountCreated(String firstName, String lastName, String phoneNumber){
        String accountNumber = generateAccountNumber(phoneNumber);
        System.out.println("\nNAME : " + firstName + " " + lastName );
        System.out.println("PHONE NUMBER : " + phoneNumber );
        System.out.println("ACCOUNT NUMBER : " + accountNumber);
        System.out.println("ACCOUNT SUCCESSFULLY CREATED");
    }
}
