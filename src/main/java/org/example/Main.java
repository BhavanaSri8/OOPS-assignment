package org.example;

import org.example.model.Account;
import org.example.model.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.InMemoryAccountRepository;
import org.example.repository.InMemoryTransactionRepository;
import org.example.repository.TransactionRepository;
import org.example.service.BankService;
import org.example.service.ChildrenBankService;
import org.example.validator.ChildrenTransactionValidator;
import org.example.validator.TransactionValidator;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Dependency Injection - following Dependency Inversion Principle
        AccountRepository accountRepo = new InMemoryAccountRepository();
        TransactionRepository transactionRepo = new InMemoryTransactionRepository();
        TransactionValidator validator = new ChildrenTransactionValidator();
        BankService bankService = new ChildrenBankService(accountRepo, transactionRepo, validator);

        try {
            // Create account for a child
            Account account = bankService.createAccount("Alice", 10);
            System.out.println("Created account: " + account.getAccountNumber() + " for " + account.getChildName());

            // Make deposits
            bankService.deposit(account.getAccountNumber(), new BigDecimal("25.00"));
            bankService.deposit(account.getAccountNumber(), new BigDecimal("30.00"));
            System.out.println("Balance after deposits: $" + bankService.getBalance(account.getAccountNumber()));

            // Make withdrawal
            bankService.withdraw(account.getAccountNumber(), new BigDecimal("15.00"));
            System.out.println("Account Holder: " + account.getChildName() + ", Balance: $" + bankService.getBalance(account.getAccountNumber()));
            
            // Show transaction history
            List<Transaction> history = bankService.getTransactionHistory(account.getAccountNumber());
            System.out.println("\nTransaction History:");
            history.forEach(t -> System.out.println(t.getType() + ": $" + t.getAmount() + " at " + t.getTimestamp()));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}