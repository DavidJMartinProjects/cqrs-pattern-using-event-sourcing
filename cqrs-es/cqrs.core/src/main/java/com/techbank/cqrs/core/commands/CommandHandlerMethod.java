package com.techbank.cqrs.core.commands;

/**
 * @author david
 */
@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {

    void handle(T command);

}
