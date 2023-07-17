package com.intexsoft.demo.utils;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.core.exception.InvalidCommandParameters;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public class ParamsValidator {

    private ParamsValidator() {
    }

    public static void validateParametersNumber(Command command, String[] args, int... expectedNumberOfParameters) {
        if (IntStream.of(expectedNumberOfParameters).noneMatch(value -> value == args.length)) {
            throw new InvalidCommandParameters("Incorrect number of params for command: " + command.getName() + ". \n" + command.getDescription());
        }
    }

    public static void validateAmount(Command command, String str) {
        if (new BigDecimal(str).compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidCommandParameters("Amount can't be less then zero.\n" + command.getDescription());
        }
    }
}
