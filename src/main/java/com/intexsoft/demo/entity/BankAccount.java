package com.intexsoft.demo.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount extends AbstractEntity {
    private UUID clientId;
    private UUID bankId;
    private Currency currency;
    private BigDecimal amount = BigDecimal.valueOf(0.0);

    public BankAccount() {
    }

    public BankAccount(UUID clientId, UUID bankId, Currency currency, BigDecimal amount) {
        this.clientId = clientId;
        this.bankId = bankId;
        this.currency = currency;
        this.amount = amount;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getBankId() {
        return bankId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "clientId=" + clientId +
                ", bankId=" + bankId +
                ", currency=" + currency +
                ", amount=" + amount +
                ", id=" + id +
                '}';
    }
}
