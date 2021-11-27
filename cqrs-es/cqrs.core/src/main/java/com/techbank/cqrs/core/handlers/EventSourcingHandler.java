package com.techbank.cqrs.core.handlers;

import com.techbank.cqrs.core.domain.AggregateRoot;

/**
 * @author david
 */
public interface EventSourcingHandler<T> {

    void save(AggregateRoot aggregateRoot);
    T getById(String aggregateId);

}
