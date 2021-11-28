package com.techbank.account.query.infrastructure.handlers;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundsDepositedEvent;
import com.techbank.account.common.events.WithdrawFundsEvent;

/**
 * @author david
 */
public interface EventHander {

    void on(AccountOpenedEvent event);
    void on(AccountClosedEvent event);
    void on(FundsDepositedEvent event);
    void on(WithdrawFundsEvent event);

}
