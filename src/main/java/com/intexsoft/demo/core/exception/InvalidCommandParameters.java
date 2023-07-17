package com.intexsoft.demo.core.exception;

public class InvalidCommandParameters extends RuntimeException {

    public InvalidCommandParameters(String message) {
        super(message);
    }
}
