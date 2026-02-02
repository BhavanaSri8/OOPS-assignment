package org.example.validator;

import java.math.BigDecimal;

public class ChildrenTransactionValidator implements TransactionValidator {
    private static final BigDecimal MAX_DEPOSIT = new BigDecimal("100.00");
    private static final BigDecimal MAX_WITHDRAWAL = new BigDecimal("50.00");

    @Override
    public void validateDeposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (amount.compareTo(MAX_DEPOSIT) > 0) {
            throw new IllegalArgumentException("Deposit amount cannot exceed $" + MAX_DEPOSIT);
        }
    }

    @Override
    public void validateWithdrawal(BigDecimal amount, BigDecimal currentBalance) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount.compareTo(MAX_WITHDRAWAL) > 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot exceed $" + MAX_WITHDRAWAL);
        }
        if (amount.compareTo(currentBalance) > 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }
}