package com.example.ledger;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(int requested, int balance) {
        super("Cannot withdraw " + requested + ", balance is " + balance);
    }
}
