package com.techbank.account.cmd.api.commands;

import com.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

/**
 * @author david
 */
@Data
public class DepositFundsCommand extends BaseCommand {

    private double amount;

}
