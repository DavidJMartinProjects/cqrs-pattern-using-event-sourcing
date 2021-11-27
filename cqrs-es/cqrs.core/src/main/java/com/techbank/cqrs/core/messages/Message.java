package com.techbank.cqrs.core.messages;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author david
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Message {

    private String id;

}
