package com.intexsoft.demo.core.config;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

public enum ConsoleOutput {
    INSTANCE(System.out);
    private final PrintStream printStream;

    private ConsoleOutput(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void println(String msg) {
        printStream.println(msg);
    }

    public void print(String msg) {
        printStream.print(msg);
    }

    public void println(Object obj) {
        printStream.println(obj);
    }

    public void printResult(Object obj) {
        if (obj.getClass().isArray() || obj instanceof Collection) {
            new ArrayList<>((Collection<?>) obj).forEach(this::println);
            return;
        }

        println(obj);
    }
}
