package com.example.ledger;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LedgerService {

    private final List<Transaction> transactions = new ArrayList<>();
    private int balance;

    public synchronized Transaction deposit(int amount) {
        balance += amount;
        Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, amount);
        transactions.add(transaction);
        return transaction;
    }

    public synchronized Transaction withdraw(int amount) {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
        Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, amount);
        transactions.add(transaction);
        return transaction;
    }

    public synchronized int balance() {
        return balance;
    }

    public synchronized List<Transaction> transactions() {
        return List.copyOf(transactions);
    }
}
