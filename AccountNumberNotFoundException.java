package BankAccount2;

public class AccountNumberNotFoundException extends RuntimeException{
    public AccountNumberNotFoundException(String message){
        super(message);
    }
}
