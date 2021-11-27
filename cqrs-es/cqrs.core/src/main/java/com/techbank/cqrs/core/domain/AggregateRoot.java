package com.techbank.cqrs.core.domain;

import com.techbank.cqrs.core.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author david
 */
public abstract class AggregateRoot {

    private final List<BaseEvent> changes  = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    @Getter
    @Setter
    private int version = -1;

    @Getter
    protected String id;

    public List<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException exception) {
            logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {}", event.getClass().getName()));
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Error applying event to aggregate.", exception);
        } finally {
            if(isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvent(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }
}
