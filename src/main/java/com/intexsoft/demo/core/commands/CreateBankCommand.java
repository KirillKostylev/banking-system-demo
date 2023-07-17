package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.entity.Bank;
import com.intexsoft.demo.repository.BankRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.math.BigDecimal;

public class CreateBankCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "create-bank";
    public static final String COMMAND_DESCRIPTION = "Create a new bank command. Params: individualFee, legalFee. E.g. 'create-bank 0.15 0.2'";

    private final BankRepository bankRepository = RepositoryFactory.INSTANCE.getBankRepository();

    public CreateBankCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 2);
        ParamsValidator.validateAmount(this, args[0]);
        ParamsValidator.validateAmount(this, args[1]);


        var bank = new Bank(new BigDecimal(args[0]), new BigDecimal(args[1]));
        return bankRepository.create(bank);
    }

}
