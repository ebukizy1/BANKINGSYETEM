package BankAccount2;

import bankaccount.Bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner userInputs = new Scanner(System.in);
    private static final Bank1 bank = new Bank1();

    private static void timer(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE hh:mm:ssa");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(dateTimeFormatter.format(localDateTime));
    }
    private static void generateDelay() throws InterruptedException {
        System.out.printf("Loading, please wait");
        for (int index = 0; index < 3; index++) {
            Thread.sleep(1000);
            System.out.printf(".");
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        timer();
        generateDelay();
        displayMenuBar();


    }


    public static void displayMenuBar() {
try {
        display("""
                \n=============================
                ====WELCOME TO E-MAX - BANK====
                =============================
                1 --> CREATE ACCOUNT
                2 --> CASH TRANSACTION
                3 --> EXIT
                """);
        String userInput = input(userInputs);
        switch (userInput.charAt(0)) {
            case '1' -> bankRegisterCustomers();
            case '2' -> openBankMenuBar();
            case '3' -> exits();
            default -> displayMenuBar();
        }
        }catch (StringIndexOutOfBoundsException e){
    displayMenuBar();
        } catch (InterruptedException e) {
    throw new RuntimeException(e);
}
    }

    private static void exits() {
        System.exit(404);
    }


    private static void bankRegisterCustomers() throws InterruptedException {
        try {
            display(" |--> WELCOME TO E-MAX-BANK FILL IN YOUR DETAILS <--|");
            display("ENTER YOUR FIRST NAME");
            String firstName = input(userInputs).toUpperCase();
            display("ENTER YOUR LAST NAME");
            String lastName = input(userInputs).toUpperCase();
            display("ENTER YOUR PHONE NUMBER");
            String phoneNumber = input(userInputs);
            display("ENTER YOUR 4 DIGIT PIN");
            String pin = input(userInputs);
            bank.registerCustomers(firstName, lastName, phoneNumber, pin);
            generateDelay();
            bank.displayAccountCreated(firstName, lastName, phoneNumber);
            openBankMenuBar();
        } catch (WrongNameException e) {
            display("Incorrect name input entered, try Again!");
        } catch (WrongPhoneNumberException e) {
            display("incorrect phoneNumber entered try Again!");
        } catch (WrongPinException e) {
            display("wrong pin entered  try Again! ");
        }
        promptBack();
    }

    private static void openBankMenuBar() throws InterruptedException {
        display("""
                \n====+====+====+=====+====+==+==
                ===+= Welcome To Your Account=+===
                ===+====+===+==+====+===+==+===
                1 --> Deposit
                2 --> Withdrawal
                3 --> Balance Inquiry
                4 --> Transfer
                5 --> Change PIN
                6 --> Exit
                """);
        String userInput = input(userInputs);
        switch (userInput.charAt(0)) {
            case '1' -> deposit();
            case '2' -> withdrawal();
            case '3' -> balanceInquiry();
            case '4' -> transfer();
            case '6' -> exits();
            default -> displayMenuBar();
        }

    }

    private static void transfer() throws InterruptedException {
        try {
            display("ENTER YOUR ACCOUNT NUMBER ");
            String accountNumber = input(userInputs);
            display("ENTER THE AMOUNT");
            String amount = input(userInputs);
            int amount1 = Integer.parseInt(amount);
            display("ENTER YOUR 4 DIGIT PIN");
            String pin = input(userInputs);
            display("ENTER THE RECEIVER ACCOUNT NUMBER");
            String receiverNumber = input(userInputs);
            bank.transferMoney(accountNumber, amount1, pin, receiverNumber);
            generateDelay();
            successfulTransaction();
            promptBack1();
        }catch (AccountNumberNotFoundException e) {
            display("account not found");
        }catch (NegativeAmountException e) {
            display("invalid amount entered");
        }catch (WrongPinException e) {
            display("wrong pin entered  try Again! ");
        }
        promptBack();
    }

    private static void balanceInquiry() throws InterruptedException {

        try {
            display("ENTER YOUR ACCOUNT NUMBER");
            String accountNumber = input(userInputs);
            display("ENTER YOUR 4 DIGIT PIN");
            String pin = input(userInputs);
           double balance =  bank.checkBalance(accountNumber, pin);
            String accountBalanceDetailsToDisplay = String.format("""
                YOUR ACCOUNT BALANCE IS : 
                %.2f
                """, balance);
            display(accountBalanceDetailsToDisplay);
            generateDelay();
            successfulTransaction();
            promptBack1();
        }catch (AccountNumberNotFoundException e) {
            display("account not found");
        }catch (WrongPinException e) {
        display("wrong pin entered  try Again! ");
        openBankMenuBar();
    }
}

    private static void deposit() throws InterruptedException {

        try {
            display("ENTER YOUR ACCOUNT NUMBER");
            String accountNumber = input(userInputs);
            display("ENTER THE AMOUNT");
            String amount = input(userInputs);
            int amount1 = Integer.parseInt(amount);
            bank.depositMoney(accountNumber, amount1);
            generateDelay();
            successfulTransaction();
            promptBack1();
        } catch (AccountNumberNotFoundException e) {
            display("account not found");
        } catch (NegativeAmountException e) {
            display("invalid amount entered");
        }promptBack1();
    }
    public static void withdrawal() throws InterruptedException {

        try {
            display("ENTER YOUR ACCOUNT NUMBER");
            String accountNumber = input(userInputs);
            display("ENTER THE AMOUNT");
            String amount = input(userInputs);
            int amount1 = Integer.parseInt(amount);
            display("ENTER YOUR 4 DIGIT PIN");
            String pin = input(userInputs);
            bank.withdrawMoney(accountNumber, amount1, pin);
            generateDelay();
            successfulTransaction();
            promptBack1();
        } catch (AccountNumberNotFoundException e) {
            display("account not found");
        } catch (NegativeAmountException e) {
            display("invalid amount entered");
        }  catch (WrongPinException e) {
        display("wrong pin entered  try Again! ");
    }
        promptBack1();

    }

    private static void promptBack1() throws InterruptedException {
        display("""
                 Do you want to try again!!
                                    1-> yes
                                    2-> No
                """);
        String promptBack = input(userInputs);
        switch (promptBack.charAt(0)) {
            case '1' -> openBankMenuBar();
            case '2' -> exits();
            default -> displayMenuBar();
        }
    }

        private static void promptBack () throws InterruptedException {
            display("""
                     Do you want to try again!!
                                        1-> yes
                                        2-> No
                    """);
            String promptBack = input(userInputs);
            switch (promptBack.charAt(0)) {
                case '1' -> bankRegisterCustomers();
                case '2' -> exits();
                default -> displayMenuBar();
            }

        }    public static void numberValidation(String number){
        String regex = "[1-3]";
        if (!Pattern.matches(regex, number)) {
            throw new InValidAmountEnter.WrongPinException("Invalid PIN. Only numbers 1 and 2 are allowed.");
        }
    }


        public static void display (String message){
            System.out.println(message);
        }

        public static String input (Scanner userInputs){
            return userInputs.nextLine();
        }
        public static void successfulTransaction(){
            display("\nTRANSACTION SUCCESSFUL!!");
        }

}
