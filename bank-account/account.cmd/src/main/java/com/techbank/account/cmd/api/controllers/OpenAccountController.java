package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.cmd.api.dto.OpenAccountResponse;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
public class OpenAccountController {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("Bank account creation request completed successfully.", id), HttpStatus.CREATED);
        } catch(IllegalStateException exception) {
            log.warn("Client made a bad request: {}", exception.getMessage(), exception);
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        } catch(Exception exception) {
            log.error("Cannot create bank account for id: {}", id);
            return new ResponseEntity<>(new BaseResponse("Encountered error creating bank account for id:" + id), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
