package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {
    private final String accountNumber;
    private final String childName;
    private final int childAge;
    private BigDecimal balance;
    private final LocalDateTime createdDate;

    public Account(String accountNumber, String childName, int childAge) {
        this.accountNumber = accountNumber;
        this.childName = childName;
        this.childAge = childAge;
        this.balance = BigDecimal.ZERO;
        this.createdDate = LocalDateTime.now();
    }

    public String getAccountNumber() { return accountNumber; }
    public String getChildName() { return childName; }
    public int getChildAge() { return childAge; }
    public BigDecimal getBalance() { return balance; }
    public LocalDateTime getCreatedDate() { return createdDate; }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}