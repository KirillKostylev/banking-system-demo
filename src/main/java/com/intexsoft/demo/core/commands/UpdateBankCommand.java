package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.entity.Bank;
import com.intexsoft.demo.repository.BankRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateBankCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "update-bank";
    public static final String COMMAND_DESCRIPTION = "Update an existing bank command. Params: id, individualFee, legalFee. E.g. 'update-bank 3474135b-2385-4a89-9f41-486a5e5ff760 myBank 0.15 0.2'";

    private final BankRepository bankRepository = RepositoryFactory.INSTANCE.getBankRepository();

    public UpdateBankCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 3);
        ParamsValidator.validateAmount(this, args[1]);
        ParamsValidator.validateAmount(this, args[2]);


        var bank = new Bank(UUID.fromString(args[0]), new BigDecimal(args[1]), new BigDecimal(args[2]));
        return bankRepository.update(bank);
    }
}
