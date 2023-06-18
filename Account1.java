package BankAccount2;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Account1 {
    private double balance;
    private final String pin;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String accountNumber;

    public Account1(String firstName, String lastName, String phoneNumber, String pin) {
        this.pin = pin;
        validatePin(pin);
        this.firstName = firstName;
        validateNamesEnter(firstName);
        this.lastName = lastName;
        validateNamesEnter(lastName);
        this.phoneNumber = phoneNumber;
        validatePhoneNumber(phoneNumber);
    }


    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getAccountNumber() {return accountNumber;}

    public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}

    public double checkBalance(String pin) {
        if(this.pin.equals(pin))
      return balance;
        else  throw new WrongPinException("Invalid pin enter");
    }

    public void deposit(int amount) {
        if(amount > 0) balance += amount;
        else throw new NegativeAmountException("in valid Transaction negative Amount enter");
    }

    public void withdraw(int amount, String pin){
        if(this.pin.equals(pin));
        else   throw  new WrongPinException("Invalid pin Enter");
        if (amount > 0 && amount < balance) balance-= amount;
        else {throw new NegativeAmountException("in valid Transaction account insufficient");}
    }
    public static void validatePin(String pin){
            String regex = "\\d{4}";
            if (!Pattern.matches(regex, pin))
                throw new WrongPinException("Invalid PIN. PIN should be 4 digits from the range of 0-9.");

    }
    public static  void validateNamesEnter(String firstName){
        String regex = "[a-zA-Z]+";
        if (!Pattern.matches(regex, firstName))
            throw  new WrongNameException("Invalid  names. Only letters A-Z and a-z are allowed.");
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length()!= 11 || !phoneNumber.matches("^0\\d{10}$")) {
            throw new WrongPhoneNumberException("invalid phone number enter");
        }
    }



}
