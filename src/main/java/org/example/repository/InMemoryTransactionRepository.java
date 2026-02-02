package org.example.repository;

import org.example.model.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {
        return transactions.stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList());
    }
}