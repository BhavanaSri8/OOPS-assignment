package org.example.repository;

import org.example.model.Account;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public void save(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    @Override
    public void update(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}