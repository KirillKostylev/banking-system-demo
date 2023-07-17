package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.repository.BankAccountRepository;
import com.intexsoft.demo.repository.ClientRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.util.UUID;

public class FindBankAccountsCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "find-bank-accounts";
    public static final String COMMAND_DESCRIPTION = "Find client's bank accounts. Params: clientId. E.g. 'find-bank-accounts 1574e3b9-1480-4f64-aad6-f1918b9fdea6'";

    private final ClientRepository clientRepository = RepositoryFactory.INSTANCE.getClientRepository();
    private final BankAccountRepository bankAccountRepository = RepositoryFactory.INSTANCE.getBankAccountRepository();

    public FindBankAccountsCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 1);

        var client = clientRepository.findById(UUID.fromString(args[0]));
        return bankAccountRepository.findBankAccountsByClientId(client.getId());
    }
}
