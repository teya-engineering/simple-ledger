package com.example.ledger;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LedgerService {

    private final List<Transaction> transactions = new ArrayList<>();

    public synchronized Transaction deposit(int amount) {
        int balance = balance() + amount;
        Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, amount, balance);
        transactions.add(transaction);
        return transaction;
    }

    public synchronized Transaction withdraw(int amount) {
        int balance = balance();
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, amount, balance - amount);
        transactions.add(transaction);
        return transaction;
    }

    public synchronized int balance() {
        return transactions.isEmpty() ? 0 : transactions.getLast().balanceAfter();
    }

    public synchronized List<Transaction> transactions() {
        return List.copyOf(transactions);
    }
}
