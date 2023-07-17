package com.intexsoft.demo.core.exception;

public class InvalidTransferRequest extends RuntimeException {
    public InvalidTransferRequest(String message) {
        super(message);
    }
}
