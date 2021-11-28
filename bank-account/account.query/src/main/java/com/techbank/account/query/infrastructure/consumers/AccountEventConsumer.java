package com.techbank.account.query.infrastructure.consumers;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundsDepositedEvent;
import com.techbank.account.common.events.WithdrawFundsEvent;
import com.techbank.account.query.infrastructure.handlers.EventHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * @author david
 */
@Service
public class AccountEventConsumer implements EventConsumer {

    @Autowired
    private EventHander eventHander;

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "$(spring.kafka.consumer.group-id)")
    @Override
    public void consume(AccountOpenedEvent event, Acknowledgment acknowledgment) {
        eventHander.on(event);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "$(spring.kafka.consumer.group-id)")
    @Override
    public void consume(AccountClosedEvent event, Acknowledgment acknowledgment) {
        eventHander.on(event);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "FundsDepositedEvent", groupId = "$(spring.kafka.consumer.group-id)")
    @Override
    public void consume(FundsDepositedEvent event, Acknowledgment acknowledgment) {
        eventHander.on(event);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "WithdrawFundsEvent", groupId = "$(spring.kafka.consumer.group-id)")
    @Override
    public void consume(WithdrawFundsEvent event, Acknowledgment acknowledgment) {
        eventHander.on(event);
        acknowledgment.acknowledge();
    }

}
