package com.techbank.account.cmd.api.commands;

import com.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

/**
 * @author david
 */
@Data
public class WithdrawFindsCommand extends BaseCommand {

    private double amount;

}
