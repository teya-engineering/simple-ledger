package com.example.ledger;

public record Transaction(Type type, int amount) {

    public enum Type {
        DEPOSIT,
        WITHDRAW
    }
}
