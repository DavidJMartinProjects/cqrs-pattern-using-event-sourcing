package com.techbank.account.query.domain;

import com.techbank.cqrs.core.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author david
 */
public interface AccountRepository extends CrudRepository<BankAccountEntity, String> {

    Optional<BankAccountEntity> findByAccountHolder(String accountHolder);
    List<BaseEntity> findByBalanceGreaterThan(double balance);
    List<BaseEntity> findByBalanceLessThan(double balance);

}
