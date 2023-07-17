package com.intexsoft.demo.core;


import com.intexsoft.demo.core.config.ConsoleInput;
import com.intexsoft.demo.core.config.ConsoleOutput;

public class ApplicationContext {
    private final CommandService commandService = ServiceFactory.getInstance().getCommandService();
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;

    private ApplicationContext(ConsoleInput consoleInput, ConsoleOutput consoleOutput) {
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
    }

    public static void run() {
        new ApplicationContext(ConsoleInput.INSTANCE, ConsoleOutput.INSTANCE).start();
    }

    private void start() {
        while (true) {
            consoleOutput.print("> ");
            var commandNameAndParams = consoleInput.readCommand();

            if ("exit".equals(commandNameAndParams.getLeft())) {
                consoleInput.destroy();
                return;
            }

            var command = commandService.findCommandOrDefault(commandNameAndParams.getLeft());
            Object result = executeAndHandleExceptions(command, commandNameAndParams.getRight());
            consoleOutput.printResult(result);
        }
    }

    private Object executeAndHandleExceptions(Command command, String[] args) {
        try {
            return command.execute(args);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
