package com.techbank.cqrs.core.commands;

/**
 * @author david
 * Implementation of the Mediator Pattern using @FunctionalInterface
 * This is a generic Interface to handle all of our CommandHandler methods
 */
@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {

    void handle(T command);

}
