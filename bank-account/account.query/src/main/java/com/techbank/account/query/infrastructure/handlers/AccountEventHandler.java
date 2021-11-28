package com.techbank.account.query.infrastructure.handlers;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundsDepositedEvent;
import com.techbank.account.common.events.WithdrawFundsEvent;
import com.techbank.account.query.domain.AccountRepository;
import com.techbank.account.query.domain.BankAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author david
 */
@Service
public class AccountEventHandler implements EventHander {

    @Autowired
    private AccountRepository repository;

    @Override
    public void on(AccountOpenedEvent event) {
        BankAccountEntity bankAccount =
            BankAccountEntity.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();

        repository.save(bankAccount);
    }

    @Override
    public void on(AccountClosedEvent event) {
        repository.deleteById(event.getId());
    }

    @Override
    public void on(FundsDepositedEvent event) {
        Optional<BankAccountEntity> bankAccount = repository.findById(event.getId());
        if(!bankAccount.isPresent()) {
            return;
        }

        double currentBalance = bankAccount.get().getBalance();
        double latestBalance = currentBalance + event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        repository.save(bankAccount.get());
    }

    @Override
    public void on(WithdrawFundsEvent event) {
        Optional<BankAccountEntity> bankAccount = repository.findById(event.getId());
        if(!bankAccount.isPresent()) {
            return;
        }

        double currentBalance = bankAccount.get().getBalance();
        double latestBalance = currentBalance - event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        repository.save(bankAccount.get());
    }

}
