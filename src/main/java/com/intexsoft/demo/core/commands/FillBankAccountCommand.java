package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.repository.BankAccountRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.math.BigDecimal;
import java.util.UUID;

public class FillBankAccountCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "fill-bank-account";
    public static final String COMMAND_DESCRIPTION = "Fill client's bank account. Params: bankAccountId, amount. E.g. 'fill-bank-account 987846d7-a4ea-44a4-a06a-75cf1513925d 100'";

    private final BankAccountRepository bankAccountRepository = RepositoryFactory.INSTANCE.getBankAccountRepository();

    public FillBankAccountCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 2);
        ParamsValidator.validateAmount(this, args[1]);

        var bankAccount = bankAccountRepository.findById(UUID.fromString(args[0]));
        bankAccount.setAmount(bankAccount.getAmount().add(new BigDecimal(args[1])));

        bankAccountRepository.update(bankAccount);
        return bankAccount;
    }

}
