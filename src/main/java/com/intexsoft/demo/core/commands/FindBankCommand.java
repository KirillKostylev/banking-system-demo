package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.repository.BankRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.util.UUID;

public class FindBankCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "find-banks";
    public static final String COMMAND_DESCRIPTION = "Find banks command. Params: id (optional). E.g. 'find-banks 1574e3b9-1480-4f64-aad6-f1918b9fdea6'";

    private final BankRepository bankRepository = RepositoryFactory.INSTANCE.getBankRepository();

    public FindBankCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 0, 1);

        if (args.length == 0) {
            return bankRepository.findAll().values();
        }

        return bankRepository.findById(UUID.fromString(args[0]));
    }
}
