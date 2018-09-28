package net.siudek.lesson01;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.NoArgsConstructor;
import lombok.val;
import lombok.extern.java.Log;

@Aggregate
@NoArgsConstructor
class BookAggregate {

    @AggregateIdentifier
    private String instanceId;
    private String name;

    @CommandHandler
    public MyAggregate(AggregateCreateCommand cmd) {
        val evt = new AggregateDraftedEvent(cmd.getInstanceId().toString(), cmd.getName());
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handle(AggregatePublishCommand cmd) {
        val evt = new AggregatePublishedEvent(cmd.getInstanceId(), name);
        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(AggregateDraftedEvent evt) {
        instanceId = evt.getInstanceId().toString();
        name = evt.getName();
    }

    @EventSourcingHandler
    public void on(AggregatePublishedEvent evt) {
        //
    }
}