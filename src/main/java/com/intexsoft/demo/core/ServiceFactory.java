package com.intexsoft.demo.core;

import java.util.Objects;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private CommandService commandService;

    public CommandService getCommandService() {
        if (Objects.isNull(commandService)) {
            commandService = new CommandService();
        }
        return commandService;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}
