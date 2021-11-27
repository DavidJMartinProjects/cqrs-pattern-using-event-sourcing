package com.techbank.cqrs.core.infrastructure;

import com.techbank.cqrs.core.commands.BaseCommand;
import com.techbank.cqrs.core.commands.CommandHandlerMethod;

/**
 * @author david
 * acts as a generic interface that registers commandHandlers and sends/dispatches a command
 */
public interface CommandDispatcher {

    <T extends BaseCommand> void registerHander(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);

}
