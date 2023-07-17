package com.intexsoft.demo.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Bank extends AbstractEntity {

    private BigDecimal individualFee;
    private BigDecimal legalFee;

    public Bank(UUID id, BigDecimal individualFee, BigDecimal legalFee) {
        super(id);
        this.individualFee = individualFee;
        this.legalFee = legalFee;
    }

    public Bank(BigDecimal individualFee, BigDecimal legalFee) {
        this.individualFee = individualFee;
        this.legalFee = legalFee;
    }

    public Bank() {
    }


    public BigDecimal getIndividualFee() {
        return individualFee;
    }

    public BigDecimal getLegalFee() {
        return legalFee;
    }

    public void setIndividualFee(BigDecimal individualFee) {
        this.individualFee = individualFee;
    }

    public void setLegalFee(BigDecimal legalFee) {
        this.legalFee = legalFee;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "individualFee=" + individualFee +
                ", legalFee=" + legalFee +
                ", id=" + id +
                '}';
    }
}
