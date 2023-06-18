package BankAccount2;

public class InValidAmountEnter extends RuntimeException{

    public InValidAmountEnter(String message){
        super(message);
    }

    public static class WrongPinException extends RuntimeException{
        public WrongPinException(String message){
            super(message);
        }
    }
}
