package com.example.ledger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LedgerServiceTest {

    private final LedgerService ledger = new LedgerService();

    @Test
    void startsWithZeroBalance() {
        assertThat(ledger.balance()).isZero();
        assertThat(ledger.transactions()).isEmpty();
    }

    @Test
    void depositIncreasesBalance() {
        ledger.deposit(100);
        ledger.deposit(50);

        assertThat(ledger.balance()).isEqualTo(150);
        assertThat(ledger.transactions()).hasSize(2);
    }

    @Test
    void withdrawDecreasesBalance() {
        ledger.deposit(100);
        Transaction transaction = ledger.withdraw(30);

        assertThat(transaction.type()).isEqualTo(Transaction.Type.WITHDRAW);
        assertThat(transaction.balanceAfter()).isEqualTo(70);
        assertThat(ledger.balance()).isEqualTo(70);
    }

    @Test
    void withdrawBeyondBalanceFails() {
        ledger.deposit(40);

        assertThatThrownBy(() -> ledger.withdraw(50))
                .isInstanceOf(InsufficientFundsException.class);
        assertThat(ledger.balance()).isEqualTo(40);
    }
}
