package com.lkss.sbproject.exceptions;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("Amount must be greater than zero");
    }
}
