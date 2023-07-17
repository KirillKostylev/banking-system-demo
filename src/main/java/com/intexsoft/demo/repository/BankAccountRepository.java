package com.intexsoft.demo.repository;

import com.intexsoft.demo.entity.BankAccount;
import com.intexsoft.demo.repository.exception.JsonStorageException;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class BankAccountRepository extends JsonAbstractRepository<BankAccount> {

    @Override
    Class<BankAccount> getStorageValueClass() {
        return BankAccount.class;
    }

    public Collection<BankAccount> findBankAccountsByClientId(UUID clientId) throws JsonStorageException {
        updateCacheIfRequired();
        return cache.values()
                .stream()
                .filter(ba -> Objects.equals(clientId, ba.getClientId()))
                .toList();
    }

    public Collection<UUID> findClientIdsByBankId(UUID bankId) throws JsonStorageException {
        updateCacheIfRequired();
        return cache.values()
                .stream()
                .filter(ba -> Objects.equals(bankId, ba.getBankId()))
                .map(BankAccount::getClientId)
                .toList();
    }
}
