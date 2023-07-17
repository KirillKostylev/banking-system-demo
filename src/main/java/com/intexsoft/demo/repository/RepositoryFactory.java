package com.intexsoft.demo.repository;

import java.util.Objects;

public enum RepositoryFactory {
    INSTANCE;

    private BankRepository bankRepository;
    private ClientRepository clientRepository;
    private BankAccountRepository bankAccountRepository;

    public BankRepository getBankRepository() {
        if (Objects.isNull(bankRepository)) {
            bankRepository = new BankRepository();
        }
        return bankRepository;
    }

    public ClientRepository getClientRepository() {
        if (Objects.isNull(clientRepository)) {
            clientRepository = new ClientRepository();
        }
        return clientRepository;
    }

    public BankAccountRepository getBankAccountRepository() {
        if (Objects.isNull(bankAccountRepository)) {
            bankAccountRepository = new BankAccountRepository();
        }
        return bankAccountRepository;
    }
}
