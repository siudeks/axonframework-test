package net.siudek.lesson01;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.val;

@Aggregate
class MyAggregate {

    @AggregateIdentifier
    private UUID instanceId;
    private STATE state = STATE.DRAFT;

    @CommandHandler
    public MyAggregate(AggregateCreateCommand cmd) {
        val evt = new AggregateDraftedEvent(cmd.getInstanceId(), cmd.getName());
        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(AggregateDraftedEvent evt) {
        instanceId = evt.getInstanceId();
    }

    enum STATE {
        DRAFT,
        PUBLIC
    }
}


