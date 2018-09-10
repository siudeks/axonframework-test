package net.siudek.lesson01;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.Value;
import lombok.val;

@Aggregate
class UniqueAggregateSupportedBySaga {

    @AggregateIdentifier
    private UUID instanceId;

    @CommandHandler
    public UniqueAggregateSupportedBySaga(CreateAggregateCommand cmd) {
        val evt = new AggregateCreatedEvent(cmd.getInstanceId(), cmd.getName());
        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(AggregateCreatedEvent evt) {
        instanceId = evt.getInstanceId();
    }
}

@Value
class CreateAggregateCommand {

    @AggregateIdentifier
    private UUID instanceId;
    private String name;
}

@Value
class AggregateCreatedEvent {
    private UUID instanceId;
    private String name;
}

