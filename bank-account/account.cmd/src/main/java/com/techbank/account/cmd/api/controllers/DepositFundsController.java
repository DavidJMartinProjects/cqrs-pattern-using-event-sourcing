package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.DepositFundsCommand;
import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.cmd.api.dto.OpenAccountResponse;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggregateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author david
 */
@RestController
@RequestMapping(ApiUrlPaths.ACCOUNT_BASE_PATH)
@Slf4j
public class DepositFundsController {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping("{id}" + ApiUrlPaths.DEPOSIT_FUNDS_PATH)
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id, @RequestBody DepositFundsCommand command) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Deposit funds request completed successfully."), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException exception) {
            log.warn("Client made a bad request: {}", exception.getMessage(), exception);
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            log.error("Error depositing funds");
            return new ResponseEntity<>(new BaseResponse("Encountered error depositing funds for account: " + id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
