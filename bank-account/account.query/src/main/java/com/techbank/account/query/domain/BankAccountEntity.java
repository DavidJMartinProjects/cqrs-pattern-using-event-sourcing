package com.techbank.account.query.domain;

import com.techbank.account.common.dto.AccountType;
import com.techbank.cqrs.core.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author david
 */
@Entity
public class BankAccountEntity extends BaseEntity {

    @Id
    private String id;

    private String accountHolder;

    private Date creationType;

    private AccountType accountType;

    private double balance;

}
