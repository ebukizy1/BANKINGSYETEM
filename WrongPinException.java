package BankAccount2;

public class WrongPinException extends RuntimeException{

    public WrongPinException(String message){
        super(message);
    }
}
