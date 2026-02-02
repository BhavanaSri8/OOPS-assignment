package org.example.service;

import org.example.model.Account;
import org.example.model.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.TransactionRepository;
import org.example.validator.TransactionValidator;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ChildrenBankService implements BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionValidator validator;

    public ChildrenBankService(AccountRepository accountRepository, 
                              TransactionRepository transactionRepository,
                              TransactionValidator validator) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.validator = validator;
    }

    @Override
    public Account createAccount(String childName, int childAge) {
        if (childAge < 5 || childAge > 17) {
            throw new IllegalArgumentException("Child age must be between 5 and 17");
        }
        String accountNumber = "CHILD-" + UUID.randomUUID().toString().substring(0, 8);
        Account account = new Account(accountNumber, childName, childAge);
        accountRepository.save(account);
        return account;
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        validator.validateDeposit(amount);
        Account account = getAccount(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.update(account);
        transactionRepository.save(new Transaction(accountNumber, Transaction.TransactionType.DEPOSIT, amount));
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        validator.validateWithdrawal(amount, account.getBalance());
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.update(account);
        transactionRepository.save(new Transaction(accountNumber, Transaction.TransactionType.WITHDRAWAL, amount));
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNumber) {
        getAccount(accountNumber); // Verify account exists
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    private Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountNumber));
    }
}