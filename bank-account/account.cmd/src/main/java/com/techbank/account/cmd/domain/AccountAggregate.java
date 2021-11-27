package com.techbank.account.cmd.domain;

import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundsDepositedEvent;
import com.techbank.account.common.events.WithdrawFundsEvent;
import com.techbank.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author david
 */
@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(
            AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build()
        );
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if(!this.active) {
            throw new IllegalStateException("Funds cannot be deposited into a closed bank account.");
        }
        if(amount <= 0) {
            throw new IllegalStateException("The deposit amount must be greater than zero.");
        }
        raiseEvent(
            FundsDepositedEvent.builder()
                .id(this.id)
                .amount(amount)
                .build()
        );
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount) {
        if(!this.active) {
            throw new IllegalStateException("Funds cannot be withdrawn from a closed bank account.");
        }
        raiseEvent(
            WithdrawFundsEvent.builder()
                .id(this.id)
                .amount(amount)
                .build()
        );
    }

    public void apply(WithdrawFundsEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount() {
        if(!this.active) {
            throw new IllegalStateException("The bank account has already been closed.");
        }
        raiseEvent(
            AccountClosedEvent.builder()
                .id(this.id)
                .build()
        );
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }

}
