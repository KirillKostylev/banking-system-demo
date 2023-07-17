package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.entity.BankAccount;
import com.intexsoft.demo.entity.Currency;
import com.intexsoft.demo.repository.BankAccountRepository;
import com.intexsoft.demo.repository.BankRepository;
import com.intexsoft.demo.repository.ClientRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.math.BigDecimal;
import java.util.UUID;

public class AddBankAccountCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "add-bank-account";
    public static final String COMMAND_DESCRIPTION = "Add a bank account to client. Params: clientId, bankId, currency (BYN, USD, EUR, RUS), amount (optional). E.g. 'add-bank-account 987846d7-a4ea-44a4-a06a-75cf1513925d 987846d7-a4ea-44a4-a06a-75cf1513925d usd 10'";

    private final ClientRepository clientRepository = RepositoryFactory.INSTANCE.getClientRepository();
    private final BankAccountRepository bankAccountRepository = RepositoryFactory.INSTANCE.getBankAccountRepository();
    private final BankRepository bankRepository = RepositoryFactory.INSTANCE.getBankRepository();

    public AddBankAccountCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 3, 4);
        ParamsValidator.validateAmount(this, args[3]);

        var clientId = UUID.fromString(args[0]);
        clientRepository.findById(clientId);

        var bankId = UUID.fromString(args[1]);
        bankRepository.findById(bankId);

        BigDecimal amount = new BigDecimal(0);
        if (args.length == 4) {
            amount = new BigDecimal(args[3]);
        }

        var bankAccount = new BankAccount(clientId, bankId, Currency.valueOf(args[2].toUpperCase()), amount);
        return bankAccountRepository.create(bankAccount);
    }

}
