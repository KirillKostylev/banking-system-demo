package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.core.ServiceFactory;


public class HelpCommand extends AbstractCommand implements Command {
    public static final String COMMAND_NAME = "help";
    public static final String COMMAND_DESCRIPTION = "Show list of commands";

    public HelpCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) {
        return ServiceFactory.getInstance().getCommandService().getCommands().stream().toList();
    }
}
