package org.example.repository;

import org.example.model.Account;
import java.util.Optional;

public interface AccountRepository {
    void save(Account account);
    Optional<Account> findByAccountNumber(String accountNumber);
    void update(Account account);
}