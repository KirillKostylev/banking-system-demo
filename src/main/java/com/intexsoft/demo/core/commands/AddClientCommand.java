package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.entity.BankAccount;
import com.intexsoft.demo.entity.Client;
import com.intexsoft.demo.entity.ClientType;
import com.intexsoft.demo.entity.Currency;
import com.intexsoft.demo.repository.BankAccountRepository;
import com.intexsoft.demo.repository.BankRepository;
import com.intexsoft.demo.repository.ClientRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.math.BigDecimal;
import java.util.UUID;

public class AddClientCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "add-client";
    public static final String COMMAND_DESCRIPTION = "Add a new client to bank. Params: bankId, clientType (INDIVIDUAL or LEGAL), currency (BYN, USD, EUR, RUS). E.g. 'add-client 987846d7-a4ea-44a4-a06a-75cf1513925d individual usd'";

    private final ClientRepository clientRepository = RepositoryFactory.INSTANCE.getClientRepository();
    private final BankAccountRepository bankAccountRepository = RepositoryFactory.INSTANCE.getBankAccountRepository();
    private final BankRepository bankRepository = RepositoryFactory.INSTANCE.getBankRepository();

    public AddClientCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 3);

        var bankId = UUID.fromString(args[0]);
        bankRepository.findById(bankId);

        var client = new Client(ClientType.valueOf(args[1].toUpperCase()));
        clientRepository.create(client);

        var bankAccount = new BankAccount(client.getId(), bankId, Currency.valueOf(args[2].toUpperCase()), new BigDecimal(0));
        bankAccountRepository.create(bankAccount);
        return client;
    }

}
