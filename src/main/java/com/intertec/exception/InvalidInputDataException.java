package com.intertec.exception;

import java.text.MessageFormat;

public class InvalidInputDataException extends RuntimeException {

    public InvalidInputDataException(String message) {
        super(message);
    }

    public InvalidInputDataException(String message, Object... args) {
        super(MessageFormat.format(message,args));
    }

    public InvalidInputDataException(Exception e) {
        this(e.getMessage());
        String exceptionMessage = MessageFormat.format("Request failed due to: {0}", e.getMessage());
    }
}
