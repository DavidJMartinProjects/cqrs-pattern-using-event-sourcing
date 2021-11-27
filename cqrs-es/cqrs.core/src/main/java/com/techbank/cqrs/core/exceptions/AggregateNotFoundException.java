package com.techbank.cqrs.core.exceptions;

/**
 * @author david
 */
public class AggregateNotFoundException extends RuntimeException {
    public AggregateNotFoundException(String message) {
        super(message);
    }

}
