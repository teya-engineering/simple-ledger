# Simple Ledger

A minimal Spring Boot ledger. Transactions are kept in an in-memory list, so the state resets on restart.

## Prerequisites

- Java 25+
- Maven 3+

## Run

```bash
./mvnw spring-boot:run
```

App starts on `http://localhost:8080`.

## Endpoints

| Method | Path            | Body              | Description                          |
|--------|-----------------|-------------------|--------------------------------------|
| POST   | `/deposit`      | `{"amount": 100}` | Add money. Amount must be positive.  |
| POST   | `/withdraw`     | `{"amount": 30}`  | Take money. Fails if over balance.   |
| GET    | `/transactions` | -                 | List all transactions in order.      |

Each transaction returns its `type`, `amount`, and the `balanceAfter` it produced.

## Examples

```bash
curl -X POST localhost:8080/deposit  -H 'Content-Type: application/json' -d '{"amount":100}'
curl -X POST localhost:8080/withdraw -H 'Content-Type: application/json' -d '{"amount":30}'
curl localhost:8080/transactions
```

## Behaviour

- A non-positive amount returns `400 Bad Request`.
- Withdrawing more than the balance returns `422 Unprocessable Entity`.

## Test

```bash
./mvnw test
```

## License

Licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE) for details.
