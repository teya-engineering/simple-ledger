package com.example.ledger;

import jakarta.validation.constraints.Positive;

public record AmountRequest(@Positive int amount) {
}
