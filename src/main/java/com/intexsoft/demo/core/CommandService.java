package com.intexsoft.demo.core;

import com.intexsoft.demo.core.commands.AddBankAccountCommand;
import com.intexsoft.demo.core.commands.AddClientCommand;
import com.intexsoft.demo.core.commands.CreateBankCommand;
import com.intexsoft.demo.core.commands.FillBankAccountCommand;
import com.intexsoft.demo.core.commands.FindBankAccountsCommand;
import com.intexsoft.demo.core.commands.FindBankCommand;
import com.intexsoft.demo.core.commands.FindClientCommand;
import com.intexsoft.demo.core.commands.HelpCommand;
import com.intexsoft.demo.core.commands.TransferMoneyCommand;
import com.intexsoft.demo.core.commands.UpdateBankCommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandService {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandService() {
        fillInContext();
    }

    public Command findCommandOrDefault(String commandName) {
        return commands.getOrDefault(commandName, commands.get(HelpCommand.COMMAND_NAME));
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }

    private void fillInContext() {
        var helpCommand = new HelpCommand();
        var createBankCommand = new CreateBankCommand();
        var findBankCommand = new FindBankCommand();
        var updateBankCommand = new UpdateBankCommand();
        var addClientCommand = new AddClientCommand();
        var findBankAccountsCommand = new FindBankAccountsCommand();
        var findClientCommand = new FindClientCommand();
        var addBankAccountCommand = new AddBankAccountCommand();
        var fillBankAccountCommand = new FillBankAccountCommand();
        var transferMoneyCommand = new TransferMoneyCommand();

        commands.put(helpCommand.getName(), helpCommand);
        commands.put(createBankCommand.getName(), createBankCommand);
        commands.put(findBankCommand.getName(), findBankCommand);
        commands.put(updateBankCommand.getName(), updateBankCommand);
        commands.put(addClientCommand.getName(), addClientCommand);
        commands.put(findBankAccountsCommand.getName(), findBankAccountsCommand);
        commands.put(findClientCommand.getName(), findClientCommand);
        commands.put(addBankAccountCommand.getName(), addBankAccountCommand);
        commands.put(fillBankAccountCommand.getName(), fillBankAccountCommand);
        commands.put(transferMoneyCommand.getName(), transferMoneyCommand);
    }
}
