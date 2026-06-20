package com.example.ledger;

public record Transaction(Type type, int amount, int balanceAfter) {

    public enum Type {
        DEPOSIT,
        WITHDRAW
    }
}
