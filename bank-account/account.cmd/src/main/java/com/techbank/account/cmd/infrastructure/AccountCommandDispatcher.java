package com.techbank.account.cmd.infrastructure;

import com.techbank.cqrs.core.commands.BaseCommand;
import com.techbank.cqrs.core.commands.CommandHandlerMethod;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    /**
     * registers a new command handler method by adding it to our routes field
     */
    @Override
    public <T extends BaseCommand> void registerHander(Class<T> type, CommandHandlerMethod<T> handler) {
        List<CommandHandlerMethod> handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        List<CommandHandlerMethod> handlers = routes.get(command.getClass());
        if(handlers.isEmpty()) {
            throw new RuntimeException("No command handler was registered.");
        }
        if(handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler.");
        }
        handlers.get(0).handle(command);
    }
}
