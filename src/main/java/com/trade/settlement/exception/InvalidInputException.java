package com.trade.settlement.exception;

public class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = -5091972167278954939L;

    public InvalidInputException(String message) {
        super(message);
    }
}
