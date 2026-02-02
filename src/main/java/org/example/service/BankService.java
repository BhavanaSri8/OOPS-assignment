package org.example.service;

import org.example.model.Account;
import org.example.model.Transaction;
import java.math.BigDecimal;
import java.util.List;

public interface BankService {
    Account createAccount(String childName, int childAge);
    void deposit(String accountNumber, BigDecimal amount);
    void withdraw(String accountNumber, BigDecimal amount);
    BigDecimal getBalance(String accountNumber);
    List<Transaction> getTransactionHistory(String accountNumber);
}