package com.intexsoft.demo.repository;

import com.intexsoft.demo.entity.Bank;

public class BankRepository extends JsonAbstractRepository<Bank> {

    @Override
    Class<Bank> getStorageValueClass() {
        return Bank.class;
    }
}
