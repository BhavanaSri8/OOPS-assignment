package org.example.validator;

import java.math.BigDecimal;

public interface TransactionValidator {
    void validateDeposit(BigDecimal amount);
    void validateWithdrawal(BigDecimal amount, BigDecimal currentBalance);
}