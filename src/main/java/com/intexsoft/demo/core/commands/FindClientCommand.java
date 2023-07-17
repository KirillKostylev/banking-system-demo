package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.repository.BankAccountRepository;
import com.intexsoft.demo.repository.ClientRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class FindClientCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "find-clients";
    public static final String COMMAND_DESCRIPTION = "Find all clients in bank. Params: bankId. E.g. 'find-clients 1574e3b9-1480-4f64-aad6-f1918b9fdea6'";

    private final BankAccountRepository bankAccountRepository = RepositoryFactory.INSTANCE.getBankAccountRepository();
    private final ClientRepository clientRepository = RepositoryFactory.INSTANCE.getClientRepository();

    public FindClientCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 1, 2);

        Collection<UUID> clientIds;
        if (args.length == 1) {
            clientIds = bankAccountRepository.findClientIdsByBankId(UUID.fromString(args[0]));
        } else {
            clientIds = Collections.singleton(UUID.fromString(args[1]));
        }

        return clientRepository.findClientsByIds(clientIds)
                .stream()
                .peek(client -> client.setAccountIds(null)) // TODO bank shouldn't know about all client's accounts (replace with DTO)
                .toList();
    }
}
