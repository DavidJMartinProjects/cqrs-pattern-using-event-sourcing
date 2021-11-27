package com.techbank.cqrs.core.messages;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author david
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Message {

    private String id;

}
