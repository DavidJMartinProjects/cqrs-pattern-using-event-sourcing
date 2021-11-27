package com.techbank.account.cmd.infrastructure;

import com.techbank.cqrs.core.domain.AggregateRoot;
import com.techbank.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.stereotype.Service;

/**
 * @author david
 */
@Service
public class AccountEventSourcingHandler implements EventSourcingHandler {

    @Override
    public void save(AggregateRoot aggregateRoot) {

    }

    @Override
    public Object getById(String aggregateId) {
        return null;
    }

}
