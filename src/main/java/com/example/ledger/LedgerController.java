package com.example.ledger;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LedgerController {

    private final LedgerService ledger;

    public LedgerController(LedgerService ledger) {
        this.ledger = ledger;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@Valid @RequestBody AmountRequest request) {
        return ledger.deposit(request.amount());
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@Valid @RequestBody AmountRequest request) {
        return ledger.withdraw(request.amount());
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return ledger.transactions();
    }

    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleInsufficientFunds(InsufficientFundsException exception) {
        return exception.getMessage();
    }
}
